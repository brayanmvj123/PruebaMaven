package com.bdb.moduleoplcancelaciones.persistence.JSONSchema.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionPago implements Serializable {

    private Integer tipoPago;
    private BigDecimal valorAbono;

}
