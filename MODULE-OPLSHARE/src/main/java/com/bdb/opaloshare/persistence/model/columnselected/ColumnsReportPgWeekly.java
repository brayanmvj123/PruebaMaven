package com.bdb.opaloshare.persistence.model.columnselected;

import java.math.BigDecimal;

public interface ColumnsReportPgWeekly {

     Long getItem();
     Long getNroOficina();
     String getDepositante();
     Long getNumCdt();
     String getCodIsin();
     String getCtaInv();
     Integer getCodId();
     String getNumTit();
     String getNomTit();
     String getFechaEmi();
     String getFechaVen();
     String getFechaProxPg();
     String getTipPlazo();
     Long getPlazo();
     String getTipBase();
     String getTipPeriodicidad();
     String getTipTasa();
     BigDecimal getTasaEfe();
     BigDecimal getTasaNom();
     BigDecimal getSpread();
     BigDecimal getValorNominal();
     BigDecimal getIntBruto();
     BigDecimal getRteFte();
     BigDecimal getIntNeto();
     BigDecimal getCapPg();
     BigDecimal getTotalPagar();
     Long getTipPosicion();
     String getFactorDcvsa();
     BigDecimal getFactorOpl();
     Long getCodProd();
     String getDescPosicion();
     String getConciliacion();
     Integer getEstado();
     String getFecha();
     Integer getEnviado();
 }

