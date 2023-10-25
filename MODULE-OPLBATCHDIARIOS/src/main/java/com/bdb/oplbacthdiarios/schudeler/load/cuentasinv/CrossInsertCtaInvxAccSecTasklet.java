package com.bdb.oplbacthdiarios.schudeler.load.cuentasinv;

import com.bdb.opaloshare.persistence.entity.CtaInvSecCarEntity;
import com.bdb.opaloshare.persistence.entity.HisCtaInvxCliEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryCtaInvSecCar;
import com.bdb.opaloshare.persistence.repository.RepositoryCtaInvxCli;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CrossInsertCtaInvxAccSecTasklet implements Tasklet {

    @Autowired
    RepositoryCtaInvSecCar repositoryCtaInvSecCar;

    @Autowired
    RepositoryCtaInvxCli repositoryCtaInvxCli;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {

        List<CtaInvSecCarEntity> ctaInvSecCarEntityList = repositoryCtaInvSecCar.findAll();
        List<HisCtaInvxCliEntity> ctaInvxAccEntityList = new ArrayList<>();

        ctaInvSecCarEntityList.forEach(data -> {
            if (!repositoryCtaInvxCli.existsByOplCtainvTblNumCtaAndOplInfoclienteTblNumTit(data.getNumCta().toString(), data.getIdTit())) {
                HisCtaInvxCliEntity ctaxCli = new HisCtaInvxCliEntity();
                ctaxCli.setTitularidad(2);
                ctaxCli.setOplCtainvTblNumCta(data.getNumCta().toString());
                ctaxCli.setOplInfoclienteTblNumTit(data.getIdTit());
                ctaInvxAccEntityList.add(ctaxCli);
            }
        });

        repositoryCtaInvxCli.saveAll(ctaInvxAccEntityList);

        return RepeatStatus.FINISHED;
    }
}
