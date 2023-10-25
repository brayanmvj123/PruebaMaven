package com.bdb.oplbacthdiarios.schudeler.load.cuentasinv;

import com.bdb.opaloshare.persistence.entity.CtaInvCarEntity;
import com.bdb.opaloshare.persistence.entity.HisCtaInvxCliEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryCtaInvCar;
import com.bdb.opaloshare.persistence.repository.RepositoryCtaInvxCli;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CrossInsertCtaInvxAccTasklet implements Tasklet {

    @Autowired
    RepositoryCtaInvCar repositoryCtaInvCar;

    @Autowired
    RepositoryCtaInvxCli repositoryCtaInvxCli;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {

        List<CtaInvCarEntity> ctaInvCarEntityList = repositoryCtaInvCar.findAll();
        List<HisCtaInvxCliEntity> ctaInvxAccEntityList = new ArrayList<>();

        ctaInvCarEntityList.forEach(data -> {
            if (!repositoryCtaInvxCli.existsByOplCtainvTblNumCtaAndOplInfoclienteTblNumTit(data.getNumCta().toString(), data.getIdTit())) {
                HisCtaInvxCliEntity ctaxCli = new HisCtaInvxCliEntity();
                ctaxCli.setTitularidad(1);
                ctaxCli.setOplCtainvTblNumCta(data.getNumCta().toString());
                ctaxCli.setOplInfoclienteTblNumTit(data.getIdTit());
                ctaInvxAccEntityList.add(ctaxCli);
            }
        });

        repositoryCtaInvxCli.saveAll(ctaInvxAccEntityList);

        return RepeatStatus.FINISHED;
    }
}
