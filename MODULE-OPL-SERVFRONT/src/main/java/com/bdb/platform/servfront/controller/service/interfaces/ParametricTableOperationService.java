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
package com.bdb.platform.servfront.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.OplHisCalendarioDownEntity;
import com.bdb.platform.servfront.model.JSONSchema.*;

import java.util.List;

/**
 * Interface encargada realizar operaciones a las tablas parametricas de la base de Datos
 *
 * @author: Esteban Talero
 * @version: 02/12/2020
 * @since: 02/12/2020
 */
public interface ParametricTableOperationService {

    List<JSONGetTipoBase> consultaTipoBase();

    List<JSONGetTipoPeriodo> consultaTipoPeriodo();

    List<JSONGetTipoTasa> consultaTipoTasa();

    List<JSONGetTipoPlazo> consultaTipoPlazo();

    List<JSONGetCalendario> consultaCalendarioAnnioNoHabil(int annio);

}
