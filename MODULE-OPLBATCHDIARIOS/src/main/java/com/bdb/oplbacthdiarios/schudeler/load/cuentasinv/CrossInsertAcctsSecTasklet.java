package com.bdb.oplbacthdiarios.schudeler.load.cuentasinv;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.persistence.entity.HisInfoClienteEntity;
import com.bdb.opaloshare.persistence.entity.CtaInvHisEntity;
import com.bdb.opaloshare.persistence.entity.CtaInvSecCarEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryInfoClienteHis;
import com.bdb.opaloshare.persistence.repository.RepositoryCtaInvHis;
import com.bdb.opaloshare.persistence.repository.RepositoryCtaInvSecCar;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CommonsLog
public class CrossInsertAcctsSecTasklet implements Tasklet {

    @Autowired
    RepositoryCtaInvSecCar repositoryCtaInvSecCar;

    @Autowired
    RepositoryInfoClienteHis repositoryInfoClienteHis;

    @Autowired
    RepositoryCtaInvHis repoCtaInv;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws IOException, ErrorFtps {

        List<CtaInvSecCarEntity> invCarSecEntityList = repositoryCtaInvSecCar.findAll();
        List<HisInfoClienteEntity> clienteHisList = new ArrayList<>();
        List<CtaInvHisEntity> ctaInvCarHisList = new ArrayList<>();

        invCarSecEntityList.forEach(data -> {

            HisInfoClienteEntity clienteEntity = new HisInfoClienteEntity();
            CtaInvHisEntity ctaInvHisEntity = new CtaInvHisEntity();

            clienteEntity.setNumTit(data.getIdTit());
            clienteEntity.setOplTipidTblCodId("1");
            clienteEntity.setNomTit(data.getNomTit());
            clienteEntity.setDirTit(data.getDir());
            clienteEntity.setTelTit(data.getTel());
            clienteEntity.setFaxTit(data.getTel());
            clienteEntity.setExtension(data.getTel());
            clienteEntity.setCorreo("No tiene correo registrado");
            clienteEntity.setDeclaRenta("1");
            clienteEntity.setIndExtra("1");
            clienteEntity.setFechaNacimiento(new Date(System.currentTimeMillis()));
            clienteEntity.setPaisNacimiento(1);
            clienteEntity.setOplTipciiuTblCodCiiu(1);
            clienteEntity.setOplTipciudTblCodCiud(1);
            clienteEntity.setOplTipdeparTblCodDep(1);
            clienteEntity.setOplTippaisTblCodPais(1);
            clienteEntity.setOplTipsegmentoTblCodSegmento(1);
            clienteEntity.setRetencion("1");
            clienteEntity.setOplParTipdaneTblCodDane(99);
            clienteHisList.add(clienteEntity);

            ctaInvHisEntity.setTipReg(data.getTipReg());
            ctaInvHisEntity.setNumCta(data.getNumCta().toString());
            ctaInvHisEntity.setNomCta(data.getNomCta());
            ctaInvHisEntity.setTipId(data.getTipId());
            ctaInvHisEntity.setIdTit(data.getIdTit());
            ctaInvHisEntity.setNomTit(data.getNomTit());
            ctaInvHisEntity.setIndRet(1);
            ctaInvHisEntity.setAutRet(1);
            ctaInvHisEntity.setDir(data.getDir());
            ctaInvHisEntity.setTel(data.getTel());
            ctaInvHisEntity.setClaTit(data.getClaTit());
            ctaInvHisEntity.setCodSect(Long.valueOf(data.getCodSect()));
            ctaInvHisEntity.setCarTit(Integer.parseInt(data.getCarTit()));
            ctaInvHisEntity.setRelCta(data.getRelCta());
            ctaInvHisEntity.setClasCta(1);
            ctaInvHisEntity.setCtaEmb(1);
            ctaInvHisEntity.setEstCta(data.getEstCta());
            ctaInvHisEntity.setCodPais("9999");
            ctaInvHisEntity.setCodDep("9999");
            ctaInvHisEntity.setCodCiud(9999);
            ctaInvHisEntity.setFechaIni(Long.valueOf("20211014"));
            ctaInvHisEntity.setFechaFin(Long.valueOf("20211017"));
            ctaInvHisEntity.setIndExtr(1);
            ctaInvHisEntity.setCodCree(1);
            ctaInvCarHisList.add(ctaInvHisEntity);
        });

        repositoryInfoClienteHis.saveAll(clienteHisList);
        repoCtaInv.saveAll(ctaInvCarHisList);

        return RepeatStatus.FINISHED;
    }
}
