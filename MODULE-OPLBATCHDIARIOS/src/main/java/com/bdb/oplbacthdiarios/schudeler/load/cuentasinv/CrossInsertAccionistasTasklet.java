package com.bdb.oplbacthdiarios.schudeler.load.cuentasinv;

import com.bdb.opaloshare.persistence.entity.HisInfoClienteEntity;
import com.bdb.opaloshare.persistence.entity.CtaInvCarEntity;
import com.bdb.opaloshare.persistence.entity.CtaInvHisEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryInfoClienteHis;
import com.bdb.opaloshare.persistence.repository.RepositoryCtaInvCar;
import com.bdb.opaloshare.persistence.repository.RepositoryCtaInvHis;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CommonsLog
public class CrossInsertAccionistasTasklet implements Tasklet {

    @Autowired
    RepositoryCtaInvCar repositoryCtaInvCar;

    @Autowired
    RepositoryInfoClienteHis repoAcc;

    @Autowired
    RepositoryCtaInvHis repoCtaInvHis;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {

        List<CtaInvCarEntity> invCarEntityList = repositoryCtaInvCar.findAll();
        List<HisInfoClienteEntity> clienteHisList = new ArrayList<>();
        List<CtaInvHisEntity> ctaInvCarHisList = new ArrayList<>();

        invCarEntityList.forEach(data -> {

            HisInfoClienteEntity clienteEntity = new HisInfoClienteEntity();
            CtaInvHisEntity ctaInvHisEntity = new CtaInvHisEntity();

            clienteEntity.setNumTit(data.getIdTit());
            clienteEntity.setOplTipidTblCodId("1");
            clienteEntity.setNomTit(data.getNomTit());
            clienteEntity.setDirTit(data.getDir());
            clienteEntity.setTelTit(data.getTel());
            clienteEntity.setFaxTit(data.getTel());
            clienteEntity.setExtension("0");
            clienteEntity.setCorreo("No tiene correo registrado");
            clienteEntity.setClasePer("1");
            clienteEntity.setDeclaRenta("1");
            clienteEntity.setIndExtra("1");
            clienteEntity.setFechaNacimiento(new Date(System.currentTimeMillis()));
            clienteEntity.setPaisNacimiento(1);
            clienteEntity.setOplTipciiuTblCodCiiu(1);
            clienteEntity.setOplTipciudTblCodCiud(data.getCodCiud());
            clienteEntity.setOplTipdeparTblCodDep(Integer.parseInt(data.getCodDep()));
            clienteEntity.setOplTippaisTblCodPais(1);
            clienteEntity.setOplTipsegmentoTblCodSegmento(1);
            clienteEntity.setRetencion(data.getIndRet().toString());
            clienteEntity.setOplParTipdaneTblCodDane(99);
            clienteHisList.add(clienteEntity);

            ctaInvHisEntity.setTipReg(data.getTipoReg().toString());
            ctaInvHisEntity.setNumCta(data.getNumCta().toString());
            ctaInvHisEntity.setNomCta(data.getNomCta());
            ctaInvHisEntity.setTipId(data.getTipId());
            ctaInvHisEntity.setIdTit(data.getIdTit());
            ctaInvHisEntity.setNomTit(data.getNomTit());
            ctaInvHisEntity.setIndRet(data.getIndRet());
            ctaInvHisEntity.setAutRet(data.getAutRet());
            ctaInvHisEntity.setDir(data.getDir());
            ctaInvHisEntity.setTel(data.getTel());
            ctaInvHisEntity.setClaTit(data.getClaTit());
            ctaInvHisEntity.setCodSect(data.getCodSect());
            ctaInvHisEntity.setCarTit(data.getCarTit());
            ctaInvHisEntity.setRelCta(data.getRelCta());
            ctaInvHisEntity.setClasCta(data.getClasCta());
            ctaInvHisEntity.setCtaEmb(data.getCtaEmb());
            ctaInvHisEntity.setEstCta(data.getEstCta());
            ctaInvHisEntity.setCodPais(data.getCodPais());
            ctaInvHisEntity.setCodDep(data.getCodDep());
            ctaInvHisEntity.setCodCiud(data.getCodCiud());
            ctaInvHisEntity.setFechaIni(data.getFechaIni());
            ctaInvHisEntity.setFechaFin(data.getFechaFin());
            ctaInvHisEntity.setIndExtr(data.getIndExtr());
            ctaInvHisEntity.setCodCree(data.getCodCree());
            ctaInvCarHisList.add(ctaInvHisEntity);
        });

        repoAcc.saveAll(clienteHisList);
        repoCtaInvHis.saveAll(ctaInvCarHisList);

        return RepeatStatus.FINISHED;

    }

}
