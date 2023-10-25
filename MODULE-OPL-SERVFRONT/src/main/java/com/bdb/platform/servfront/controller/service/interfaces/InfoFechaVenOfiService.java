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

import com.bdb.opaloshare.persistence.model.jsonschema.semanal.JSONGetSalPgSemanal;
import com.bdb.platform.servfront.model.JSONSchema.JSONGetSalPgDiaria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Interface encargada realizar la consulta a la tabla de salida pgSemanal de la base de Datos
 *
 * @author: Esteban Talero
 * @version: 16/12/2020
 * @since: 16/12/2020
 */
public interface InfoFechaVenOfiService {

    List<JSONGetSalPgSemanal> consultaDataFechaVenOfiSemanal();
    List<JSONGetSalPgDiaria> consultaDataFechaVenOfi();
    Page<JSONGetSalPgDiaria> consultaDataFechaVenOfiPagination(Pageable pageable);
}
