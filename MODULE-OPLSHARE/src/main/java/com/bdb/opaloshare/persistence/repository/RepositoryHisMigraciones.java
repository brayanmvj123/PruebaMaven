package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.HisMigracionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryHisMigraciones extends JpaRepository<HisMigracionesEntity, Long> {
}
