package com.bdb.opalogdoracle.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NuevasCondicionesCdtModel implements Serializable {

    private BigDecimal capital;
    private BigDecimal rendimientos;
    private Integer tipPlazo;
    private Integer plazo;
    private Integer base;
    private String codProd;

}
