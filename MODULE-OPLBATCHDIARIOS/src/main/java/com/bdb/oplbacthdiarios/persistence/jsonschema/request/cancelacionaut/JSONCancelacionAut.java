package com.bdb.oplbacthdiarios.persistence.jsonschema.request.cancelacionaut;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
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
    private List<TransaccionPago> transaccionPagoList;

}
