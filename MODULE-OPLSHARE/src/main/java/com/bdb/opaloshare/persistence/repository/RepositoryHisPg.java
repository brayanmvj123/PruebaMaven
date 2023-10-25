package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.HisPgEntity;
import com.bdb.opaloshare.persistence.entity.HisPgEntityPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryHisPg extends JpaRepository<HisPgEntity, HisPgEntityPk> {
}
