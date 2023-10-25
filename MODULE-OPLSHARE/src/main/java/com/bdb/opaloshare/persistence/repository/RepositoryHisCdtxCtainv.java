package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.HisCdtxCtainvEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface RepositoryHisCdtxCtainv extends JpaRepository<HisCdtxCtainvEntity, Serializable> {


    HisCdtxCtainvEntity findByNumCdtAndOplCtainvTblNumCta(Long numCdt, String oplCtainvTblNumCta);

}
