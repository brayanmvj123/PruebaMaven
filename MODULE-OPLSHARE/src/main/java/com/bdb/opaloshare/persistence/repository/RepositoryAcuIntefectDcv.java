package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.AcuIntefectDcvEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RepositoryAcuIntefectDcv extends JpaRepository<AcuIntefectDcvEntity,Long> {

    Page<AcuIntefectDcvEntity> findBySysCargueBetween(LocalDate inicio, LocalDate fin, Pageable pageable);

}
