package com.bdb.opaloshare.persistence.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OplParEndpointRepositoryCustomImpl implements OplParEndpointRepositoryCustom{
	
	@PersistenceContext
    EntityManager entityManager;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String getParametro() {
		
		
		Query query = entityManager.createNativeQuery("select DESC_RUTA from OPL_PAR_ENDPOINT_DOWN_TBL WHERE COD_RUTA = 1");
		
		 try {
			 List<String> value = query.getResultList();

	        	if(!value.isEmpty()) {
	        		return value.get(0).toString();
	        		
	        	}
	        	entityManager.flush();
	        	entityManager.close();
	        	
	        }
	        catch (Exception e) {
	        	logger.error("ERROR IN OplParEndpointRepositoryCustomImpl: "+e.getMessage());
	        }finally {
	        	entityManager.close(); 
	        }
		return null;
	}

}
