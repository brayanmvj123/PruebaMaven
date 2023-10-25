package com.bdb.opalossqls.persistence.repository;

import com.bdb.opalossqls.persistence.entity.DCVSalRenautdigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface RepositoryDCVSalRenautdig extends JpaRepository<DCVSalRenautdigEntity, Serializable> {

}
