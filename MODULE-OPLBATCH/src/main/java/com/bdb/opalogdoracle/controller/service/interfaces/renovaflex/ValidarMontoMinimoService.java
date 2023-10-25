package com.bdb.opalogdoracle.controller.service.interfaces.renovaflex;

import com.bdb.opaloshare.persistence.entity.HisCtrCdtsEntity;
import com.bdb.opaloshare.persistence.entity.SalRenautdigEntity;

import java.math.BigDecimal;

public interface ValidarMontoMinimoService {

    boolean montoMinimo(SalRenautdigEntity salRenautdigByNumCdt, HisCtrCdtsEntity hisCtrCdtsEntity, BigDecimal montoMinimo);

}
