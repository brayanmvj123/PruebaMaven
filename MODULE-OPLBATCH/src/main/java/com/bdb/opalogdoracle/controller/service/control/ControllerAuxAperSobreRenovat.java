package com.bdb.opalogdoracle.controller.service.control;

import com.bdb.opalogdoracle.controller.service.interfaces.ApersobreRenautService;
import com.bdb.opalogdoracle.persistence.Response.ResponseBatch;
import com.bdb.opalogdoracle.persistence.Response.ResponseService;
import lombok.extern.apachecommons.CommonsLog;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("CDTSDesmaterializado/v1/serviceAux/")
@CommonsLog
public class ControllerAuxAperSobreRenovat {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value="JobAperIntoRenaut")
    Job job;

    @Autowired
    ResponseService responseService;

    @Autowired
    ApersobreRenautService apersobreRenautService;

    HttpStatus httpStatus;

    @GetMapping(value = "renovacion/cdtsdigitales")
    public ResponseEntity<ResponseBatch> apeturaSobreRenovacion(HttpServletRequest http) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        maps.put("url", new JobParameter(http.getRequestURL().toString()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        log.info("JobExecution: " + jobExecution.getStatus());

        while (jobExecution.isRunning()) {
            log.info("...");
        }

        log.info("RESULTADO DE LA EJECUCIÓN: "+jobExecution.getStatus().getBatchStatus().toString());

        httpStatus = apersobreRenautService.getCdtsRenaut().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        if (jobExecution.getStatus().isUnsuccessful()) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            log.error("OCURRIO UN ERROR :(");
            ResponseBatch responseEntity = responseService.resultJob(HttpStatus.INTERNAL_SERVER_ERROR.toString(), http.getRequestURL().toString(),
                    "No se alcanzo a ejecutar el Job" , "N/A" , "La renovación ha FALLADO" , null);
            return new ResponseEntity<>(responseEntity,HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            jobExecution.getStepExecutions().forEach(stepExecution -> log.error(stepExecution.getExitStatus().getExitDescription()));
            ResponseBatch responseEntity = responseService.resultJob(httpStatus.toString(), http.getRequestURL().toString(),
                    jobExecution.getJobId().toString() , jobExecution.getStatus().toString() , null , jobExecution.getStepExecutions());
            return new ResponseEntity<>(responseEntity,httpStatus);
        }
    }
}
