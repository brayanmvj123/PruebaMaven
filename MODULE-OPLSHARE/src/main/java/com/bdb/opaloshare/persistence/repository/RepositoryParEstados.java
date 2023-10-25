package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.TipEstadosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryParEstados extends JpaRepository<TipEstadosEntity, Integer> {
}
