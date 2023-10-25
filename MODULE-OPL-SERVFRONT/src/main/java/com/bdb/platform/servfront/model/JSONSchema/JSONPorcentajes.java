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
public class JSONPorcentajes {
    @NotNull
    private String canal;

    @NotNull
    private ParametrosJSONPorcentajes parametros;

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public ParametrosJSONPorcentajes getParametros() {
        return parametros;
    }

    public void setParametros(ParametrosJSONPorcentajes parametros) {
        this.parametros = parametros;
    }

    public JSONPorcentajes(@NotNull String canal, @NotNull ParametrosJSONPorcentajes parametros) {
        super();
        this.canal = canal;
        this.parametros = parametros;
    }

    public JSONPorcentajes() {
        super();
    }
}
