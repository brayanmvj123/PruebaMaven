package com.bdb.opalogdoracle.persistence.model.servicecancel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransaction implements Serializable {

    private Integer tipoPago;
    private BigDecimal valorAbono;

}
