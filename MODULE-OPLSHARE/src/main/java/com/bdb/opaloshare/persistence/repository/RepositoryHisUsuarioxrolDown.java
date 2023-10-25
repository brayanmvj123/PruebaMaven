package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.HisLoginDownEntity;
import com.bdb.opaloshare.persistence.entity.HisUsuarioxrolDownEntity;
import com.bdb.opaloshare.persistence.entity.ParRolesxgdaDownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
@Repository
public interface RepositoryHisUsuarioxrolDown extends JpaRepository<HisUsuarioxrolDownEntity, Long> {

    /**
     * Get a register with rol by user.
     *
     * @param login user login
     * @param rol   role
     * @return role by user
     */
    HisUsuarioxrolDownEntity findByLoginAndRol(HisLoginDownEntity login, ParRolesxgdaDownEntity rol);

    /**
     * Find all relations between roles and user using user id.
     *
     * @param user user
     * @return list of relations.
     */
    List<HisUsuarioxrolDownEntity> findAllByLogin(HisLoginDownEntity user);

    /**
     * Delete all role relations by user
     *
     * @param user user login
     */
    void deleteAllByLogin(HisLoginDownEntity user);
}
