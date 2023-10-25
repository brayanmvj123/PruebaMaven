package com.bdb.opaloshare.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bdb.opaloshare.persistence.entity.SalNovCrmCliEntity;

@Repository
public interface OplNovcrmcliSalDownTblRepository extends JpaRepository<SalNovCrmCliEntity, Long>{

}
