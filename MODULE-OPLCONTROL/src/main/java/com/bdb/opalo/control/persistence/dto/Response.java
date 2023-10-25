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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Dto generico de respuesta del servicio
 *
 * @author: Esteban Talero
 * @version: 01/10/2020
 * @since: 01/10/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String app;

    /**
     * Timestamp
     */
    private LocalDateTime date;
    /**
     * Data
     */
    private ResponseParametros parametrosRequest;

    /**
     * Status
     */
    private String nameService;

    /**
     * Response Code
     */
    private HttpStatus responseCode;

    private String errorMessage;

}
