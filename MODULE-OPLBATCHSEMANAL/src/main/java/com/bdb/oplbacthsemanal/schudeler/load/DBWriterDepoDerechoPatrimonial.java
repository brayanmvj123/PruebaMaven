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
package com.bdb.oplbacthsemanal.schudeler.load;

import com.bdb.opaloshare.persistence.entity.AcuDerpatriemiDownEntity;
import com.bdb.opaloshare.persistence.model.component.ModelDepositanteDerechoPatrimonial;
import com.bdb.opaloshare.persistence.repository.RepositoryAcuDerpatriemiDownEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Writer encargada de hacer el cruce del archivo semanal APE150R con el archivo acumulado APE170R
 * Para clasificar los depositantes del banco de Bogota
 *
 * @author: Esteban Talero
 * @version: 26/11/2020
 * @since: 25/11/2020
 */
public class DBWriterDepoDerechoPatrimonial implements ItemWriter<ModelDepositanteDerechoPatrimonial> {

    @Autowired
    RepositoryAcuDerpatriemiDownEntity repoDcvSemanalPatrimonialesCarga;

    @Override
    public void write(List<? extends ModelDepositanteDerechoPatrimonial> items) throws Exception {
        try {
            //Busca los coincidentes en la tabla acumulado despositantes con los del archivo y clasifica a los depositante del banco
            //Con 1 en el campo de depositanteV
            items.forEach(itemAcumDep -> {
                AcuDerpatriemiDownEntity acuDerpatriEmi = repoDcvSemanalPatrimonialesCarga
                        .findAccumulatedDepositor(itemAcumDep.getIsin(), itemAcumDep.getCtaInv(), itemAcumDep.getIdTit());
                if (acuDerpatriEmi != null) {
                    repoDcvSemanalPatrimonialesCarga.updateDepositorStatus(acuDerpatriEmi.getIsin(), acuDerpatriEmi.getCtaInv(),
                            acuDerpatriEmi.getIdTit());
                }
            });
        } catch (Exception e) {
            throw new Exception("OCURRIO UN ERROR AL GUARDAR ACUDERPATRIEMI ... ", e);
        }
    }

}
