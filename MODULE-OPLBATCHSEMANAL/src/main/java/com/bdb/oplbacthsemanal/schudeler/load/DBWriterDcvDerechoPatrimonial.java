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
import com.bdb.opaloshare.persistence.repository.RepositoryAcuDerpatriemiDownEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Writer encargada de guardar la informacion leida del archivo DCV con el resultado de
 * Derechos patrimoniales
 *
 * @author: Esteban Talero
 * @version: 26/11/2020
 * @since: 24/11/2020
 */
public class DBWriterDcvDerechoPatrimonial implements ItemWriter<AcuDerpatriemiDownEntity> {

    @Autowired
    RepositoryAcuDerpatriemiDownEntity repoDcvSemanalPatrimonialesCarga;

    @Override
    public void write(List<? extends AcuDerpatriemiDownEntity> items) throws Exception {
        try {
            // Guarda la informacion del archivo en la base de Datos y clasifica al depositante como No enviando un 0
            items.forEach(itemsAcu -> itemsAcu.setDepositanteV("0"));
            repoDcvSemanalPatrimonialesCarga.saveAll(items);
        } catch (Exception e) {
            throw new Exception("OCURRIO UN ERROR AL GUARDAR ACUDERPATRIEMI ... ", e);
        }
    }
}
