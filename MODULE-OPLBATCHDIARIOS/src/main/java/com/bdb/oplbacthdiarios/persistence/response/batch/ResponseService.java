package com.bdb.oplbacthdiarios.persistence.response.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

public interface ResponseService {

    ResponseEntity<ResponseBatch> getResponseJob(JobExecution jobExecution, String message, HttpServletRequest urlRequest);

    ResponseBatch resultJob(String status, String requestUrl, String jobId, String resultJob, String possibleMistake, Collection<StepExecution> stepExecution);

}
