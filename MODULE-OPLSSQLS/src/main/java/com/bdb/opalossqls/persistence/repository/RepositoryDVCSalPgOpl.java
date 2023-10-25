package com.bdb.opalossqls.persistence.repository;

import com.bdb.opalossqls.persistence.entity.DCVSalPgOpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


public interface RepositoryDVCSalPgOpl extends JpaRepository<DCVSalPgOpl, Serializable> {

}
