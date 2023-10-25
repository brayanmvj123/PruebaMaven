package com.bdb.opalo.pagoaut.persistence.jsonschema.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTranpg implements Serializable {

    private String tipPayment;
    private String notification;
    private String result;

}
