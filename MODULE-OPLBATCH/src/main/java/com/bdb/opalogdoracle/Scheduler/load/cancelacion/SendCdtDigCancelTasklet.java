package com.bdb.opalogdoracle.Scheduler.load.cancelacion;

import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@CommonsLog
public class SendCdtDigCancelTasklet implements Tasklet, StepExecutionListener {

    private final SharedService serviceShared;

    public SendCdtDigCancelTasklet(SharedService serviceShared){
        this.serviceShared = serviceShared;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("Se inicia el consumo de la tabla SAL_PG la cual contiene los CDTs Digitales que se enviaran a CANCELAR.");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        if (stepExecution.getStatus().isUnsuccessful())
            return ExitStatus.FAILED;
        else
            return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        log.info("Inicia el envio de CDTs cancelados a Deceval BTA.");

        String host = serviceShared.generarUrl(chunkContext
                .getStepContext()
                .getJobParameters()
                .get("url")
                .toString(), "OPLBATCH");

        final String url = host + "OPLSSQLS/CDTSDesmaterializado/v1/cancelacion/cdtsdig";
        //"http://localhost:8080/CDTSDesmaterializado/v1/cancelacion/cdtsdig";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.POST,
                null,
                new ParameterizedTypeReference<String>() {
                });

        log.info("Resultado: "+response.getBody());
        Optional<String> resultado = Optional.ofNullable(response.getBody());
        if (response.getStatusCode().is2xxSuccessful() && resultado.filter(item -> item.equals("Cancelaciones exitosas")).isPresent())
            chunkContext.getStepContext().getStepExecution().setExitStatus(ExitStatus.COMPLETED);
        else
            chunkContext.getStepContext().getStepExecution().setExitStatus(ExitStatus.FAILED);

        return RepeatStatus.FINISHED;
    }

}
