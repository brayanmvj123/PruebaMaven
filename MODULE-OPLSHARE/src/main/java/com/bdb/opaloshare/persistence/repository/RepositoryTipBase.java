package com.bdb.opaloshare.persistence.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bdb.opaloshare.persistence.entity.TipbaseParDownEntity;

public interface RepositoryTipBase extends JpaRepository<TipbaseParDownEntity, Serializable>{

	public List<TipbaseParDownEntity> findByHomoDcvbtaNot(String codigo);
}
