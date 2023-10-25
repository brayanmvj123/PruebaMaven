package com.bdb.moduleoplcancelaciones.scheduler.tasklet;

import com.bdb.moduleoplcancelaciones.scheduler.service.OperationCancelCdtaCtaCon;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class CancelCdtCtaConTasklet implements Tasklet, StepExecutionListener {

    private final OperationCancelCdtaCtaCon operationCancelCdtaCtaCon;

    public CancelCdtCtaConTasklet(OperationCancelCdtaCtaCon operationCancelCdtaCtaCon){
        this.operationCancelCdtaCtaCon = operationCancelCdtaCtaCon;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        if (stepExecution.getStatus().isUnsuccessful())
            return ExitStatus.FAILED;
        else
            return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        operationCancelCdtaCtaCon.cancelarCdtCtaContables(chunkContext.getStepContext().getJobParameters().get("url").toString());
        return RepeatStatus.FINISHED;
    }
}
