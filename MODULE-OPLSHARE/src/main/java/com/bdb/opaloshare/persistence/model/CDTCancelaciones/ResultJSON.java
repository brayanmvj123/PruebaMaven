/*
 * Copyright (c) 2021 Banco de Bogotá. All Rights Reserved.
 * <p>
 * ACCIONESBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Esteban Talero <etalero@bancodebogota.com.co>.
 */
package com.bdb.opaloshare.persistence.model.CDTCancelaciones;

/**
 * Objeto de respuesta al servicio Batch
 *
 * @author: Esteban Talero
 * @version: 19/01/2021
 * @since: 19/01/2021
 */
public class ResultJSON {

    private String date;
    private String message;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
