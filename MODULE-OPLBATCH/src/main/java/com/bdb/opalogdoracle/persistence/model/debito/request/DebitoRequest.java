package com.bdb.opalogdoracle.persistence.model.debito.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DebitoRequest implements Serializable {

    private String tipoId;
    private String numeroId;
    private Long numCdt;
    private Integer oficinaOrigen;
    private Long numCta;
    private String tipoCta;
    private BigDecimal valor;

}
