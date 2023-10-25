package com.bdb.opalogdoracle.controller.service.control;

import com.bdb.opalogdoracle.controller.service.interfaces.ArchivoTraductorService;
import com.bdb.opalogdoracle.controller.service.interfaces.CarMaeCDTSService;
import com.bdb.opalogdoracle.persistence.Response.ResponseBatch;
import com.bdb.opalogdoracle.persistence.Response.ResponseService;
import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.entity.SalPdcvlEntity;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
public class ControllerArchivoTraductor {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value = "JobArchivoTraductor")
    Job job;

    @Autowired
    @Qualifier(value = "JobArchivoTraductorContigencia")
    Job jobContigencia;

    @Autowired
    @Qualifier(value = "JobArchivoTramasDiferDeceapdi")
    Job jobTramasDiferDeceapdi;

    @Autowired
    ArchivoTraductorService serviceArchTraduc;

    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;

    @Autowired
    private SharedService serviceShared;

    @Autowired
    private ResponseService responseService;

    HttpStatus httpStatus;

    private Logger logger = LoggerFactory.getLogger(ControllerArchivoTraductor.class);

    @GetMapping(value = "generarArchivoTraductor",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ResponseBatch> generarArchivoTramas(HttpServletResponse response , HttpServletRequest http) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, ErrorFtps, JobRestartException, IOException, NoSuchAlgorithmException, KeyManagementException {
        JobExecution batchStatus;
        JobExecution batchStatusSecundario;
        boolean conocer = serviceArchTraduc.verificarTablaArchivoP(verificarTablaArchivoP(http));
        if (conocer) {
            logger.info("SE REALIZA LA VALIDACION EN LA BASE DE DATOS DE DECEVAL BTA Y LA TABLA SE ENCUENTRA VACIA");
            batchStatus = generarArchivoTraductor(job);
            verificarGeneracionArchivo();
            httpStatus = HttpStatus.OK;
            if (batchStatus.getStatus().equals(BatchStatus.FAILED)) {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            batchStatus.getStepExecutions().forEach(stepExecution -> logger.error(stepExecution.getExitStatus().getExitDescription()));

            ResponseBatch responseEntity = responseService.resultJob(httpStatus.toString(), http.getRequestURL().toString(),
                    batchStatus.getJobId().toString(), batchStatus.getStatus().toString(), null, batchStatus.getStepExecutions());
            return new ResponseEntity<>(responseEntity, httpStatus);
        }else{
            logger.info("SE REALIZA LA VALIDACION EN LA BASE DE DATOS DE DECEVAL BTA Y LA TABLA NO SE ENCUENTRA VACIA, " +
                    "SE EMPLEA LA CREACIÓN DEL ARCHIVO DE CONTINGENCIA DEL TRADUCTOR CONTABLE");
            batchStatus = generarArchivoTraductor(jobContigencia);
            serviceArchTraduc.eliminarTramasDeceapdi();
            batchStatusSecundario = generarArchivoTraductor(jobTramasDiferDeceapdi);
            System.out.println("STATUS BATCH: "+batchStatus+" STATUS BATCHSECUNDARIO: "+batchStatusSecundario);
            verificarGeneracionArchivo();
            httpStatus = HttpStatus.OK;
            if (batchStatus.getStatus().equals(BatchStatus.FAILED) || batchStatusSecundario.getStatus().equals(BatchStatus.FAILED)) {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            batchStatus.getStepExecutions().forEach(stepExecution -> logger.error(stepExecution.getExitStatus().getExitDescription()));

            ResponseBatch responseEntity = responseService.resultJob(httpStatus.toString(), http.getRequestURL().toString(),
                    batchStatus.getJobId().toString(), batchStatus.getStatus().toString(), null, batchStatus.getStepExecutions());
            return new ResponseEntity<>(responseEntity, httpStatus);
        }
    }

    public JobExecution generarArchivoTraductor(Job job) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, IOException, ErrorFtps {
        System.out.println("ENTRO AL CONTROLLER DE ARCHIVO TRADUCTOR");

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        //System.out.println("JobExecution: " + jobExecution.getStatus());
        logger.info("JobExecution: " + jobExecution.getStatus());

        while (jobExecution.isRunning()) {
            System.out.println("...");
        }

        System.out.println(jobExecution.getStatus().getBatchStatus().toString());

        return jobExecution;
    }

    public void verificarGeneracionArchivo() throws IOException, ErrorFtps, KeyManagementException, NoSuchAlgorithmException {
        ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
        serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());
        serviceFTP.makeDirectoryDay(parameters.getRuta());
        serviceFTP.makeSubDirectorys();
        serviceArchTraduc.verificaGeneracionrArchivo();
        serviceFTP.disconnectFTP();
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
}
