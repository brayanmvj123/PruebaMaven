package com.bdb.opalogdoracle.controller.service.control;

import com.bdb.opalogdoracle.controller.service.interfaces.*;
import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
public class ControllerParametros {
	
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	@Qualifier(value="JobCIIU")
	Job jobCIIU;

	@Autowired
	@Qualifier(value="JobDepositante")
	Job jobDepositante;

	@Autowired
	@Qualifier(value="JobOficina")
	Job jobOficina;

	@Autowired
	@Qualifier(value="JobDane")
	Job jobDane;

	@Autowired
	@Qualifier(value = "JobSegmento")
	Job jobSegmento;

	@Autowired
	@Qualifier(value="JobPais")
	Job jobPais;
	
	@Autowired
	@Qualifier(value="JobDepartamento")
	Job jobDepartamento;
	
	@Autowired
	@Qualifier(value="JobCiudad")
	Job jobCiudad;
	
	@Autowired
	@Qualifier("serviceFTPS")
	FTPService serviceFTP;

	@Autowired
	@Qualifier("serviceCIIU")
	TipCiiuService serviceCIIU;

	@Autowired
	TipDepositanteService serviceDepositante;

	@Autowired
	TipDaneService serviceDane;

	@Autowired
	OficinaService serviceOficina;

	@Autowired
	TipSegmentoService serviceSegmento;
	
	@Autowired
	@Qualifier("servicePais")
	TipPaisWService servicePais;

	@Autowired
	@Qualifier("serviceDepartamento")
	TipDepWService serviceDepartamento;
	
	@Autowired
	@Qualifier("serviceCiudad")
	TipCiudWService serviceCiudad;

	@Autowired
	SharedService sharedService;
	
	private Logger logger = LoggerFactory.getLogger(ControllerParametros.class);
	
	@GetMapping("Parametro/cargarParametros")
	public BatchStatus destinarJob(HttpServletResponse response) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, ErrorFtps {
		BatchStatus resultado = null ;
		try {

			ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
			serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());
			
			//serviceFTP.connectToFTP(host, port, user, pass);
			String fechaActual = serviceFTP.obtenerFechaActual();
			//"/PRUEBA_ACCIONES/"+fechaActual+"/INPUT/";
			String rutaArchivoOriginal = serviceFTP.rutaEspecifica("%OCASIONAL%",fechaActual);
			//la.verificarDirectorio();

			List<String> nombres = serviceFTP.getNameParameters();

			//System.out.println("NOMBRE: " + nombres.get(0));
			for(String nombreArchivo : nombres) {
				ByteArrayOutputStream resource = null;
				switch (nombreArchivo) {
				case "OPL_PAR_CODIGOS_DANE":
					resource = serviceFTP.archivoResource(rutaArchivoOriginal+nombreArchivo+".csv");
					serviceDane.guardarDane(resource);
					resultado = load(jobDane);
					break;
				case "OPL_PAR_CODIGOS_CIIU":
					resource = serviceFTP.archivoResource(rutaArchivoOriginal+nombreArchivo+".csv");
					serviceCIIU.guardarCIIU(resource);
					resultado = load(jobCIIU);
					break;
				case "OPL_PAR_CODIGOS_DEPOSITANTES":
					resource = serviceFTP.archivoResource(rutaArchivoOriginal+nombreArchivo+".csv");
					serviceDepositante.guardarDepositante(resource);
					resultado = load(jobDepositante);
					break;
				case "OPL_PAR_OFICINAS":
					resource = serviceFTP.archivoResource(rutaArchivoOriginal+nombreArchivo+".csv");
					serviceOficina.guardarOficina(resource);
					resultado = load(jobOficina);
					break;
				case "OPL_PAR_SEGMENTOS":
					resource = serviceFTP.archivoResource(rutaArchivoOriginal+nombreArchivo+".csv");
					serviceSegmento.guardarSegmento(resource);
					resultado = load(jobSegmento);
					break;
				case "OPL_PAR_PAIS":
					resource = serviceFTP.archivoResource(rutaArchivoOriginal+nombreArchivo+".csv");
					servicePais.guardarPais(resource);
					resultado = load(jobPais);
					break;
				case "OPL_PAR_DEPARTAMENTOS":
					resource = serviceFTP.archivoResource(rutaArchivoOriginal+nombreArchivo+".csv");
					serviceDepartamento.guardarDepartamento(resource);
					resultado = load(jobDepartamento);
					break;
				case "OPL_PAR_CIUDAD":
					resource = serviceFTP.archivoResource(rutaArchivoOriginal+nombreArchivo+".csv");
					serviceCiudad.guardarCiudad(resource);
					resultado = load(jobCiudad);
					break;
				default:
					logger.warn("NINGUN ARCHIVO COINCIDE CON ALGUNA TABLA");
					break;
				}
			}
			serviceFTP.disconnectFTP();
		} catch (ErrorFtps ftpErrors) {
			logger.error(ftpErrors.getMessage());
			//System.out.println(ftpErrors.getMessage());
		}

		if (resultado != null){
			System.out.println(resultado.getBatchStatus().toString());

			if(resultado.getBatchStatus().toString().equals("FAILED")){
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			return resultado;
		}else {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return null;
		}
	}
	
	public BatchStatus load(Job job) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
				
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        System.out.println("JobExecution: " + jobExecution.getStatus());
        logger.info("JobExecution: " + jobExecution.getStatus());

        System.out.println("Parametros Job is Running...");
        while (jobExecution.isRunning()) {
            System.out.println("...");
        }
   
        return jobExecution.getStatus();
    }
	
	@GetMapping(value="Directorio/crearDirectorios")
	public String crearDirectorios(HttpServletResponse response) {
		try {
			ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
			serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());
			if (serviceFTP.verificarCarpetaDiaria(parameters.getRuta())) {
				serviceFTP.makeDirectoryDay(parameters.getRuta());
				serviceFTP.makeSubDirectorys();
				serviceFTP.disconnectFTP();
				response.setStatus(HttpServletResponse.SC_CREATED);
				return HttpStatus.CREATED.toString();
			}else{
				serviceFTP.disconnectFTP();
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				return HttpStatus.NO_CONTENT.toString();
			}
		} catch (ErrorFtps | IOException ftpErrors) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			logger.error(ftpErrors.getMessage());
			return HttpStatus.INTERNAL_SERVER_ERROR.toString();
		}
	}
}