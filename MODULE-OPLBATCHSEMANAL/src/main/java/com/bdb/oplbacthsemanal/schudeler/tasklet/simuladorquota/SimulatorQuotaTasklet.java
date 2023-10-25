package com.bdb.oplbacthsemanal.schudeler.tasklet.simuladorquota;

import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.oplbacthsemanal.schudeler.services.OperationAjusLiqSem;
import com.bdb.oplbacthsemanal.schudeler.services.OperationBatchSimQuota;
import org.springframework.batch.core.*;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class SimulatorQuotaTasklet implements Tasklet, StepExecutionListener {

    @Autowired
    OperationBatchSimQuota operationBatchSimQuota;

    @Autowired
    OperationAjusLiqSem operationAjusLiqsem;

    @Autowired
    SharedService sharedService;

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
        String host = sharedService.generarUrl(chunkContext.getStepContext().getJobParameters().get("url").toString(), "OPLBATCHSEMANAL");
        operationBatchSimQuota.calculateFactor(host);
        operationAjusLiqsem.createAjusLiqSem();
        return RepeatStatus.FINISHED;
    }
}
