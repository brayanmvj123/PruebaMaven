package com.bdb.oplbacthdiarios.controller.service.implement;

import com.bdb.oplbacthdiarios.controller.service.interfaces.CdtDiarioTesoreriaService;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseBatch;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseService;
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
public class CdtDiarioTesoreriaServiceImpl implements CdtDiarioTesoreriaService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    final JobLauncher jobLauncher;
    final Job job;
    final Job jobRen;
    final Job jobCan;
    final ResponseService responseService;

    public CdtDiarioTesoreriaServiceImpl(JobLauncher jobLauncher, @Qualifier(value = "JobCdtDiarioTesoreria") Job job,
                                         @Qualifier(value = "JobCdtDiarioTesoreriaRenovado") Job jobRen,
                                         @Qualifier(value = "JobCdtDiarioTesoreriaCancelado") Job jobCan,
                                         ResponseService responseService) {
        this.jobLauncher = jobLauncher;
        this.job = job;
        this.jobRen = jobRen;
        this.jobCan = jobCan;
        this.responseService = responseService;
    }

    @Override
    public ResponseEntity<ResponseBatch> cdtDiarioTesoreria(HttpServletRequest request) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        logger.info("inicio job CdtDiarioTesoreria...");

        JobExecution jobExecution = getJobExecution(job, "JobCdtDiarioTesoreria Job is Running...");

        return responseService.getResponseJob(jobExecution, "CdtDiarioTesoreria Exitoso", request);

    }

    /*public ResponseEntity<ResponseBatch> getResponseJob(JobExecution jobExecution, String s, HttpServletRequest urlRequest) {

        if (jobExecution.getStatus().isUnsuccessful()) {
            logger.error("OCURRIO UN ERROR :(");
            jobExecution.getStepExecutions().forEach(stepExecution -> logger.error(stepExecution.getExitStatus().getExitDescription()));
            ResponseBatch responseEntity = responseService.resultJob(HttpStatus.INTERNAL_SERVER_ERROR.toString(), urlRequest.getRequestURL().toString(),
                    jobExecution.getJobId().toString(), "N/A", "SE PRESENTO FALLO AL GENERAR EL REPORTE", null);
            return new ResponseEntity<>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(responseBatch(jobExecution, urlRequest), validateStatus(jobExecution));
        }

    }

    public ResponseBatch responseBatch(JobExecution jobExecution, HttpServletRequest urlRequest) {
        return responseService.resultJob(validateStatus(jobExecution).toString(),
                urlRequest.getRequestURL().toString(), jobExecution.getJobId().toString(),
                jobExecution.getStatus().toString(), null, jobExecution.getStepExecutions());
    }

    public HttpStatus validateStatus(JobExecution jobExecution) {
        return jobExecution.getStatus().isUnsuccessful() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK;
    }*/

    public JobExecution getJobExecution(Job job, String message) throws JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, JobParametersInvalidException {

        logger.info(message);

        JobParameters parameters = new JobParametersBuilder()
                .addDate("date", new Date())
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

        logger.info("inicio job CdtDiarioTesoreriaRenovado...");

        JobExecution jobExecution = getJobExecution(jobRen, "CdtDiarioTesoreriaRenovado Job is Running...");

        return responseService.getResponseJob(jobExecution, "CdtDiarioTesoreriaRenovado Exitoso", request);

    }

    @Override
    public ResponseEntity<ResponseBatch> cdtDiarioTesoreriaCancelado(HttpServletRequest request) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        logger.info("inicio job CdtDiarioTesoreriaCancelado...");

        JobExecution jobExecution = getJobExecution(jobCan, "CdtDiarioTesoreriaCancelado Job is Running...");

        return responseService.getResponseJob(jobExecution, "CdtDiarioTesoreriaCancelado Exitoso", request);


    }

}
