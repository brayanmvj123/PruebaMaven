package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.TipPeriodParDownEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface RepositoryTipPeriodicidad extends JpaRepository<TipPeriodParDownEntity, Serializable> {

    TipPeriodParDownEntity findByTipPeriodicidad(Integer periodicidad);

}
