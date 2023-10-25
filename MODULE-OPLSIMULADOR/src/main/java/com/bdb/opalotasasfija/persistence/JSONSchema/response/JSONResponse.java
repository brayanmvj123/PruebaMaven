package com.bdb.opalotasasfija.persistence.JSONSchema.response;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JSONResponse<T,Q,W> {

    private T status;
    private String requestUrl;
    private Q parameters;
    private W result;

}
