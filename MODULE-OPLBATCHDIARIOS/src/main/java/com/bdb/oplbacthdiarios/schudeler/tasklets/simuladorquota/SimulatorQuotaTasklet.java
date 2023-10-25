package com.bdb.oplbacthdiarios.schudeler.tasklets.simuladorquota;

import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.oplbacthdiarios.schudeler.services.OperationAjusLiqDia;
import com.bdb.oplbacthdiarios.schudeler.services.OperationBatchSimQuota;
import com.bdb.oplbacthdiarios.schudeler.services.OperationHisPg;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class SimulatorQuotaTasklet implements Tasklet, StepExecutionListener {

    @Autowired
    OperationHisPg operationHisPg;

    @Autowired
    OperationAjusLiqDia operationAjusLiqDia;

    @Autowired
    SharedService sharedService;

    @Autowired
    private OperationBatchSimQuota operationBatchSimQuota;


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
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        String host = sharedService.generarUrl(chunkContext.getStepContext().getJobParameters().get("url").toString(), "OPLBATCHDIARIO");
        operationBatchSimQuota.calculateFactor(host);
        operationHisPg.backupSalpg();
        operationAjusLiqDia.createAjusLiqDia();
        return RepeatStatus.FINISHED;
    }
}
