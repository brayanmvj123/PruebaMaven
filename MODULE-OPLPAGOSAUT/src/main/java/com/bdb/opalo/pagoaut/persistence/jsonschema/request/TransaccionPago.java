package com.bdb.opalo.pagoaut.persistence.jsonschema.request;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionPago implements Serializable {

    private Integer tipoPago;
    private BigDecimal valorAbono;

}
