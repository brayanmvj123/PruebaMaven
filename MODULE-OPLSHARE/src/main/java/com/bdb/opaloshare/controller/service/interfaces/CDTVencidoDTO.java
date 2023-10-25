package com.bdb.opaloshare.controller.service.interfaces;

import java.math.BigDecimal;

public interface CDTVencidoDTO {

    String getPlazo();

    String getTipPlazo();

    Long getNumCdt();

    String getFechaVen();

    String getTipProd();

    String getTipId();

    String getNumTit();

    String getNomTit();

    BigDecimal getCapPg();

    BigDecimal getTotalPagar();

    BigDecimal getIntTotal();

    BigDecimal getRteFte();

    BigDecimal getIntNeto();

    Integer getTipTran();

    Integer getTipProcces();

    Integer getOficina();

    String getNomOficina();

    String getCodIsin();

    BigDecimal getIntBruto();

    Long getCtaInv();

    Integer getRelCta();
}
