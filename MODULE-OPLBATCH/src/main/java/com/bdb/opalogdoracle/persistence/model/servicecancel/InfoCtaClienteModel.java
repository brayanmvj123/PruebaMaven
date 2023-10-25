package com.bdb.opalogdoracle.persistence.model.servicecancel;

import com.bdb.opalogdoracle.persistence.model.servicecancel.PaymentTransaction;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoCtaClienteModel implements Serializable {

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
    private List< PaymentTransaction > transaccionPagoList;

}
