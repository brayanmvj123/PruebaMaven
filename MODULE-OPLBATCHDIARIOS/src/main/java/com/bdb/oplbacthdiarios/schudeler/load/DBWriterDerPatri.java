package com.bdb.oplbacthdiarios.schudeler.load;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bdb.opaloshare.persistence.entity.CarDerpatridepDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryCarDerpatridepDownEntity;
import com.bdb.oplbacthdiarios.controller.service.interfaces.DerechosPatrimonialesService;

import java.util.List;
public class DBWriterDerPatri implements ItemWriter<CarDerpatridepDownEntity> {
	
	/*
	 * REALIZA EL LLAMADO AL SERVICE PARA PODER IMPLEMENTAR EL ALMACENAMIENTO DE LOS DATOS OBTENIDOS A LA BASE DE DATOS.
	 */

	@Autowired
    private RepositoryCarDerpatridepDownEntity repoPatrimonialesCarga;
	
	@Override
	public void write(List<? extends CarDerpatridepDownEntity> items) throws Exception {
		
		try {
			repoPatrimonialesCarga.saveAll(items);
		} catch (Exception e) {
			throw new Exception("OCURRIO UN ERROR AL GUARDAR ... ",e);
		}
		
			
	}

}
