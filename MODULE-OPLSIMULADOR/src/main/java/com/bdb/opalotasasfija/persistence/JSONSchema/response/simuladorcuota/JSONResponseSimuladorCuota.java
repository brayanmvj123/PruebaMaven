package com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota;

import com.bdb.opalotasasfija.persistence.JSONSchema.request.simuladorcuota.JSONRequestSimuladorCuota;
import com.bdb.opalotasasfija.persistence.JSONSchema.request.simuladorcuota.ParametersSimuladorCuota;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JSONResponseSimuladorCuota implements Serializable {

    private JSONResponseStatus status;
    private String requestUrl;
    private ParametersSimuladorCuota parameters;
    private JSONResultSimuladorCuota result;

}
