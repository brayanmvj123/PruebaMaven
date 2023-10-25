package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.HisTranpgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OplHisTranpgDownTbl extends JpaRepository<HisTranpgEntity, Long> {
}
