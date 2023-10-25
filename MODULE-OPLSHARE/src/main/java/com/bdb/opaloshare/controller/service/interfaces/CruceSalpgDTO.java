package com.bdb.opaloshare.controller.service.interfaces;

import java.math.BigDecimal;

public interface CruceSalpgDTO {

    Long getNumCdt();

    String getCodIsin();

    String getNumTit();

    String getNomTit();

    BigDecimal getIntBruto();

    BigDecimal getRteFte();

    BigDecimal getIntNeto();

    BigDecimal getTotalPagar();

    BigDecimal getCapPg();

    String getTipId();

    Long getNroOficina();

    String getTipCta();

    String getNroCta();

    Integer getTipoTran();

    String getNomOficina();
}
