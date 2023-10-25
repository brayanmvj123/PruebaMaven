package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.AjusLiqDiaEntity;
import com.bdb.opaloshare.persistence.entity.AjusLiqDiaEntityPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAjusLiqDia extends JpaRepository<AjusLiqDiaEntity, AjusLiqDiaEntityPk> {
}
