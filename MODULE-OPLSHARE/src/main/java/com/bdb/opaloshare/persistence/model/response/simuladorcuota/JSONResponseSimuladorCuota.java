package com.bdb.opaloshare.persistence.model.response.simuladorcuota;

import com.bdb.opaloshare.persistence.model.jsonschema.simuladorcuota.ParametersSimuladorCuota;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JSONResponseSimuladorCuota implements Serializable {

    private JSONResponseStatus status;

    private String requestUrl;

    private ParametersSimuladorCuota parameters;

    private JSONResultSimuladorCuota result;

    private static final long serialVersionUID = 1L;

}
