package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.HisRenovaCdtEntity;
import com.bdb.opaloshare.persistence.entity.HisRenovaCdtPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryHisRenovaCdt extends JpaRepository<HisRenovaCdtEntity, HisRenovaCdtPK> {

    boolean existsByCdtAct(Long numCdt);

    HisRenovaCdtEntity findByCdtAct(Long numCdt);

    Long countByCdtOrigen(Long numCdt);

    boolean existsByCdtAnt(Long numCdtAnt);

}
