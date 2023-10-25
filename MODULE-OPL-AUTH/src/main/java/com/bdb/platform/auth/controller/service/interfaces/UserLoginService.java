package com.bdb.platform.auth.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.HisLoginDownEntity;

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
public interface UserLoginService {
    /**
     * Find login user in database.
     * <p>
     * This is only created or searched in database if exists in Active Directory Single Sign on process.
     *
     * @param username Username from logging.
     * @return A single user entity logged.
     */
    HisLoginDownEntity findUser(String username);

    /**
     * Save user to local repository
     *
     * @param user User entity
     * @return Status result
     */
    HisLoginDownEntity saveUser(HisLoginDownEntity user);

    /**
     * Do update process to exists user to set token or change basic information.
     *
     * @param user exists user in Active Directory and local repository
     */
    void updateUser(HisLoginDownEntity user);

    /**
     * Check if user exists
     *
     * @param username username
     * @return true or false
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    boolean userExists(String username);

    /**
     * Search user by criteria
     *
     * @param criteria criteria to search
     * @return list of users
     */
    List<HisLoginDownEntity> getUsers(String criteria);

    /**
     * Get all users
     *
     * @return list of users
     */
    List<HisLoginDownEntity> getAllUsers();
}
