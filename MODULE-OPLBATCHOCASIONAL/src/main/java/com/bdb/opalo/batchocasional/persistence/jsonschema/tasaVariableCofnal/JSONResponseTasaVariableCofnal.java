package com.bdb.opalo.batchocasional.persistence.jsonschema.tasaVariableCofnal;

import com.bdb.opalo.batchocasional.persistence.model.TasaVariableCofnalModel;
import com.bdb.opaloshare.persistence.model.response.simuladorcuota.JSONResponseStatus;
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
    private List<TasaVariableCofnalModel> result;
}
