package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.TiptasaParDownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface RepositoryParTipTasa extends JpaRepository<TiptasaParDownEntity, Serializable> {
    List<TiptasaParDownEntity> findByDescTasaNotOrderByTipTasaAsc(String descTasa);
}
