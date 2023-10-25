package com.bdb.moduleoplcancelaciones.persistence.response.batch;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collection;

@Service
@CommonsLog
public class ResponseServiceImpl implements ResponseService {

    @Override
    public ResponseBatch resultJob(String status, String requestUrl, String jobId, String resultJob, String messagge, Collection<StepExecution> step) {
        final String[] possibleMistake = new String[1];
        if (step != null) {
            step.stream().filter(stepExecution -> !stepExecution.getFailureExceptions().isEmpty()).
                    forEach(stepExecution -> possibleMistake[0] = stepExecution.getFailureExceptions().get(0).getLocalizedMessage());
        }
        messagge = messagge != null ? messagge : "N/A";
        return new ResponseBatch(LocalDateTime.now().toString(),
                status,
                requestUrl,
                jobId,
                resultJob,
                possibleMistake[0] != null ? possibleMistake[0] : messagge);
    }

    public ResponseEntity<ResponseBatch> getResponseJob(JobExecution jobExecution, String message, HttpServletRequest urlRequest) {

        if (jobExecution.getStatus().isUnsuccessful()) {
            log.error("OCURRIO UN ERROR :(");
            jobExecution.getStepExecutions().forEach(stepExecution -> log.error(stepExecution.getExitStatus().getExitDescription()));
            return new ResponseEntity<>(responseBatch(jobExecution, urlRequest, message),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(responseBatch(jobExecution, urlRequest, null),
                    validateStatus(jobExecution));
        }

    }

    public ResponseBatch responseBatch(JobExecution jobExecution, HttpServletRequest urlRequest, String message) {
        return resultJob(validateStatus(jobExecution).toString(),
                urlRequest.getRequestURL().toString(), jobExecution.getJobId().toString(),
                jobExecution.getStatus().toString(), message, jobExecution.getStepExecutions());
    }

    public HttpStatus validateStatus(JobExecution jobExecution) {
        return jobExecution.getStatus().isUnsuccessful() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK;
    }

}
