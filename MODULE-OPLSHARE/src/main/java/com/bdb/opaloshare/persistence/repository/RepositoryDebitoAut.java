package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.HisDebitoAutEntity;
import com.bdb.opaloshare.persistence.entity.HisDebitoAutPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryDebitoAut extends JpaRepository<HisDebitoAutEntity, HisDebitoAutPk> {
}
