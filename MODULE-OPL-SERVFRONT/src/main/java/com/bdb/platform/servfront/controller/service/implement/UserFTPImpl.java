package com.bdb.platform.servfront.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.ParUserconexDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryParUserconexDown;
import com.bdb.platform.servfront.controller.service.interfaces.UserFTPService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
@CommonsLog
public class UserFTPImpl implements UserFTPService {
    @Autowired
    private RepositoryParUserconexDown ftpUsers;

    /**
     * Find user exists.
     *
     * @param username User name to find
     * @return User FTP
     */
    @Override
    public ParUserconexDownEntity findUser(String username) {
        ParUserconexDownEntity user = null;

        if (!ftpUsers.findByNombreUsuario(username).isEmpty()) {
            user = ftpUsers.findByNombreUsuario(username).get(0);
        }

        return user;
    }

    /**
     * Save FTP User to database.
     *
     * @param user FTP User object
     * @return User FTP
     */
    @Override
    public ParUserconexDownEntity saveUser(ParUserconexDownEntity user) {
        return ftpUsers.save(user);
    }


    /**
     * Find all FTP users.
     *
     * @return List of FTP Users
     */
    @Override
    public List<ParUserconexDownEntity> findAll() {
        return ftpUsers.findAll();
    }


    /**
     * Find one FTP User by ID
     *
     * @param id user id
     * @return FTP User
     */
    @Override
    public ParUserconexDownEntity getOne(Long id) {
        return ftpUsers.getOne(id);
    }


    /**
     * Check if exists
     *
     * @param id user id
     * @return true or false
     */
    @Override
    public boolean exists(Long id) {
        return ftpUsers.existsById(id);
    }


    /**
     * Check if user exists in host
     *
     * @param username Username
     * @param hostIp   IP
     * @return true or false
     */
    @Override
    public boolean usernameInHost(String username, String hostIp) {
        return false;
    }
}
