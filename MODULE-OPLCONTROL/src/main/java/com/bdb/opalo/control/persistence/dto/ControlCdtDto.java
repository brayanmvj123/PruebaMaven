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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * DTO de entrada del CDT a marcar renovacion con los headers del Body
 *
 * @author: Esteban Talero
 * @version: 01/10/2020
 * @since: 01/10/2020
 */
@Data
public class ControlCdtDto {

    /**
     * App desde donde se accede a consumir el servicio
     */
    @NotNull(message = "Debe especificar app")
    private String app;

    /**
     * Date en el momento que se consume el servicio
     */
    @NotNull(message = "Debe especificar el valor de fecha y hora")
    private LocalDateTime date;

    /**
     * Canal desde donde se accede al servicio
     */
    @NotNull(message = "Debe especificar canal")
    private String canal;

    /**
     * Parametros del request
     */
    @NotNull(message = "Debe especificar los parametros del request")
    @Valid
    private ParametrosControlCdtDto parametros;

    @Valid
    private CondicionesDto condiciones;

    @Valid
    private CuentaClienteDto cuentaCliente;

}
