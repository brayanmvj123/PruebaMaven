package com.bdb.oplbatchmensual.Schudeler.load;

import com.bdb.oplbatchmensual.controller.service.interfaces.CalendarioOpaloService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

@CommonsLog
public class CalendarioOpaloTasklet  implements Tasklet, StepExecutionListener {

    @Autowired
    CalendarioOpaloService calendarioOpaloService;

    @Override
    public void beforeStep(StepExecution stepExecution) {
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("ESTADO: "+stepExecution.getStatus().isUnsuccessful());
        if (stepExecution.getStatus().isUnsuccessful())
            return ExitStatus.FAILED;
        else
            return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("inicio tasklet");
        calendarioOpaloService.migrarCalendarioOpalo(chunkContext.getStepContext().getJobParameters().get("url").toString());
        return RepeatStatus.FINISHED;
    }
}