package com.bdb.platform.auth.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.VarentornoDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import com.bdb.platform.auth.controller.service.interfaces.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
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
public class EnvironmentImpl implements EnvironmentService {

    @Autowired
    private RepositoryTipVarentorno repository;

    /**
     * Get environment variable by description
     *
     * @param desc description
     * @return get object
     */
    @Override
    public VarentornoDownEntity getByDesc(String desc) {
        return repository.findByDescVariable(desc);
    }
}
