package com.bdb.platform.auth.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.ParEndpointDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryParEndpointDown;
import com.bdb.platform.auth.controller.service.interfaces.WsdlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@SuppressWarnings("ALL")
@Service
public class WsdlImpl implements WsdlService {

    @Autowired
    private RepositoryParEndpointDown repository;

    /**
     * Get WSDL Endpoint by ID.
     *
     * @param id id to request
     * @return WSDL Endpoint.
     */
    @Override
    public ParEndpointDownEntity getById(Long id) {
        return repository.getOne(id);
    }
}
