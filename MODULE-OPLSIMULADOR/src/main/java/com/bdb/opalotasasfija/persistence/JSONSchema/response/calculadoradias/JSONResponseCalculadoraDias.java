package com.bdb.opalotasasfija.persistence.JSONSchema.response.calculadoradias;

import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResponseStatus;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JSONResponseCalculadoraDias<T,Q,W> {

    private T status;
    private String requestUrl;
    private Q parameters;
    private W result;

}
