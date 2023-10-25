package com.bdb.opaloshare.persistence.model.response.calculadoradias;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JSONResponseCalculadoraDias<T,Q,W> {

    private T status;

    private String requestUrl;

    private Q parameters;

    private W result;

}
