package com.bdb.traductor.deceapdi.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.HisTraductoresDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryHisTraductoresDown;
import com.bdb.traductor.deceapdi.controller.service.interfaces.HisTraductoresDownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class HisTraductoresDownImplt implements HisTraductoresDownService {

    @Autowired
    private RepositoryHisTraductoresDown traductoresDown;

    @Override
    public HisTraductoresDownEntity getByTrad(String aplFuente, String transac) {
        return traductoresDown.findByAplFuenteAndCodTran(aplFuente, transac);
    }
}
