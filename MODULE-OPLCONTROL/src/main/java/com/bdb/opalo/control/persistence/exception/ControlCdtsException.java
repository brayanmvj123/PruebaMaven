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
package com.bdb.opalo.control.persistence.exception;

import com.bdb.opalo.control.persistence.dto.ControlCdtDto;

/**
 * Clase Exception del servicio Control CDTs
 *
 * @author: Esteban Talero
 * @version: 01/10/2020
 * @since: 01/10/2020
 */
public class ControlCdtsException extends Exception {

    private Integer httpStatus;
    private String message;
    private ControlCdtDto controlCdtDto;

    private static final long serialVersionUID = -528134378438377740L;

    public ControlCdtsException(Integer httpStatus, String message, ControlCdtDto controlCdtDto) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.controlCdtDto = controlCdtDto;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ControlCdtDto getControlCdtDto() {
        return controlCdtDto;
    }

    public void setControlCdtDto(ControlCdtDto controlCdtDto) {
        this.controlCdtDto = controlCdtDto;
    }
}
