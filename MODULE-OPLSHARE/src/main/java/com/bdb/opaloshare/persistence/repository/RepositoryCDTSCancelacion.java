package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.HisCDTSCancelationEntity;
import com.bdb.opaloshare.persistence.entity.HisCDTSLargeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RepositoryCDTSCancelacion extends JpaRepository<HisCDTSCancelationEntity, Serializable> {

    List<HisCDTSCancelationEntity> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFinal);
}
