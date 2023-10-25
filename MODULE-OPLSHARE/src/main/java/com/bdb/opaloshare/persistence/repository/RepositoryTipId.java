package com.bdb.opaloshare.persistence.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bdb.opaloshare.persistence.entity.TipidParDownEntity;

public interface RepositoryTipId extends JpaRepository<TipidParDownEntity, Serializable> {

	public List<TipidParDownEntity> findByHomoDcvbtaNot(String codigo);

	TipidParDownEntity findByHomoDcvsa(String codigo);
	
}
