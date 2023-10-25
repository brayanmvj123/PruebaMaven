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
public class ControllerEnvioCorreoReporteHisLoginUser {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value="JobEnvioCorreoReporteHisLoginUser")
    Job job;

    private Logger logger = LoggerFactory.getLogger(ControllerEnvioCorreoReporteHisLoginUser.class);

    @GetMapping("envioReporteHisLogin")
    public BatchStatus loadenvioReporteHisLogin(HttpServletResponse response) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, IOException, ErrorFtps {
        logger.info("SE INICIA EL PROCESO DE ENVIO REPORTE USUARIOS DE HIS LOGIN");

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        logger.info("JobExecution: " + jobExecution.getStatus());

        while (jobExecution.isRunning()) {
            System.out.println("...");
        }

        System.out.println(jobExecution.getStatus().getBatchStatus().toString());

        if(jobExecution.getStatus().getBatchStatus().toString().equals("FAILED")){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        return jobExecution.getStatus();
    }
}
