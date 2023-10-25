package com.bdb.moduleoplcancelaciones.controller.service.interfaces;

import com.bdb.moduleoplcancelaciones.persistence.response.batch.ResponseBatch;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface CancelCtaContService {

    JobExecution runJob(Job job, HttpServletRequest urlRequest) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException;

    ResponseEntity<ResponseBatch> consumeJobCanCdtCtaCon(HttpServletRequest urlRequest) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException;

}
