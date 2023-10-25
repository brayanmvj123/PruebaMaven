package com.bdb.oplbacthdiarios.schudeler.load;

import com.bdb.opaloshare.persistence.entity.CtaInvCarEntity;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.BasicAccountInformationModel;
import com.bdb.opaloshare.persistence.repository.RepositoryCtaInvCar;
import com.bdb.oplbacthdiarios.controller.service.interfaces.CtaInvWService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@CommonsLog
public class DBWriterCtaInvT implements ItemWriter<CtaInvCarEntity> {

    @Autowired
    RepositoryCtaInvCar repositoryCtaInvCar;

    @Autowired
    CtaInvWService ctaInvWService;

    @Override
    public void write(List<? extends CtaInvCarEntity> items) throws Exception {
        List<CtaInvCarEntity> ctaInvCarEntities = new ArrayList<>();
        List<BasicAccountInformationModel> accountInformationModels = new ArrayList<>();

        items.forEach(data -> {
            if (data.getNumCta() != null && data.getNumCta() != 0L) {
                CtaInvCarEntity ctaInvCarEntity = new CtaInvCarEntity();
                BasicAccountInformationModel basicAccountInf = new BasicAccountInformationModel();

                ctaInvCarEntity.setAutRet(data.getAutRet());
                ctaInvCarEntity.setCarTit(data.getCarTit());
                ctaInvCarEntity.setClasCta(data.getClasCta());
                ctaInvCarEntity.setCodCiud(data.getCodCiud());
                ctaInvCarEntity.setCodCree(data.getCodCree());
                ctaInvCarEntity.setCodDep(data.getCodDep());
                ctaInvCarEntity.setCodPais(data.getCodPais());
                ctaInvCarEntity.setCodSect(data.getCodSect());
                ctaInvCarEntity.setClaTit(data.getClaTit());
                ctaInvCarEntity.setCtaEmb(data.getCtaEmb());
                ctaInvCarEntity.setDir(data.getDir());
                ctaInvCarEntity.setEstCta(data.getEstCta());
                ctaInvCarEntity.setFechaFin(data.getFechaFin());
                ctaInvCarEntity.setFechaIni(data.getFechaIni());
                ctaInvCarEntity.setIdTit(data.getIdTit());
                ctaInvCarEntity.setIndExtr(data.getIndExtr());
                ctaInvCarEntity.setIndRet(data.getIndRet());
                ctaInvCarEntity.setNomCta(data.getNomCta());
                ctaInvCarEntity.setNomTit(data.getNomTit());
                ctaInvCarEntity.setNumCta(data.getNumCta());
                ctaInvCarEntity.setRelCta(data.getRelCta());
                ctaInvCarEntity.setTel(data.getTel());
                ctaInvCarEntity.setTipId(data.getTipId());
                ctaInvCarEntity.setTipoReg(data.getTipoReg());

                ctaInvCarEntities.add(ctaInvCarEntity);

                basicAccountInf.setNumCta(data.getNumCta());
                basicAccountInf.setNomCta(data.getNomCta());
                basicAccountInf.setRelCta(data.getRelCta());
                basicAccountInf.setEstCta(data.getEstCta());

                accountInformationModels.add(basicAccountInf);
            }
        });

        ctaInvCarEntities.forEach(System.out::println);
        repositoryCtaInvCar.saveAll(ctaInvCarEntities);
        ctaInvWService.saveBasicAccountInf(accountInformationModels);
    }
}

