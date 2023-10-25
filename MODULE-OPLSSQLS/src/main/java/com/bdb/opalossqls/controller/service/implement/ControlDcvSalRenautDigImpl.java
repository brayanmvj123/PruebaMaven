/*
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Esteban Talero <etalero@bancodebogota.com.co>.
 */
package com.bdb.opalossqls.controller.service.implement;

import com.bdb.opaloshare.persistence.model.component.ModelCrucePatrimonioRenaut;
import com.bdb.opalossqls.controller.service.interfaces.ControlDcvSalRenautDigService;
import com.bdb.opalossqls.controller.service.interfaces.EstadosProcesosService;
import com.bdb.opalossqls.persistence.entity.DCVSalRenautdigEntity;
import com.bdb.opalossqls.persistence.repository.RepositoryDCVSalRenautdig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementacion encargada de limpiar y guardar en la tabla salRenautDig los Cdts
 *
 * @author: Esteban Talero
 * @version: 19/10/2020
 * @since: 19/10/2020
 */
@Service
public class ControlDcvSalRenautDigImpl implements ControlDcvSalRenautDigService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RepositoryDCVSalRenautdig repositoryDCVSalRenautdig;

    @Autowired
    EstadosProcesosService estadosProcesosService;

    @Override
    public boolean controlDcvRenautDig(List<ModelCrucePatrimonioRenaut> listPatrimonio) {

        logger.info("start repositoryDCVSalRenautdig.deleteAll()...");
        repositoryDCVSalRenautdig.deleteAll();

        boolean resultInsertDcvRenaut = false;

        logger.info("crecesToSalDCV: " + listPatrimonio.toString());

        List<DCVSalRenautdigEntity> salDcvRenautDigItems = new ArrayList<>();

        try {

            listPatrimonio.forEach(dataCrucePatrimonial -> {

                logger.info("start crecesToSal.forEach(dataCrucePatrimonial ->)...");

                DCVSalRenautdigEntity dcvSalPdcvl = new DCVSalRenautdigEntity();

                dcvSalPdcvl.setCodIsin(dataCrucePatrimonial.getCodIsin());
                dcvSalPdcvl.setNumCdt(Long.valueOf(dataCrucePatrimonial.getNumCdt().substring(5, 14)));
                dcvSalPdcvl.setTipId(dataCrucePatrimonial.getTipId());
                dcvSalPdcvl.setNumTit(dataCrucePatrimonial.getNumTit());
                dcvSalPdcvl.setNomTit(dataCrucePatrimonial.getNomTit());
                dcvSalPdcvl.setCapPg(new BigDecimal(dataCrucePatrimonial.getCapPg()));
                dcvSalPdcvl.setIntBruto(new BigDecimal(dataCrucePatrimonial.getIntBruto()));
                dcvSalPdcvl.setRteFte(new BigDecimal(dataCrucePatrimonial.getRteFte()));
                dcvSalPdcvl.setIntNeto(new BigDecimal(dataCrucePatrimonial.getIntNeto()));
                dcvSalPdcvl.setFormaPago("RENOVACION_DIGITAL");

                logger.info("dcvSalPdcvl: " + dcvSalPdcvl.toString());
                salDcvRenautDigItems.add(dcvSalPdcvl);
            });

            logger.info("repositoryDCVSalRenautdig.saveAll...");
            repositoryDCVSalRenautdig.saveAll(salDcvRenautDigItems);
            resultInsertDcvRenaut = true;

        } catch (Exception e) {
            estadosProcesosService.llenarEstado("RENAUT_FAIL");
            logger.error("Error en DcvSalRenautDigImp: ");
        }
        return resultInsertDcvRenaut;
    }
}
