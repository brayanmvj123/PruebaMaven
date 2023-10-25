package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.TipCiudParEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface RepositoryTipCiudPar extends JpaRepository<TipCiudParEntity, Serializable>{

	/*
	 * ESTE REPOSITORIO PERTENECE A LA TABLA ACC_TIPCIUD_PAR_TBL
	 */

	boolean existsByIdAndAccTipdepTblIdAndAccTippaisTblId(Long codCiud,Long codDep,Long codPais);

}
