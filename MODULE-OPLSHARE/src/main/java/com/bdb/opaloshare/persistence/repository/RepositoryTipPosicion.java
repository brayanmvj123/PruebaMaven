package com.bdb.opaloshare.persistence.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bdb.opaloshare.persistence.entity.TipposicionParDownEntity;

public interface RepositoryTipPosicion extends JpaRepository<TipposicionParDownEntity, Serializable> {

}
