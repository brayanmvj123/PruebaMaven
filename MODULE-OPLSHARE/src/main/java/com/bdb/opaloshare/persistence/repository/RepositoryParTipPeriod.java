package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.TipPeriodParDownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface RepositoryParTipPeriod extends JpaRepository<TipPeriodParDownEntity, Serializable> {
}
