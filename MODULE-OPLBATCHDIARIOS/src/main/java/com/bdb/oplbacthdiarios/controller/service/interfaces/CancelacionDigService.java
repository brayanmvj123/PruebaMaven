package com.bdb.oplbacthdiarios.controller.service.interfaces;

import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseBatch;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface CancelacionDigService {

    ResponseEntity<ResponseBatch> cancelacionAutDig(HttpServletRequest urlRequest)
            throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, JsonProcessingException;

}
