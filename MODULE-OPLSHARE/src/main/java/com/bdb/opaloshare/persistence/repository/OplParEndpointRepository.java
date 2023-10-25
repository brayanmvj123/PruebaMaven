package com.bdb.opaloshare.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bdb.opaloshare.persistence.entity.OplParEndpoint;

public interface OplParEndpointRepository extends JpaRepository<OplParEndpoint, Integer>, OplParEndpointRepositoryCustom {
	
	@Query("select f from OplParEndpoint f WHERE f.codRuta = :codRuta") 
	OplParEndpoint getParametro(Long codRuta);

}
