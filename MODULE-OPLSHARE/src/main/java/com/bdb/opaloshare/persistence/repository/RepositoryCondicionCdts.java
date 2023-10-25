package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.HisCondicionCdtsEntity;
import com.bdb.opaloshare.persistence.entity.HisCondicionCdtsPK;
import com.bdb.opaloshare.persistence.entity.HisCtrCdtsPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCondicionCdts extends JpaRepository<HisCondicionCdtsEntity, HisCondicionCdtsPK> {
}
