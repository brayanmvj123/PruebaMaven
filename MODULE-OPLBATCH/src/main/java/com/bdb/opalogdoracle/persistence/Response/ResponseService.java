package com.bdb.opalogdoracle.persistence.Response;

import org.springframework.batch.core.StepExecution;

import java.util.Collection;

public interface ResponseService {

    ResponseBatch resultJob(String status, String requestUrl, String jobId, String resultJob, String possibleMistake , Collection<StepExecution> stepExecution);

}
