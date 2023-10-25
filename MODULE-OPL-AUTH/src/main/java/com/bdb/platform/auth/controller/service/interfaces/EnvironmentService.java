package com.bdb.platform.auth.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.VarentornoDownEntity;

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
public interface EnvironmentService {

    /**
     * Get environment variable by description
     * @param desc description
     * @return get object
     */
    VarentornoDownEntity getByDesc(String desc);
}
