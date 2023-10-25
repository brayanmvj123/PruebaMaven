package com.bdb.opalogdoracle.controller.service.control;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
public class ControllerBloquearUsuario {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value = "JobBloquearUser")
    Job job;

    @Autowired
    @Qualifier(value = "JobSendEmail")
    Job job2;

    private Logger logger = LoggerFactory.getLogger(ControllerBloquearUsuario.class);

    @GetMapping("bloquearUsuario")
    public BatchStatus loadBloquearUsuario(HttpServletResponse response) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, IOException, ErrorFtps {
        logger.info("SE INICIA EL PROCESO DE BLOQUEO DE USUARIO ");

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job2, parameters);
        logger.info("JobExecution: " + jobExecution.getStatus());

        //System.out.println("Cuentas Job is Running...");
        while (jobExecution.isRunning()) {
            System.out.println("...");
        }

        System.out.println(jobExecution.getStatus().getBatchStatus().toString());

        if (jobExecution.getStatus().getBatchStatus().toString().equals("COMPLETED") ||
                jobExecution.getStatus().getBatchStatus().toString().equals("FAILED")) {
            JobExecution jobExecution2 = jobLauncher.run(job, parameters);
            while (jobExecution2.isRunning()) {
                System.out.println("...");
            }

            System.out.println(jobExecution2.getStatus().getBatchStatus().toString());
            if (jobExecution2.getStatus().getBatchStatus().toString().equals("FAILED")) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            return jobExecution2.getStatus();
        }

        return jobExecution.getStatus();
    }

}
