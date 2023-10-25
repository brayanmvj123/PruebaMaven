package com.bdb.oplbacthdiarios.schudeler.tasklets;

import com.bdb.opaloshare.persistence.repository.RepositoryAcuRenovatesorDownEntity;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

@CommonsLog
public class CdtRenovadosOficinaTasklet implements Tasklet {

    @Autowired
    private RepositoryAcuRenovatesorDownEntity acuRenovaRepo;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        log.info("start CdtRenovadosOficinaTasklet execute()...");
        return null;
    }

}
