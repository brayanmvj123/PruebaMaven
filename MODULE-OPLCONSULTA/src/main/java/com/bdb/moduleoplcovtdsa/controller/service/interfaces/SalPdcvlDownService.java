package com.bdb.moduleoplcovtdsa.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.SalPdcvlDownEntity;

import java.util.List;

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
public interface SalPdcvlDownService {
    List<SalPdcvlDownEntity> consultaCdtsDia();
}
