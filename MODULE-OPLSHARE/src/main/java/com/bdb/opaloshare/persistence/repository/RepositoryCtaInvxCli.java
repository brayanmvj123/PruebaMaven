package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.HisCtaInvxCliEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface RepositoryCtaInvxCli extends JpaRepository<HisCtaInvxCliEntity, Serializable> {

    HisCtaInvxCliEntity findByOplCtainvTblNumCtaAndOplInfoclienteTblNumTit(String ctaInv, String numTit);


    List<HisCtaInvxCliEntity> findByOplCtainvTblNumCta(String ctaInv);

    HisCtaInvxCliEntity findByTitularidadAndOplCtainvTblNumCta(Long titularidad, String ctaInv);

//    List<HisCtaInvxCliEntity> findAllByAccAccionistasTblIdAccAndTitularidad(String id, Integer ownership);
//
//    HisCtaInvxCliEntity findByAccAccionistasTblIdAcc(String id);

//    boolean existsByAccCtainvTblNumCtaAndAccAccionistasTblIdAcc(Long numCta, String idAcc);

    boolean existsByOplCtainvTblNumCtaAndOplInfoclienteTblNumTit(String numCta, String idAcc);

//    List<HisCtaInvxCliEntity> findAllByAccCtainvTblNumCtaAndTitularidad(Long num, Integer tit);
//
//    List<HisCtaInvxCliEntity> deleteByAccCtainvTblNumCtaAndTitularidad(Long numCta, Integer titu);

}
