package com.bdb.opalo.cofnal.persistance.model.repository;

import com.bdb.opalo.cofnal.persistance.model.entity.TasaVariableCofnalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface RepositoryTasaVariableCofnal extends JpaRepository<TasaVariableCofnalEntity, Serializable> {

    List<TasaVariableCofnalEntity> findAllByFechaBetween(String fechaInicio, String fechaFin);
}
