package com.bdb.opalocdeceval.controller.service.control;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdb.opalocdeceval.controller.service.interfaces.CarMaeCDTSService;
import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
public class ControllerCarMaeCDTS {

	@Autowired
	JobLauncher jobLauncher;
     
	@Autowired
	@Qualifier(value="JobCDTDeceval")
	Job job;
	
	@Autowired
	@Qualifier("serviceFTPS")
	FTPService serviceFTP;
	
	@Autowired
	CarMaeCDTSService serviceCarCDTS;
			
	String inicioArchivo = "MAECDTDCV";
	
	private Logger logger = LoggerFactory.getLogger(ControllerCarMaeCDTS.class);
	
	@GetMapping("cargarMaestroDeceval")
	public BatchStatus loadInformationDeceval() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, IOException, ErrorFtps {
		System.out.println("ENTRO AL CONTROLLER DE CUENTAS");
		
		serviceCarCDTS.eliminarInformacion();
		
		convertir();
		
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        //System.out.println("JobExecution: " + jobExecution.getStatus());
        logger.info("JobExecution: " + jobExecution.getStatus());

        //System.out.println("Cuentas Job is Running...");
        while (jobExecution.isRunning()) {
            System.out.println("...");
        }
                    
        return jobExecution.getStatus();
    }
	
	@PostMapping("actualizarTempMaeDcv")
	public String actualizarTempMaeDcv() {
		serviceCarCDTS.homologarCodId();  //no problem
		serviceCarCDTS.homologarCodIdNIT();
		serviceCarCDTS.homologarTipPlazo(); //no problem
		serviceCarCDTS.homologarTipBase(); //no problem
		serviceCarCDTS.homologarTipPeriodicidad();
		serviceCarCDTS.homologarTipTasa();
		serviceCarCDTS.homologarTipPosicion(); //no problem
		System.out.println("LLEGO PENULTIMO");
		serviceCarCDTS.actualizarTmp();
		return "{\"resultado\":\"REALIZADO\"}";
	}
	
	public void convertir() throws IOException, ErrorFtps {
		String rutaArchivoProcesado = null;
		try {
			
			//INFORMACIÓN PARA CONEXIÓN AL SITIO FTPS
			/*String user = "gcelest";
			String pass = "Julio2019";
			String host = "10.86.82.73";
			int port = 1500;*/
			
			ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
						
			//ESTE METODO SE ENCARGA DE LA CONEXION AL SITIO FTPS
			serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());
			//serviceFTP.connectToFTP(host, port, user, pass);
			
			//LA VARIABLE FECHA ACTUAL SE GUARDA LA FECHA ACTUAL DEL SISTEMA
			String fechaActual = serviceFTP.obtenerFechaActual();
			
			//SE CONCATENA LA RUTA CON LA FECHA ACTUAL DONDE SE BUSCARA EL ARCHIVO
			String rutaArchivoOriginal = serviceFTP.rutaEspecifica("%INPUT%",fechaActual);//"/PRUEBA_ACCIONES/"+fechaActual+"/INPUT/";
			
			//SE CREA LA RUTA DONDE SE GUARDARA EL ARCHIVO PROCESADO
			rutaArchivoProcesado = serviceFTP.rutaEspecifica("%CONFIGURATION%",fechaActual);//"/PRUEBA_ACCIONES/"+fechaActual+"/CONFIGURATION/";
			
			//SE OBTIENE EL ARCHIVO QUE INICIE CON LAS PRIMERAS PALABRAS DEL ARCHIVO
			String leerArchivo = serviceFTP.listFile(inicioArchivo);
			System.out.println("EL ARCHIVO A LEER ES: " + leerArchivo);
			
			//SE LE AGREGA PROCESADO AL NOMBRE DEL ARCHIVO 
			String nombreArchivoProcesado = "Procesado_"+leerArchivo;

			//SE TRANSFORMA EL ARCHIVO EN BYTES
			ByteArrayOutputStream resource = serviceFTP.archivoResource(rutaArchivoOriginal+leerArchivo);
			
			//EL ARCHIVO PROCESADO SE GUARDA EN LA RUTA DECLARADA EN LA VARIABLE rutaArchivoProcesado
			serviceFTP.makeFile(resource, rutaArchivoProcesado , nombreArchivoProcesado);
			
			//ESTA VARIABLE SE ENCARGA DE LLENAR EL METODO DEL SERVICIO , EL CUAL LLEVARA LA INFORMACION DEL ARCHIVO PROCESADO
			//AL ITEMREADER
			serviceCarCDTS.almacenarCDT(resource);
			
			//SE DESCONECTA DEL SERVIDOR FTPS
			serviceFTP.disconnectFTP();
		} catch (ErrorFtps ftpErrors) {
			logger.error(ftpErrors.getMessage());
            //System.out.println(ftpErrors.getMessage());
        }
	}
}
