package com.bdb.oplbacthdiarios.schudeler.tasklets.reportpg;

import com.bdb.oplbacthdiarios.schudeler.services.OperationBatchReportPg;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ReportPgTasklet implements Tasklet, StepExecutionListener {

    private final OperationBatchReportPg operationBatchReportPg;

    public ReportPgTasklet(OperationBatchReportPg operationBatchReportPg){
        this.operationBatchReportPg = operationBatchReportPg;
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
        operationBatchReportPg.reportFechaVenOfic();
        return RepeatStatus.FINISHED;
    }
}
