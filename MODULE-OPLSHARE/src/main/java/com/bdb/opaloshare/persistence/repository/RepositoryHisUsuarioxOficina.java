package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.HisUsuarioxOficinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RepositoryHisUsuarioxOficina extends JpaRepository<HisUsuarioxOficinaEntity, Long> {

    List<HisUsuarioxOficinaEntity> findAllByNroOficinaAndEstadoUsuario(Long nroOficina, Integer estadoUsuario);
    List<HisUsuarioxOficinaEntity> findAllByNroOficina(Long nroOficina);
    List<HisUsuarioxOficinaEntity> findAllByEstadoUsuario(Integer estadoUsuario);
}
