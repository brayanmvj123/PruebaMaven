package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.OplHisTasaVariableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

import java.util.List;
import java.util.Optional;

public interface RepositoryOplTasaVariable extends JpaRepository<OplHisTasaVariableEntity, Serializable> {

    Optional<OplHisTasaVariableEntity> findByIdTipotasaTipTasaAndIdFecha(int tipotasa, int fecha);

    List<OplHisTasaVariableEntity> findAllByIdTipotasaTipTasa(int tipotasa);

    List<OplHisTasaVariableEntity> findAllByIdFecha(int fecha);
}
