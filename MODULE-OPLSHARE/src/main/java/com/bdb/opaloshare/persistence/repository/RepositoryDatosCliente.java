package com.bdb.opaloshare.persistence.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bdb.opaloshare.persistence.entity.HisClientesLargeEntity;

@Repository
public interface RepositoryDatosCliente extends JpaRepository<HisClientesLargeEntity, Serializable> {

    @Query("select codPais from TipPaisEntity where homoCrm = :valor")
    String codigoPaisObtenido(@Param("valor") String valor);

    @Query("select distinct(cliente.numTit) from HisClientesLargeEntity cliente where cliente.numTit like :numTit and cliente.oplTipidTblCodId = :tipId ")
    List<String> cantidadClientesEncontrados(String numTit , Integer tipId);

    HisClientesLargeEntity findByNumTitAndOplTipidTblCodId(String numTit, Integer codId);

}
