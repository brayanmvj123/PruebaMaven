package com.bdb.platform.servfront.model.JSONSchema;

import javax.validation.constraints.NotNull;

/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBF was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
public class JSONLabels {

    @NotNull
    private String canal;

    @NotNull
    private ParametrosJSONLabels parametros;

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public ParametrosJSONLabels getParametros() {
        return parametros;
    }

    public void setParametros(ParametrosJSONLabels parametros) {
        this.parametros = parametros;
    }

    public JSONLabels(@NotNull String canal, @NotNull ParametrosJSONLabels parametros) {
        super();
        this.canal = canal;
        this.parametros = parametros;
    }

    public JSONLabels() {
        super();
    }
}