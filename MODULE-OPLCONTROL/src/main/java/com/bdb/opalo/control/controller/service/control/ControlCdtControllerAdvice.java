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
package com.bdb.opalo.control.controller.service.control;

import com.bdb.opalo.control.persistence.exception.ControlCdtsException;
import com.bdb.opalo.control.persistence.dto.Response;
import com.bdb.opalo.control.persistence.dto.ResponseParametros;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Controlador Advice del servicio de Control renovacion CDT
 *
 * @author: Esteban Talero
 * @version: 09/10/2020
 * @since: 09/10/2020
 */
@RestControllerAdvice
@Slf4j
public class ControlCdtControllerAdvice {
    @ExceptionHandler(value = ControlCdtsException.class)
    public ResponseEntity<Response> handleControlCdts(ControlCdtsException e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (e.getHttpStatus() == 204) {
            status = HttpStatus.NO_CONTENT;
        }
        if (e.getHttpStatus() == 409) {
            status = HttpStatus.CONFLICT;
        }
        ResponseParametros responseParametros = new ResponseParametros();
        responseParametros.setCanal(e.getControlCdtDto().getCanal());
        responseParametros.setParametros(e.getControlCdtDto().getParametros());
        Response resultEntity = new Response("OPALO", LocalDateTime.now(), responseParametros, "controlesCDTs", status, e.getControlCdtDto().getCanal().trim());
        return new ResponseEntity<>(resultEntity, status);
    }
}
