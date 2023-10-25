package com.bdb.opalossqls.persistence.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bdb.opalossqls.persistence.entity.OpaloPrueba;

@Repository
public interface RepositoryOpaloPrueba extends JpaRepository<OpaloPrueba, Serializable> {

	
}
