package com.bdb.opaloshare.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bdb.opaloshare.persistence.entity.TipidParDownEntity;

@Repository
public interface OplTipidParDownTblRepository extends JpaRepository<TipidParDownEntity, Integer> {

}
