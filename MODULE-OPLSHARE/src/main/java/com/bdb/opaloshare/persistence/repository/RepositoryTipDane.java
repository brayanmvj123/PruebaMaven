package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.TipDaneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

public interface RepositoryTipDane extends JpaRepository<TipDaneEntity, Serializable> {

    @Query("select codDane from TipDaneEntity where homoCrm = :valor ")
    Integer codigoObtenidoDane(@Param("valor") String valor);

}
