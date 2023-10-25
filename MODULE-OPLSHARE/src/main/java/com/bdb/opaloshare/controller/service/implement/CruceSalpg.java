package com.bdb.opaloshare.controller.service.implement;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CruceSalpg {

    private Long numCdt;

    private String codIsin;

    private String numTit;

    private String nomTit;

    private BigDecimal intBruto;

    private BigDecimal rteFte;

    private BigDecimal intNeto;

    private BigDecimal totalPagar;

    private BigDecimal capPg;

    private String tipId;

    private Long nroOficina;

    private String tipCta;

    private String nroCta;

    private Integer tipoTran;

    private String nomOficina;

    public String strucData(){
        return numCdt + "|" + codIsin + "|" + String.format("%6s", tipId) + "|" + String.format("%15s", numTit) + "|" +
                String.format("%45s", nomTit) + "|" + String.format("%1$25.4f", intBruto) + "|" +
                String.format("%1$25.4f", rteFte) + "|" + String.format("%1$25.4f", intNeto) + "|" +
                String.format("%1$25.4f", capPg) + "|" + String.format("%7s", tipCta.equals("Cuenta Corriente") ? "CC" : "CA") + "|" +
                String.format("%10s", nroCta) + "|" + String.format("%1$25.4f", totalPagar) + "|";
    }
}
