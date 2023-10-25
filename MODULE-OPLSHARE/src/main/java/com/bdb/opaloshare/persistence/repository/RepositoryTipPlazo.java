package com.bdb.opaloshare.persistence.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bdb.opaloshare.persistence.entity.TipplazoParDownEntity;

public interface RepositoryTipPlazo extends JpaRepository<TipplazoParDownEntity, Serializable> {

	//public List<TipplazoParDownEntity> findByHomoDcvbta();
}
