package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.TipbaseParDownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface RepositoryParTipBase extends JpaRepository<TipbaseParDownEntity, Serializable> {
}
