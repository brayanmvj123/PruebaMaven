package com.bdb.opaloshare.persistence.model.trad;

import java.math.BigDecimal;

public interface CruceHisCdtRenautDigDto {

    Long getCliente();

    Long getNumCdt();

    Integer getTipTransaccion();

    String getProceso();

    String getTransaccion();

    String getUnidadDestino();

    String getUnidadOrigen();

    BigDecimal getValor();

    BigDecimal getGmf();

    String getCodTrn();

    Integer getOplTipPlazo();

    Integer getPlazo();

    Integer getOficina();

    Integer getGmfCapital();

    Long getCodProd();

}
