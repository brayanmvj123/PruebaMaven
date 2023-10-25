package com.bdb.moduleoplcancelaciones.controller.service.implement;

import com.bdb.moduleoplcancelaciones.controller.service.interfaces.CancelCtaContService;
import com.bdb.moduleoplcancelaciones.persistence.response.batch.ResponseBatch;
import com.bdb.moduleoplcancelaciones.persistence.response.batch.ResponseService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
@CommonsLog
public class CancelCtaContServiceImpl implements CancelCtaContService {

    private final JobLauncher jobLauncher;

    private final Job jobCancelCdtCtaCon;

    private final ResponseService responseService;

    public CancelCtaContServiceImpl(JobLauncher jobLauncher,
                               @Qualifier(value = "JobCancelCdtCtaCon") Job jobCancelCdtCtaCon,
                               ResponseService responseService) {
        this.jobLauncher = jobLauncher;
        this.jobCancelCdtCtaCon = jobCancelCdtCtaCon;
        this.responseService = responseService;
    }

    @Override
    public JobExecution runJob(Job job, HttpServletRequest urlRequest) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        maps.put("url", new JobParameter(urlRequest.getRequestURL().toString()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        log.info("JobExecution: " + jobExecution.getStatus());

        while (jobExecution.isRunning()) {
            log.info("...");
        }

        return jobExecution;
    }

    @Override
    public ResponseEntity<ResponseBatch> consumeJobCanCdtCtaCon(HttpServletRequest urlRequest) throws
            JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException {
        log.info("SE PROCEDE A ELIMINAR LOS DATOS EXISTENTES EN LA TABLA.");
//        deleteData();
        log.info("DATOS ELIMINADOS.");
        JobExecution reportPgWeekly = runJob(jobCancelCdtCtaCon, urlRequest);
        return responseService.getResponseJob(reportPgWeekly, "EL CRUCE HA FALLADO", urlRequest);
    }
}
