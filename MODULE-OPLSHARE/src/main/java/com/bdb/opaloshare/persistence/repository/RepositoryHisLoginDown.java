package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.HisLoginDownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
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
public interface RepositoryHisLoginDown extends JpaRepository<HisLoginDownEntity, Serializable> {

    /**
     * Get user by username
     *
     * @param usuario username
     * @return object
     */
    HisLoginDownEntity findByUsuario(String usuario);

    /**
     * Check if user exists
     *
     * @param usuario username
     * @return boolean
     */
    boolean existsByUsuario(String usuario);

    /**
     * Search users by usuario criteria
     *
     * @param criteria search criteria
     * @return list of users.
     */
    List<HisLoginDownEntity> findAllByUsuarioLike(String criteria);

    @Query(value="SELECT LOGIN.ITEM, LOGIN.USUARIO, LOGIN.NOMBRES, LOGIN.APELLIDOS, LOGIN.FECHA_CONEXION, LOGIN.ESTADO, LOGIN.TOKEN, LOGIN.IDENTIFICACION FROM OPL_HIS_LOGIN_DOWN_TBL LOGIN, OPL_PAR_VARENTORNO_DOWN_TBL VARENTORNO WHERE VARENTORNO.DESC_VARIABLE = 'CANT_DIAS_BLOQUEO_USR' AND sysdate - LOGIN.FECHA_CONEXION > to_number(VARENTORNO.VAL_VARIABLE) AND estado = 1", nativeQuery=true)
    List<HisLoginDownEntity> findByUserConnectionDate();

}
