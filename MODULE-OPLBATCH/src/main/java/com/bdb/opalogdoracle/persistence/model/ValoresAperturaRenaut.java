package com.bdb.opalogdoracle.persistence.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ValoresAperturaRenaut implements Serializable {

    private BigDecimal tasa;
    private String signoSpread;
    private LocalDate fechaVencimiento;

}
