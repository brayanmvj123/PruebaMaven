package com.bdb.platform.servfront.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.ParConexionesDownEntity;

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
public interface FTPConnectionService {

    /**
     * Get connection by ID
     *
     * @param id Connection ID
     * @return Connection Object
     */
    ParConexionesDownEntity getOne(Long id);

    /**
     * Save Connection
     *
     * @param connection Connection object
     * @return created object
     */
    ParConexionesDownEntity save(ParConexionesDownEntity connection);

    /**
     * Check if connection exists
     *
     * @param id user id
     * @return true or false
     */
    boolean exists(Long id);

    /**
     * Check if user exists in host
     *
     * @param username Username
     * @param ip       IP
     * @return true or false
     */
    boolean usernameInHost(String username, String ip);
}
