package com.bdb.oplbatchmensual.controller.service.control;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.oplbatchmensual.controller.service.interfaces.InteresesMensualesService;
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
public class ControllerInteresesMensuales {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value="JobReportInterests")
    Job job;

    @Autowired
    InteresesMensualesService interesesMensualesService;

    private final Logger logger = LoggerFactory.getLogger(ControllerInteresesMensuales.class);

    @GetMapping("reporte/interesesGeneradosMensuales")
    public BatchStatus interesesGeneradosMensuales(HttpServletResponse response) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, IOException, ErrorFtps {
        logger.info("SE PROCEDE A GENERAR LA DATA DE LOS INTERESES EFECTUADOS MENSUALES");

        interesesMensualesService.eliminarRegistrosSalida();

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        logger.info("JobExecution: {}" , jobExecution.getStatus());

        while (jobExecution.isRunning()) {
            logger.info("...");
        }

        logger.info("STATUS JOB: {}", jobExecution.getStatus().getBatchStatus());

        interesesMensualesService.verificarGeneracionArchivo();

        if(jobExecution.getStatus().getBatchStatus().toString().equals("FAILED")){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return jobExecution.getStatus();
    }

}
