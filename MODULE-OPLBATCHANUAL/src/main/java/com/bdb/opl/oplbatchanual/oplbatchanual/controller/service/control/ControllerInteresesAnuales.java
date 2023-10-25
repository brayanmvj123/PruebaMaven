package com.bdb.opl.oplbatchanual.oplbatchanual.controller.service.control;

import com.bdb.opl.oplbatchanual.oplbatchanual.controller.service.interfaces.InterAnuService;
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
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
public class ControllerInteresesAnuales {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value="JobReportAnualInterests")
    Job job;

    @Autowired
    InterAnuService interAnuService;

    private Logger logger = LoggerFactory.getLogger(ControllerInteresesAnuales.class);

    @GetMapping("reporte/interesesGeneradosAnuales")
    public BatchStatus interesesGeneradosAnuales(HttpServletResponse response) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        logger.info("SE PROCEDE A GENERAR LA DATA DE LOS INTERESES EFECTUADOS ANUALMENTE");

        interAnuService.eliminarRegistrosSalida();

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
