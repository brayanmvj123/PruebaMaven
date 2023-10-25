package com.bdb.opaloshare.persistence.model.response.tasaVariable;

import com.bdb.opaloshare.persistence.entity.OplHisTasaVariableEntity;
import com.bdb.opaloshare.persistence.model.jsonschema.tasaVariable.ParametersTasaVariable;
import com.bdb.opaloshare.persistence.model.response.simuladorcuota.JSONResponseStatus;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JSONResponseTasaVariable implements Serializable {

    private JSONResponseStatus status;

    private String requestUrl;

    private ParametersTasaVariable parameters;

    private OplHisTasaVariableEntity result;

    private static final long serialVersionUID = 1L;

}