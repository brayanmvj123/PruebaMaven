package com.bdb.oplbacthsemanal.schudeler.tasklet.reportpg;

import com.bdb.oplbacthsemanal.controller.service.interfaces.ReportPgWeeklyService;
import com.bdb.oplbacthsemanal.schudeler.services.OperationBatchReportPg;
import com.bdb.oplbacthsemanal.schudeler.tasklet.creationfile.CreationExcelByOfficeTasklet;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class ReportPgWeeklyTasklet implements Tasklet, StepExecutionListener {

    @Autowired
    ReportPgWeeklyService reportPgWeeklyService;

    @Autowired
    CreationExcelByOfficeTasklet simulatorQuotaTasklet;

    @Autowired
    OperationBatchReportPg operationBatchReportPg;

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
