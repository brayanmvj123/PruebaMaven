package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.ParUserconexDownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
@Repository
public interface RepositoryParUserconexDown extends JpaRepository<ParUserconexDownEntity, Long> {

    List<ParUserconexDownEntity> findByNombreUsuario(String ndu);
}
