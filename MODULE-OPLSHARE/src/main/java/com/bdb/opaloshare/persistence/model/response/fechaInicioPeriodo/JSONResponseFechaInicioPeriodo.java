package com.bdb.opaloshare.persistence.model.response.fechaInicioPeriodo;

import com.bdb.opaloshare.persistence.model.jsonschema.tasaVariable.ParametersTasaVariable;
import com.bdb.opaloshare.persistence.model.response.simuladorcuota.JSONResponseStatus;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JSONResponseFechaInicioPeriodo implements Serializable {

    private JSONResponseStatus status;

    private String requestUrl;

    private ParametersTasaVariable parameters;

    private JSONResultFechaInicioPeriodo result;

    private static final long serialVersionUID = 1L;
}