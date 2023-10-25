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
package com.bdb.oplbacthdiarios.schudeler.load;

import com.bdb.opaloshare.persistence.entity.CarDerpatriemiDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryCarDerpatriemiDownEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Writer encargada de guardar la informacion leida del archivo DCV dcob01s
 *
 * @author: Esteban Talero
 * @version: 19/11/2020
 * @since: 19/11/2020
 */
public class DBWriterDcvDerechoPatrimoniales implements ItemWriter<CarDerpatriemiDownEntity> {

    @Autowired
    RepositoryCarDerpatriemiDownEntity repoDcvPatrimonialesCarga;

    @Override
    public void write(List<? extends CarDerpatriemiDownEntity> items) {
        repoDcvPatrimonialesCarga.saveAll(items);
    }
}
