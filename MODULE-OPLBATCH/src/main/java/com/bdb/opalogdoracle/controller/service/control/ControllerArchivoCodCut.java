package com.bdb.opalogdoracle.controller.service.control;

import com.bdb.opalogdoracle.controller.service.interfaces.ArchivoCodigoCutService;
import com.bdb.opalogdoracle.persistence.Response.ResponseBatch;
import com.bdb.opalogdoracle.persistence.Response.ResponseService;
import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
public class ControllerArchivoCodCut {

    @Autowired
    private ArchivoCodigoCutService serviceCodCut;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value = "JobArchivoCut")
    Job job;

    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;

    @Autowired
    private ResponseService responseService;

    HttpStatus httpStatus;

    private Logger logger = LoggerFactory.getLogger(ControllerArchivoCodCut.class);

    @GetMapping(value = "generarCodCut" , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ResponseBatch> generarCodCut(HttpServletResponse response, HttpServletRequest httpServletRequest) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, ErrorFtps, JobRestartException, IOException {
        serviceCodCut.limpiarTabla();
        serviceCodCut.generarInformacionCut();
        JobExecution resultado = crearArchivoCut();

        /*logger.info("JOB_STATUS: " + resultado.getStatus());
        logger.info("JOB_ID: " + resultado.getJobId());
        resultado.getStepExecutions().forEach(stepExecution -> logger.info("STEP: " + stepExecution.getStepName()));*/

        httpStatus = HttpStatus.OK;
        if (resultado.getStatus().getBatchStatus().toString().equals("FAILED")) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        verificarGeneracionArchivo();

        resultado.getStepExecutions().forEach(stepExecution -> logger.error(stepExecution.getExitStatus().getExitDescription()));

        ResponseBatch responseEntity = responseService.resultJob(httpStatus.toString(), httpServletRequest.getRequestURL().toString(),
                resultado.getJobId().toString(), resultado.getStatus().toString(), null, resultado.getStepExecutions());
        return new ResponseEntity<>(responseEntity, httpStatus);

    }

    public JobExecution crearArchivoCut() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, IOException, ErrorFtps {
        System.out.println("ENTRO AL CONTROLLER DE CREAR ARCHIVO CUT");

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        logger.info("JobExecution: " + jobExecution.getStatus());

        while (jobExecution.isRunning()) {
            System.out.println("...");
        }

        return jobExecution;//.getStatus();
    }

    public void verificarGeneracionArchivo() throws IOException, ErrorFtps {

        ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
        serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());

        serviceFTP.makeDirectoryDay(parameters.getRuta());
        serviceFTP.makeSubDirectorys();

        serviceCodCut.verificaGeneracionrArchivo();

        serviceFTP.disconnectFTP();

    }

}
