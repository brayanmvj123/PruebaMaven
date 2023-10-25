package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.ParEndpointDownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBF was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@Repository
public interface RepositoryParEndpointDown extends JpaRepository<ParEndpointDownEntity, Long> {
	
	@Query("select p from ParEndpointDownEntity p WHERE p.id = :codRuta") 
	ParEndpointDownEntity getParametro(Long codRuta);
	
}
