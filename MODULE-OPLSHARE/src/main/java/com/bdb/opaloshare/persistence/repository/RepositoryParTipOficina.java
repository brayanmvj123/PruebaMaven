package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.TipOficinaParDownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryParTipOficina extends JpaRepository<TipOficinaParDownEntity, Integer> {
}
