package com.bdb.opaloshare.persistence.model.response.tasaVariable;

import com.bdb.opaloshare.persistence.entity.OplHisTasaVariableEntity;
import com.bdb.opaloshare.persistence.model.jsonschema.tasaVariable.ParametersTasaVariable;
import com.bdb.opaloshare.persistence.model.response.simuladorcuota.JSONResponseStatus;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JSONResponseTasaVariableLista implements Serializable {

    private JSONResponseStatus status;
    private String requestUrl;
    private ParametersTasaVariable parameters;
    private List<OplHisTasaVariableEntity> result;

}