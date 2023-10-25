package com.bdb.platform.auth.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.HisLoginDownEntity;
import com.bdb.opaloshare.persistence.entity.HisUsuarioxrolDownEntity;
import com.bdb.opaloshare.persistence.entity.ParRolesxgdaDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryHisUsuarioxrolDown;
import com.bdb.platform.auth.controller.service.interfaces.RoleByUserService;
import lombok.extern.apachecommons.CommonsLog;
import org.dom4j.tree.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaDelete;
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
@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
@CommonsLog
public class RoleByuserImpl implements RoleByUserService {

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private RepositoryHisUsuarioxrolDown repository;

    /**
     * Find specific role by user relation
     *
     * @param user user
     * @param rol  role
     * @return role by user
     */
    @Override
    public HisUsuarioxrolDownEntity userByRol(HisLoginDownEntity user, ParRolesxgdaDownEntity rol) {
        return repository.findByLoginAndRol(user, rol);
    }

    /**
     * Save user into local repository
     *
     * @param user user
     * @return user
     */
    @Override
    public HisUsuarioxrolDownEntity save(HisUsuarioxrolDownEntity user) {
        return repository.save(user);
    }

    /**
     * Get all roles by user relations using user id
     *
     * @param user login user
     * @return list of relations.
     */
    @Override
    public List<HisUsuarioxrolDownEntity> rolesByUser(HisLoginDownEntity user) {
        return repository.findAllByLogin(user);
    }

    /**
     * Delete all user roles to update
     *
     * @param user login user
     */
    @Override
    public void deleteAll(HisLoginDownEntity user) {
        repository.deleteAllByLogin(user);
    }

    /**
     * Delete all user roles to update
     *
     * @param clazz class to delete
     */
    @Override
    public final <T extends AbstractEntity> int deleteAll(Class<T> clazz) {
        CriteriaDelete<T> criteriaDelete = entityManager.getCriteriaBuilder().createCriteriaDelete(clazz);
        criteriaDelete.from(clazz);

        return entityManager.createQuery(criteriaDelete).executeUpdate();
    }
}
