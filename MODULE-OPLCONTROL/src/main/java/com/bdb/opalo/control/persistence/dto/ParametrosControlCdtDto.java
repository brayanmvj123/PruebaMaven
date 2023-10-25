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
package com.bdb.opalo.control.persistence.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * DTO de entrada del CDT a marcar renovacion
 *
 * @author: Esteban Talero
 * @version: 01/10/2020
 * @since: 01/10/2020
 */
@Data
public class ParametrosControlCdtDto {

    /**
     * Numero de CDT
     */
    @NotNull(message = "Debe especificar numero de Cdt")
    private Long numCdt;

    /**
     * Numero de Titular
     */
    @NotNull(message = "Debe especificar el Numero del Titular del Cdt")
    private String numTit;

    /**
     * Novedad
     */
    @NotNull(message = "Debe especificar novedad")
    private Integer novedadV;

    /**
     * Descripcion de la marcacion
     */
    private String descripcion;

    /**
     * Codigo de la marcacion
     */
    private Long codMarcacion;

    /**
     * Codigo cut
     */
    @NotNull(message = "Debe informar codigo Cut")
    private String codCut;
}
