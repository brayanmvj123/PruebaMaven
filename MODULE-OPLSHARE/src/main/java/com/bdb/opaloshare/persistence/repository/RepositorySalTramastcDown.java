package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.SalTramastcDownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Copyright (c) 2019 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@Repository
public interface RepositorySalTramastcDown extends JpaRepository<SalTramastcDownEntity, Serializable> {

    @Transactional
    @Modifying
    void deleteByTramaContainingAndFecha( String palabra , String fecha );

}
