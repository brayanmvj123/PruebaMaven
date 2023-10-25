package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.ParRolesxgdaDownEntity;
import com.bdb.opaloshare.persistence.entity.ParVistasxrolDownEntity;
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
public interface RepositoryParVistasxrolDown extends JpaRepository<ParVistasxrolDownEntity, Long> {

    /**
     * Find all relations between roles and views using role id
     *
     * @param role role object
     * @return list of relations
     */
    List<ParVistasxrolDownEntity> findAllByRol(ParRolesxgdaDownEntity role);
}
