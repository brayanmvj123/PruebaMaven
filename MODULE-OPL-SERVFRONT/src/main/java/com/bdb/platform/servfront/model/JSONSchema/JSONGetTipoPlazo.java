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
package com.bdb.platform.servfront.model.JSONSchema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objeto de respuesta para la consulta a la tabla tipo de Plazo
 *
 * @author: Esteban Talero
 * @version: 02/12/2020
 * @since: 02/12/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JSONGetTipoPlazo {

    /**
     * Tipo de Plazo
     */
    private Integer tipPlazo;

    /**
     * Descripcion tipo de Plazo
     */
    private String descPlazo;

    /**
     * Homologacion DECEVAL
     */
    private String homoDcvbta;
}
