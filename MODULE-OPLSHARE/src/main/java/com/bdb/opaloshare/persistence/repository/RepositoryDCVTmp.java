package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.OplMaeDCVTmpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Repository
public interface RepositoryDCVTmp extends JpaRepository<OplMaeDCVTmpEntity, Serializable> {
    public List<OplMaeDCVTmpEntity> findByOficina(String oficina);

    public List<OplMaeDCVTmpEntity> findByFechaReg(Date fechaReg);
}
