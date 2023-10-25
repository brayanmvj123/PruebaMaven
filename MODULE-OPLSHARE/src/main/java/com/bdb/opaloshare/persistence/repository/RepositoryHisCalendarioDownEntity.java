package com.bdb.opaloshare.persistence.repository;


import com.bdb.opaloshare.persistence.entity.OplHisCalendarioDownEntity;
import com.bdb.opaloshare.persistence.entity.OplHisCalendarioDownIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryHisCalendarioDownEntity extends JpaRepository<OplHisCalendarioDownEntity, OplHisCalendarioDownIdEntity> {

    List<OplHisCalendarioDownEntity> findByFechaAnnoAndValor(Integer anno,Integer fecha_valor);
}
