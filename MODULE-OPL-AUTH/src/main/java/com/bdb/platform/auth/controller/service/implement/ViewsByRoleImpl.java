package com.bdb.platform.auth.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.ParRolesxgdaDownEntity;
import com.bdb.opaloshare.persistence.entity.ParVistasxrolDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryParVistasxrolDown;
import com.bdb.platform.auth.controller.service.interfaces.ViewsByRoleService;
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
public class ViewsByRoleImpl implements ViewsByRoleService {

    @Autowired
    private RepositoryParVistasxrolDown repository;

    /**
     * Get all relations between roles and views using role id
     *
     * @param role role object
     * @return list of relations
     */
    @Override
    public List<ParVistasxrolDownEntity> findByRole(ParRolesxgdaDownEntity role) {
        return repository.findAllByRol(role);
    }
}
