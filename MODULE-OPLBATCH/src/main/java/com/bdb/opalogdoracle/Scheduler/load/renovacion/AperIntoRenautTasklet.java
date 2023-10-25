package com.bdb.opalogdoracle.Scheduler.load.renovacion;

import com.bdb.opalogdoracle.controller.service.interfaces.ApersobreRenautService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class AperIntoRenautTasklet implements Tasklet, StepExecutionListener {

    @Autowired
    private ApersobreRenautService apersobreRenautService;

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        if(apersobreRenautService.aperIntoRenaut(chunkContext.getStepContext().getJobParameters().get("url").toString()))
            chunkContext.getStepContext().getStepExecution().setStatus(BatchStatus.COMPLETED);
        else
            chunkContext.getStepContext().getStepExecution().setStatus(BatchStatus.FAILED);

        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        if (stepExecution.getStatus().isUnsuccessful())
            return ExitStatus.FAILED;
        else
            return ExitStatus.COMPLETED;
    }
}
