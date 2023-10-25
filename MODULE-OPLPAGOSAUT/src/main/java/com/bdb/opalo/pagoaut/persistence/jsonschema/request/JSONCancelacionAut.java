package com.bdb.opalo.pagoaut.persistence.jsonschema.request;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JSONCancelacionAut implements Serializable {

    private String tipoId;
    private String numeroId;
    private String nombreBeneficiario;
    private Long numCdt;
    private String codIsin;
    private Integer oficinaOrigen;
    private Long ctaInv;
    private Integer tipoCtaInv;
    private Long numCta;
    private Integer tipoCta;
    private Integer oficinaRadic;
    private Integer tipoPago;
    private BigDecimal valorAbono;
    private List<TransaccionPago> transaccionPagoList;

}
