package com.bdb.opalo.cofnal.persistance.JSONSchema.response.tasaVariable;

import com.bdb.opalo.cofnal.persistance.JSONSchema.response.JSONResponseStatus;
import com.bdb.opalo.cofnal.persistance.model.entity.TasaVariableCofnalEntity;
import com.bdb.opaloshare.persistence.model.jsonschema.obtenerTasa.ParametersTasaVariableCofnal;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JSONResponseTasaVariableCofnal implements Serializable {

    private JSONResponseStatus status;
    private String requestUrl;
    private ParametersTasaVariableCofnal parameters;
    private List<TasaVariableCofnalEntity> result;

}
