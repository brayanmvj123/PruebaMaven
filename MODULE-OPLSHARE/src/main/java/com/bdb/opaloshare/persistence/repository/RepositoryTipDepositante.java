package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.TipDepositanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

public interface RepositoryTipDepositante extends JpaRepository<TipDepositanteEntity, Serializable> {

    @Query("select tipDepositante from TipDepositanteEntity where homoDcvsa = :valor")
    Integer conocerDepositante(@Param("valor") String valor);

}
