package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.TipDeparEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

public interface RepositoryTipDepar extends JpaRepository<TipDeparEntity, Serializable> {

    @Query(value = "select dep.codDep " +
            "from TipDeparEntity dep " +
            "inner join TipPaisEntity pais on pais.codPais = dep.oplTippaisTblCodPais " +
            "where dep.homoCrm = :valorCrmDep and pais.homoCrm = :valorCrmPais")
    String codigoObtenidoDep(@Param("valorCrmDep") String valorCrmDep , @Param("valorCrmPais") String valorCrmPais);

    TipDeparEntity findByHomoCrmAndDesDep(String codCrmDep,String desDep);

    List<TipDeparEntity> findByHomoCrmAndOplTippaisTblCodPais(String codigoDep, Integer codigoPais);

}
