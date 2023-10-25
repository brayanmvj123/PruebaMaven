package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.TipPaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface RepositoryTipPaisPar extends JpaRepository<TipPaisEntity, Serializable> {
	
	/*
	 * ESTE REPOSITORIO PERTENECE A LA TABLA ACC_TIPPAIS_PAR_TBL
	 */
	
	TipPaisEntity findByHomoCrm(String codPais);

	TipPaisEntity findByCodPais(Integer codPais);

}
