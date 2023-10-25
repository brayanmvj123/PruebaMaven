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

import com.bdb.opaloshare.persistence.entity.HisTraductoresDownEntity;
import com.bdb.opaloshare.persistence.entity.SalTramastcDownEntity;
import com.bdb.opaloshare.persistence.model.trad.CruceHisCdtRenautDigDto;

import java.util.List;
import java.util.Map;

/**
 * Service encargado de generar la trama del traductor CADI
 *
 * @author: Esteban Talero
 * @version: 26/10/2020
 * @since: 26/10/2020
 */
public interface CreatePlotService {
    Map<String, List<SalTramastcDownEntity>> createPlot(List<Long> cdtsHisCadi, List<CruceHisCdtRenautDigDto> hisCdtsCadig,
                                                        HisTraductoresDownEntity trd);
}
