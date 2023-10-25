package com.bdb.platform.auth.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.ParRolesxgdaDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryParRolesxgdaDown;
import com.bdb.platform.auth.controller.service.interfaces.RolesService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBF was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@Service
@CommonsLog
public class RolesImpl implements RolesService {

    @Autowired
    private RepositoryParRolesxgdaDown repository;

    /**
     * Get all roles in a set of criteria
     *
     * @param roles roles
     * @return list of exists roles
     */
    @Override
    public List<ParRolesxgdaDownEntity> findRoles(List<String> roles) {
        return repository.findByGrupoDaIn(roles);
    }
}
