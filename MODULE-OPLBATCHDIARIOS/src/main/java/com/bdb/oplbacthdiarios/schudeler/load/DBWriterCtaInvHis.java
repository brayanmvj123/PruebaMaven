package com.bdb.oplbacthdiarios.schudeler.load;

import com.bdb.opaloshare.persistence.entity.CtaInvCarEntity;
import com.bdb.opaloshare.persistence.entity.CtaInvHisEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryCtaInvHis;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@CommonsLog
public class DBWriterCtaInvHis implements ItemWriter<CtaInvCarEntity> {

    @Autowired
    RepositoryCtaInvHis repositoryCtaInvHis;

    @Override
    public void write(List<? extends CtaInvCarEntity> items) throws Exception {
        log.info("DBWriterCtaInvHis ");
        List<CtaInvHisEntity> ctaInvHisEntityArrayList = new ArrayList<>();
        items.forEach(data -> {
            //if (!repositoryCtaInvHis.existsByNumCta(data.getNumCta().toString())) {
            if (data.getNumCta() != null && data.getNumCta() != 0L) {
                log.info("DBWriterCtaInvHis "+data.getNumCta().toString());
                CtaInvHisEntity ctaInvHisEntity = new CtaInvHisEntity();

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
                ctaInvHisEntityArrayList.add(ctaInvHisEntity);
            }
        });

        repositoryCtaInvHis.saveAll(ctaInvHisEntityArrayList);

     }
}
