package com.bdb.platform.servfront.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.ParConexionesDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryParConexionesDown;
import com.bdb.platform.servfront.controller.service.interfaces.FTPConnectionService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) 2019 Banco de Bogotá. All Rights Reserved.
 * <p>
 * ACCIONESBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@Service
@CommonsLog
public class FTPConnectionImpl implements FTPConnectionService {

    @Autowired
    private RepositoryParConexionesDown ftpConnection;

    /**
     * Get connection by ID
     *
     * @param id Connection ID
     * @return Connection Object
     */
    @Override
    public ParConexionesDownEntity getOne(Long id) {
        return ftpConnection.getOne(id);
    }

    /**
     * Save Connection
     *
     * @param connection Connection object
     * @return created object
     */
    @Override
    public ParConexionesDownEntity save(ParConexionesDownEntity connection) {
        return ftpConnection.save(connection);
    }

    /**
     * Check if connection exists
     *
     * @param id user id
     * @return true or false
     */
    @Override
    public boolean exists(Long id) {
        return ftpConnection.existsById(id);
    }

    /**
     * Check if user exists in host
     *
     * @param username Username
     * @param ip       IP
     * @return true or false
     */
    @Override
    public boolean usernameInHost(String username, String ip) {
        return false;
    }
}
