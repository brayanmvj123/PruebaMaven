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
package com.bdb.opalogdoracle.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Dto generico de respuesta del servicio
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
     * Status
     */
    private String nameService;

    /**
     * Response Code
     */
    private HttpStatus responseCode;

}
