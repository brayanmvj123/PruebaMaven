package com.bdb.traductor.deceapdi.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.SalTramastcDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositorySalTramastcDown;
import com.bdb.traductor.deceapdi.controller.service.interfaces.SalTramastcDownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
public class SalTramastcDownImplt implements SalTramastcDownService {

    @Autowired
    private RepositorySalTramastcDown repositoryCDTSLarge;

    @Override
    public void saveAll(List<SalTramastcDownEntity> entities) {
        repositoryCDTSLarge.saveAll(entities);
    }
}
