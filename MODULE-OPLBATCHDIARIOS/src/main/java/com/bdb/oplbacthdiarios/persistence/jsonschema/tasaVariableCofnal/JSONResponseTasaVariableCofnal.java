package com.bdb.oplbacthdiarios.persistence.jsonschema.tasaVariableCofnal;

import com.bdb.opaloshare.persistence.model.response.simuladorcuota.JSONResponseStatus;
import com.bdb.oplbacthdiarios.persistence.model.TasaVariableCofnalModel;
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
