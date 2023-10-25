package com.bdb.platform.auth.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.HisLoginDownEntity;
import com.bdb.opaloshare.persistence.entity.HisUsuarioxrolDownEntity;
import com.bdb.opaloshare.persistence.entity.ParRolesxgdaDownEntity;
import org.dom4j.tree.AbstractEntity;

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
public interface RoleByUserService {
    /**
     * Find specific role by user relation
     *
     * @param user user
     * @param rol  role
     * @return role by user
     */
    HisUsuarioxrolDownEntity userByRol(HisLoginDownEntity user, ParRolesxgdaDownEntity rol);

    /**
     * Save user into local repository
     *
     * @param user user
     * @return user
     */
    HisUsuarioxrolDownEntity save(HisUsuarioxrolDownEntity user);

    /**
     * Get all roles by user relations using user id
     *
     * @param user login user
     * @return list of relations.
     */
    List<HisUsuarioxrolDownEntity> rolesByUser(HisLoginDownEntity user);

    /**
     * Delete all user roles to update
     *
     * @param user login user
     */
    void deleteAll(HisLoginDownEntity user);

    /**
     * Delete all user roles to update
     *
     * @param user login user
     * @return number of deleted items
     */
    <T extends AbstractEntity> int deleteAll(Class<T> user);
}
