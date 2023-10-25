package com.bdb.opalogdoracle.controller.service.control;

import com.bdb.opalogdoracle.controller.service.interfaces.CarMaeCDTSService;
import com.bdb.opalogdoracle.persistence.Response.ResponseBatch;
import com.bdb.opalogdoracle.persistence.Response.ResponseService;
import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

	@Autowired
	ResponseService responseService;

	JobParameters parameters;
	JobExecution jobExecution;
	HttpStatus httpStatus;

	private Logger logger = LoggerFactory.getLogger(ControllerCarMaeCDTS.class);
	
	@GetMapping("cargarMaestroDeceval")
	public ResponseEntity<ResponseBatch> loadInformationDeceval(HttpServletResponse response , HttpServletRequest httpServletRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, IOException, ErrorFtps {
		logger.info("SE EMPIEZA A EJECUTAR EL CONTROLLER DE CARGA DE LOS MAESTROS ENVIADOS POR DECEVAL");
		
		serviceCarCDTS.eliminarInformacion();
		logger.info("SE ELIMINÓ LA DATA DE LA TABLA OPL_CAR_MAECDTS_DOWN_TBL");

		if (convertir()) {
			logger.info("SE ENCONTRO EL ARCHIVO PARA SER PROCESADO :)");

			Map<String, JobParameter> maps = new HashMap<>();
			maps.put("time", new JobParameter(System.currentTimeMillis()));
			parameters = new JobParameters(maps);
			jobExecution = jobLauncher.run(job, parameters);

			logger.info("JobExecution: " + jobExecution.getStatus());

			while (jobExecution.isRunning()) {
				System.out.println("...");
			}

			logger.info(jobExecution.getStatus().getBatchStatus().toString());

			httpStatus = HttpStatus.OK;
			if (jobExecution.getStatus().getBatchStatus().toString().equals("FAILED")) {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			}

			serviceCarCDTS.verificacionNumCDTDigital();

			jobExecution.getStepExecutions().forEach(stepExecution -> logger.error(stepExecution.getExitStatus().getExitDescription()));

			ResponseBatch responseEntity = responseService.resultJob(httpStatus.toString(), httpServletRequest.getRequestURL().toString(),
					jobExecution.getJobId().toString() , jobExecution.getStatus().toString() , null , jobExecution.getStepExecutions());
			return new ResponseEntity<>(responseEntity,httpStatus);
		}else{
			logger.info("NO SE ENCONTRO EL ARCHIVO :(");
			ResponseBatch responseEntity = responseService.resultJob(HttpStatus.INTERNAL_SERVER_ERROR.toString(), httpServletRequest.getRequestURL().toString(),
					"No se alcanzo a ejecutar el Job" , "N/A" , "El archivo no se encontro en la ruta indicada" , null);
			return new ResponseEntity<>(responseEntity,HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	@PostMapping("actualizarTempMaeDcv")
	public String actualizarTempMaeDcv() {
		serviceCarCDTS.eliminarTmpMaeDcv();
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
	
	public boolean convertir() throws IOException, ErrorFtps {
		String rutaArchivoProcesado = null;
		String inicioArchivo =  serviceCarCDTS.conocerNombreArchivoCarga(); //"MAECDTDCV";
		try {

			ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
						
			//ESTE METODO SE ENCARGA DE LA CONEXION AL SITIO FTPS
			serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());

			serviceFTP.makeDirectoryDay(parameters.getRuta());
			serviceFTP.makeSubDirectorys();

			//LA VARIABLE FECHA ACTUAL SE GUARDA LA FECHA ACTUAL DEL SISTEMA
			String fechaActual = serviceFTP.obtenerFechaActual();
			
			//SE CONCATENA LA RUTA CON LA FECHA ACTUAL DONDE SE BUSCARA EL ARCHIVO
			String rutaArchivoOriginal = serviceFTP.rutaEspecifica("%INPUT%",fechaActual);//"/PRUEBA_ACCIONES/"+fechaActual+"/INPUT/";
			
			//SE CREA LA RUTA DONDE SE GUARDARA EL ARCHIVO PROCESADO
			rutaArchivoProcesado = serviceFTP.rutaEspecifica("%CONFIGURATION%",fechaActual);//"/PRUEBA_ACCIONES/"+fechaActual+"/CONFIGURATION/";
			
			//SE OBTIENE EL ARCHIVO QUE INICIE CON LAS PRIMERAS PALABRAS DEL ARCHIVO
			String leerArchivo = serviceFTP.listFile(inicioArchivo);
			if (leerArchivo != null) {
				System.out.println("EL ARCHIVO A LEER ES: " + leerArchivo);

				//SE LE AGREGA PROCESADO AL NOMBRE DEL ARCHIVO
				String nombreArchivoProcesado = "Procesado_" + leerArchivo;

				//SE TRANSFORMA EL ARCHIVO EN BYTES
				ByteArrayOutputStream resource = serviceFTP.archivoResource(rutaArchivoOriginal + leerArchivo);

				//EL ARCHIVO PROCESADO SE GUARDA EN LA RUTA DECLARADA EN LA VARIABLE rutaArchivoProcesado
				serviceFTP.makeFile(resource, rutaArchivoProcesado, nombreArchivoProcesado);

				//ESTA VARIABLE SE ENCARGA DE LLENAR EL METODO DEL SERVICIO , EL CUAL LLEVARA LA INFORMACION DEL ARCHIVO PROCESADO
				//AL ITEMREADER
				serviceCarCDTS.almacenarCDT(resource);

				//SE DESCONECTA DEL SERVIDOR FTPS
				serviceFTP.disconnectFTP();
				return true;
			}else{
				serviceFTP.disconnectFTP();
				return false;
			}
		} catch (ErrorFtps ftpErrors) {
			logger.error(ftpErrors.getMessage());
			return false;
            //System.out.println(ftpErrors.getMessage());
        }
	}
}
