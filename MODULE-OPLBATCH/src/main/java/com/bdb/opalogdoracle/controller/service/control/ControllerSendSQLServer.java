package com.bdb.opalogdoracle.controller.service.control;

import com.bdb.opalogdoracle.controller.service.interfaces.SendDataSQLServeService;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.SalPdcvlEntity;
import lombok.extern.apachecommons.CommonsLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@CommonsLog
public class ControllerSendSQLServer {

	@Autowired
	JobLauncher jobLauncher;
     
	@Autowired
	@Qualifier(value="JobSalPdcvlCDT")
	Job job;
	
	@Autowired
	private SendDataSQLServeService service;
	
	@Autowired
	private SharedService serviceShared;

	@PostMapping(value="sendCDTDeceval",produces = {"application/json"})
	public List<SalPdcvlEntity> sendCDTDeceval(HttpServletRequest http, HttpServletResponse response) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
		boolean conocer = service.verificarTablaArchivoP(verificarTablaArchivoP(http));
		log.info("CONOCER: "+conocer);
		if (conocer){
			service.eliminarTabla();
			service.actulizarEstadoaFinalizado();
		}else{
			eliminarDataTablaArchivoP(http);
		}

		BatchStatus resultado = load_cuentas();
		if (resultado.isUnsuccessful()) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return new ArrayList<>();
		} else{
			response.setStatus(HttpServletResponse.SC_OK);
			return service.listaArchivoP();
		}
	}
	
	@PostMapping(value="updateClientCDTDeceval",produces = {"application/json"})
	public List<SalPdcvlEntity> updateClientCDTDeceval() {
		return service.listaArchivoP();
	}
	
	@PostMapping(value="verificaProcesoDECEVAL")
	public String verificarProceso(HttpServletRequest http) {
		String host = serviceShared.generarUrlBatch(http.getRequestURL().toString());
		final String url = host+"OPLSSQLS/CDTSDesmaterializado/v1/getEstadoCarga";
				//"http://localhost:8081/CDTSDesmaterializado/v1/getEstadoCarga";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, null, new ParameterizedTypeReference<String>(){});
		String resultado = response.getBody();
		return service.verificarProceso(resultado);
	}
	
	public BatchStatus load_cuentas() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
		log.info("ENTRO AL CONTROLLER DE DECEVAL CDTS Desmaterializado");
		
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        log.info("JobExecution: " + jobExecution.getStatus());

        while (jobExecution.isRunning()) {
            log.info("...");
        }
                    
        return jobExecution.getStatus();
    }

	public Long verificarTablaArchivoP(HttpServletRequest http) {
		String host = serviceShared.generarUrlBatch(http.getRequestURL().toString());
		String url = host+"OPLSSQLS/CDTSDesmaterializado/v1/verificarTablaArchivoP";
				//"http://localhost:8081/CDTSDesmaterializado/v1/verificarTablaArchivoP";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Long> response = restTemplate.exchange(url, HttpMethod.POST, null, new ParameterizedTypeReference<Long>() {
		});
		return response.getBody();
	}

	public void eliminarDataTablaArchivoP(HttpServletRequest http){
		String host = serviceShared.generarUrlBatch(http.getRequestURL().toString());
		String url = host+"OPLSSQLS/CDTSDesmaterializado/v1/eliminarDataTablaArchivoP";
				//"http://localhost:8081/CDTSDesmaterializado/v1/eliminarDataTablaArchivoP";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(url);
	}
}
