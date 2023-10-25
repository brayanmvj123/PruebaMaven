package com.bdb.opalo.mds.scheduler.tasklet;

import com.bdb.opalo.mds.scheduler.services.OperationRatesMds;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class CreationRatesMdsTasklet implements Tasklet, StepExecutionListener {

    final OperationRatesMds operationRatesMds;

    public CreationRatesMdsTasklet(OperationRatesMds operationRatesMds) {
        this.operationRatesMds = operationRatesMds;
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
        operationRatesMds.creationRates();
        return RepeatStatus.FINISHED;
    }
}
