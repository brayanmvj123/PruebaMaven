package com.bdb.oplbacthdiarios.schudeler.tasklets.creationfile;

import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.oplbacthdiarios.schudeler.services.OperationCreationFile;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

//@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CreationExcelByOfficeTasklet implements Tasklet, StepExecutionListener {

    @Autowired
    OperationCreationFile operationCreationFile;

    @Autowired
    SharedService sharedService;

    @Override
    public void beforeStep(StepExecution stepExecution) {}

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
        operationCreationFile.creationFile(host);
        return RepeatStatus.FINISHED;
    }
}