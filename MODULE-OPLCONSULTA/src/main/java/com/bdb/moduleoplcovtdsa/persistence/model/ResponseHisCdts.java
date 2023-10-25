package com.bdb.moduleoplcovtdsa.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHisCdts implements Serializable {

    private String numCDT;
    private BigDecimal vlrCDT;
    private LocalDate fechaVen;
    private LocalDate fechaProPago;
    private Integer tipTasa;
    private String desTasa;
    private LocalDate fechaEmi;
    private BigDecimal tasEfe;
    private TipoCDT tipoCDT;

}
