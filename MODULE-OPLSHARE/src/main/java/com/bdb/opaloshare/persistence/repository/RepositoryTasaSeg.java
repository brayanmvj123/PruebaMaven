package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.TmpTasaSegEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryTasaSeg extends JpaRepository<TmpTasaSegEntity, Long> {

    TmpTasaSegEntity findBySegmento(Integer segmento);
}
