package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.AjusLiqSemEntity;
import com.bdb.opaloshare.persistence.entity.AjusLiqSemEntityPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAjusLiqSem extends JpaRepository<AjusLiqSemEntity, AjusLiqSemEntityPk> {
}
