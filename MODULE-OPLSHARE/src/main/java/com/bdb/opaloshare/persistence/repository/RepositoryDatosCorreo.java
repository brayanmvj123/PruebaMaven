package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.OplParCorreoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface RepositoryDatosCorreo extends JpaRepository<OplParCorreoEntity, Serializable> {

    OplParCorreoEntity findByIdPerfil(Integer idPerfil);

}
