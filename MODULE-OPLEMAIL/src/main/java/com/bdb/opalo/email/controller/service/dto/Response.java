/**
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
package com.bdb.opalo.email.controller.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Dto generico de respuesta del servicio
 *
 * @author: Esteban Talero
 * @version: 26/07/2020
 * @since: 26/07/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    /**
     * Timestamp
     */
    private LocalDateTime timestamp;
    /**
     * Data
     */
    private String data;
    /**
     * Response Code
     */
    private HttpStatus respondeCode;
    /**
     * Status
     */
    private String status;
}
