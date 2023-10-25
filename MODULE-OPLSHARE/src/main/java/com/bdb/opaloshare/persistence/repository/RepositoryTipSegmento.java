package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.TipSegmentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

public interface RepositoryTipSegmento extends JpaRepository<TipSegmentoEntity, Serializable> {

    @Query("select codSegmento from TipSegmentoEntity where homoCrm= :valor")
    String codigoObtenidoSegmento(@Param("valor") String valor);

}
