package com.bdb.oplbacthsemanal.controller.service.implement;

import com.bdb.oplbacthsemanal.controller.service.interfaces.CdtTesoreriaSemanalService;
import com.bdb.oplbacthsemanal.persistence.model.response.ResponseBatch;
import com.bdb.oplbacthsemanal.persistence.model.response.ResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class CdtTesoreriaSemanalServiceImpl implements CdtTesoreriaSemanalService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    final JobLauncher jobLauncher;

    final Job job;

    final Job jobRen;

    final Job jobCan;

    final ResponseService responseService;

    public CdtTesoreriaSemanalServiceImpl(JobLauncher jobLauncher, @Qualifier(value = "JobCdtSemanalTesoreria") Job job,
                                          @Qualifier(value = "JobCdtSemanalTesoreriaRenovados") Job jobRen,
                                          @Qualifier(value = "JobCdtSemanalTesoreriaCancelados") Job jobCan,
                                          ResponseService responseService) {
        this.jobLauncher = jobLauncher;
        this.job = job;
        this.jobRen = jobRen;
        this.jobCan = jobCan;
        this.responseService = responseService;
    }

    @Override
    public ResponseEntity<ResponseBatch> cdtSemanalTesoreria(HttpServletRequest request) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        logger.info("inicio job CdtSemanalTesoreria...");
        JobExecution jobExecution = runJob(this.job);
        return responseService.getResponseJob(jobExecution, "inicio job CdtSemanalTesoreria...", request);

    }

    public JobExecution runJob(Job job) throws JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, JobParametersInvalidException {

        JobParameters parameters = new JobParametersBuilder().addDate("date", new Date())
                .addLong("time", System.currentTimeMillis()).toJobParameters();

        logger.info("joblauncher Run...");
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        logger.info("JobExecution: {}", jobExecution.getStatus());

        while (jobExecution.isRunning()) {
            logger.info("...");
        }

        return jobExecution;
    }

    @Override
    public ResponseEntity<ResponseBatch> cdtDiarioTesoreriaRenovado(HttpServletRequest request) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        logger.info("inicio job CdtSemanalTesoreriaRenovado...");
        JobExecution jobExecution = runJob(this.jobRen);
        return responseService.getResponseJob(jobExecution, "inicio job CdtSemanalTesoreriaRenovado...", request);

    }

    @Override
    public ResponseEntity<ResponseBatch> cdtDiarioTesoreriaCancelado(HttpServletRequest request) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        logger.info("inicio job CdtSemanalTesoreriaCancelado...");
        JobExecution jobExecution = runJob(this.jobCan);
        return responseService.getResponseJob(jobExecution, "inicio job CdtSemanalTesoreriaCancelado...", request);

    }
}
