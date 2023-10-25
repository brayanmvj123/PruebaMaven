package com.bdb.opalogdoracle.Scheduler.load;

import com.bdb.opalogdoracle.controller.service.interfaces.TipCiudWService;
import com.bdb.opaloshare.persistence.entity.TipCiudEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DBWriterTipCiud implements ItemWriter<TipCiudEntity> {
	
	/*
	 * REALIZA EL LLAMADO AL SERVICE PARA PODER IMPLEMENTAR EL ALMACENAMIENTO DE LOS DATOS OBTENIDOS A LA BASE DE DATOS.
	 */

	@Autowired
	TipCiudWService serviceCiudad;
	
	@Override
	public void write(List<? extends TipCiudEntity> items) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(items.size());
		serviceCiudad.guardarCiudad(items);
	}

}
