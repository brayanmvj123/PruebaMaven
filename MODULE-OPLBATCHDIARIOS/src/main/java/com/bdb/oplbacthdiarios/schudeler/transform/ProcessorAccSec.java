package com.bdb.oplbacthdiarios.schudeler.transform;

import com.bdb.opaloshare.persistence.entity.CtaInvSecCarEntity;
import com.bdb.oplbacthdiarios.controller.service.interfaces.CtaInvWService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

@CommonsLog
public class ProcessorAccSec implements ItemProcessor<CtaInvSecCarEntity, CtaInvSecCarEntity> {

    @Autowired
    CtaInvWService ctaInvWService;

    @Override
    public CtaInvSecCarEntity process(CtaInvSecCarEntity item) throws Exception {
        log.info("Ingresa a ProcessorAccSec");
        CtaInvSecCarEntity ctaInvSecCar = new CtaInvSecCarEntity();

        ctaInvSecCar.setTipReg(item.getTipReg());
        ctaInvSecCar.setNumCta(item.getNumCta());
        ctaInvSecCar.setTipRel(item.getTipRel());
        ctaInvSecCar.setTipId(item.getTipId());
        ctaInvSecCar.setIdTit(item.getIdTit());
        ctaInvSecCar.setNomTit(item.getNomTit());
        ctaInvSecCar.setDir(item.getDir());
        ctaInvSecCar.setTel(item.getTel());
        ctaInvSecCar.setClaTit(item.getClaTit());
        ctaInvSecCar.setCodSect(item.getCodSect());
        ctaInvSecCar.setCarTit(item.getCarTit());

        ctaInvWService.findAllBasicAccountInf().forEach(data -> {
            log.info("recorre lista basicAccount "+data.getNumCta() + " -- "+item.getNumCta());
            if (data.getNumCta().equals(item.getNumCta())) {
                log.info("Es igual cta basicAc "+data.getNomCta());
                ctaInvSecCar.setNomCta(data.getNomCta());
                ctaInvSecCar.setRelCta(data.getRelCta());
                ctaInvSecCar.setEstCta(data.getEstCta());
            }
        });

        return ctaInvSecCar;

    }
}
