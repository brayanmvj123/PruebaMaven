package com.bdb.opalo.oficina.scheduler.read;

import com.bdb.opalo.oficina.controller.service.interfaces.operationbatch.OperationClosingOfficesService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateTranPgOfficeIdTasklet implements Tasklet, StepExecutionListener {

    @Autowired
    OperationClosingOfficesService operationClosingOfficesService;

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("PASO: "+stepExecution.getStatus().getBatchStatus().name());
        if (stepExecution.getStatus().isUnsuccessful())
            return ExitStatus.FAILED;
        else
            return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        operationClosingOfficesService.changeTranPgOfficesId();
        return RepeatStatus.FINISHED;
    }
}
