package com.bdb.platform.servfront.controller.service.interfaces;


import com.bdb.opaloshare.persistence.entity.ParUserconexDownEntity;

import java.util.List;

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
public interface UserFTPService {

    /**
     * Find user exists.
     *
     * @param username User name to find
     * @return User FTP
     */
    ParUserconexDownEntity findUser(String username);

    /**
     * Save FTP User to database.
     *
     * @param user FTP User object
     * @return User FTP
     */
    ParUserconexDownEntity saveUser(ParUserconexDownEntity user);

    /**
     * Find all FTP users.
     *
     * @return List of FTP Users
     */
    List<ParUserconexDownEntity> findAll();

    /**
     * Find one FTP User by ID
     *
     * @param id user id
     * @return FTP User
     */
    ParUserconexDownEntity getOne(Long id);

    /**
     * Check if exists
     *
     * @param id user id
     * @return true or false
     */
    boolean exists(Long id);

    /**
     * Check if user exists in host
     *
     * @param username Username
     * @param hostIp   IP
     * @return true or false
     */
    boolean usernameInHost(String username, String hostIp);
}
