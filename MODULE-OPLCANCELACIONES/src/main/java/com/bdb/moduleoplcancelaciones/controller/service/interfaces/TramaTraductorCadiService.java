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
package com.bdb.moduleoplcancelaciones.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.HisCdtxCtainvEntity;
import com.bdb.opaloshare.persistence.entity.HisInfoCDTEntity;
import com.bdb.opaloshare.persistence.entity.HisRenovacion;
import com.bdb.opaloshare.persistence.entity.HisTransaccionesEntity;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestTraductor.RequestTraductor;

import java.util.List;

/**
 * Interface encargado de ejecutar traductor CADI
 *
 * @author: Esteban Talero
 * @version: 10/11/2020
 * @since: 09/11/2020
 */
public interface TramaTraductorCadiService {

    boolean tramasTraductorCadi(String urlBatch, RequestTraductor requestTraductor, List<HisCdtxCtainvEntity> hisCdtxCtainvEntities,
                                List<HisTransaccionesEntity> transacciones, HisInfoCDTEntity hisInfoCDTEntity , HisRenovacion renovacion);
}
