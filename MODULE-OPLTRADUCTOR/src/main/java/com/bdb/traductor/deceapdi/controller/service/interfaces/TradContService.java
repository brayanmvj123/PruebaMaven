package com.bdb.traductor.deceapdi.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.HisCDTSLargeEntity;
import com.bdb.opaloshare.persistence.entity.HisTranpgEntity;
import com.bdb.opaloshare.persistence.model.trad.CruceHisCdtRenautDigDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * Copyright (c) 2019 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
public interface TradContService {
    public List<HisCDTSLargeEntity> consultaAllCDTSLarge();

    public List<HisCDTSLargeEntity> consultaCDTSLargePorNumero(String numCdts);

    public List<HisCDTSLargeEntity> consultaCDTSLargeDia();

    List<CruceHisCdtRenautDigDto> consultaCDTSCadi();

    List<CruceHisCdtRenautDigDto> consultaCDTSCadiCancel();

    List<CruceHisCdtRenautDigDto> consultaCDTSCaOficina(Long numCdt, String codIsin, Long ctaInv);

    BigDecimal validarRenovacion(HisTranpgEntity transac);

}
