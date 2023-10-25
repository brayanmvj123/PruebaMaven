package com.bdb.opalogdoracle.controller.service.interfaces.renovaflex;

import com.bdb.opaloshare.persistence.entity.SalRenautdigEntity;

import java.math.BigDecimal;

public interface CapitalService {

    BigDecimal capital(SalRenautdigEntity cdt, String endpoint) throws Exception;

}
