package com.bdb.opalogdoracle.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseQaRenovacion implements Serializable {

    private BigDecimal montoReinvertir;
    private BigDecimal tasaNominal;
    private String fechaVencimiento;
    private BigDecimal tasaEfectiva;
}
