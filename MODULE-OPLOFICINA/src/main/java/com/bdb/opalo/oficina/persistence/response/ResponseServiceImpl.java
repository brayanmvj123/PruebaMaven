package com.bdb.opalo.oficina.persistence.response;

import org.springframework.batch.core.StepExecution;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class ResponseServiceImpl implements ResponseService{

    @Override
    public ResponseBatch resultJob(String status, String requestUrl, String jobId, String resultJob, String messagge , Collection<StepExecution> step) {
        final String[] possibleMistake = new String[1];
        if(step != null) {
            step.stream().filter(stepExecution -> !stepExecution.getFailureExceptions().isEmpty()).
                    forEach(stepExecution -> possibleMistake[0] = stepExecution.getFailureExceptions().get(0).getLocalizedMessage());
        }
        messagge = messagge != null ? messagge : "N/A";
        return new ResponseBatch(LocalDateTime.now().toString(),status,requestUrl,jobId,resultJob,possibleMistake[0] != null ? possibleMistake[0] : messagge);
    }

}
