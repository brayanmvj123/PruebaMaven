package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.TipCIIUEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

public interface RepositoryTipCIUU extends JpaRepository<TipCIIUEntity, Serializable> {

    @Query("select codCiiu from TipCIIUEntity where homoCrm = :valor")
    String codigoObtenidoCIIU(@Param("valor") String valor);

}
