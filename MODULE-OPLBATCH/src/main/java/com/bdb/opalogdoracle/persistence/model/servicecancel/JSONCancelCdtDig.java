package com.bdb.opalogdoracle.persistence.model.servicecancel;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JSONCancelCdtDig implements Serializable {

    private String codIsin;
    private Long numCdt;
    private String tipId;
    private String numTit;
    private String nomTit;
    private BigDecimal capPg;
    private BigDecimal intBruto;
    private BigDecimal rteFte;
    private BigDecimal intNeto;
    private String formaPago;

}
