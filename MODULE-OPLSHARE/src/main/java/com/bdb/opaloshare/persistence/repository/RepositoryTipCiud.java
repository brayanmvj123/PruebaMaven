package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.TipCiudEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

public interface RepositoryTipCiud extends JpaRepository<TipCiudEntity, Serializable> {

    @Query("select ciud.codCiud " +
            "from TipCiudEntity ciud " +
            "inner join TipDeparEntity depar on depar.codDep = ciud.oplTipdeparTblCodDep " +
            "inner join TipPaisEntity pais on pais.codPais = depar.oplTippaisTblCodPais " +
            "where ciud.homoCrm = :valorCrmCiud and depar.homoCrm = :valorCrmDep and pais.homoCrm = :valorCrmPais ")
    String codigoObtenidoCiud(@Param("valorCrmCiud") String valorCrmCiud ,
                              @Param("valorCrmDep") String valorCrmDep ,
                              @Param("valorCrmPais") String valorCrmPais);

}
