package com.bdb.platform.auth.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.HisLoginDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryHisLoginDown;
import com.bdb.platform.auth.controller.service.interfaces.UserLoginService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
public class LoginUserImpl implements UserLoginService {

    @Autowired
    private RepositoryHisLoginDown repositoryHisLoginDown;

    /**
     * Find user by username.
     *
     * @param username Username from logging.
     * @return User object entity
     */
    @Override
    public HisLoginDownEntity findUser(String username) {
        return repositoryHisLoginDown.findByUsuario(username);
    }

    /**
     * Save user to local repository.
     *
     * @param user User entity
     * @return created user
     */
    @Override
    public HisLoginDownEntity saveUser(HisLoginDownEntity user) {
        return repositoryHisLoginDown.save(user);
    }

    /**
     * Update user in local repository.
     *
     * @param user exists user in Active Directory and local repository
     */
    @Override
    public void updateUser(HisLoginDownEntity user) {
        // Find user
        Optional<HisLoginDownEntity> u = repositoryHisLoginDown.findById(user.getItem());

        // Change info
        u.ifPresent(us -> {

            // Set basic info
            us.setToken(user.getToken());
            us.setApellidos(user.getApellidos());
            us.setNombres(user.getNombres());
            us.setUsuarioXroles(user.getUsuarioXroles());

            // Save to database
            repositoryHisLoginDown.save(us);
        });
    }

    /**
     * Check if user exists
     *
     * @param username username
     * @return true or false
     */
    @Override
    public boolean userExists(String username) {
        return repositoryHisLoginDown.existsByUsuario(username);
    }

    /**
     * Search user by criteria
     *
     * @param criteria criteria to search
     * @return list of users
     */
    @Override
    public List<HisLoginDownEntity> getUsers(String criteria) {
        return repositoryHisLoginDown.findAllByUsuarioLike(criteria);
    }

    /**
     * Get all users
     *
     * @return list of users
     */
    @Override
    public List<HisLoginDownEntity> getAllUsers() {
        return repositoryHisLoginDown.findAll();
    }
}
