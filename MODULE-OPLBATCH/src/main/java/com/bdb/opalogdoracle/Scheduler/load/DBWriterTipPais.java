package com.bdb.opalogdoracle.Scheduler.load;

import com.bdb.opalogdoracle.controller.service.interfaces.TipPaisWService;
import com.bdb.opaloshare.persistence.entity.TipPaisEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DBWriterTipPais implements ItemWriter<TipPaisEntity> {
	
	/*
	 * REALIZA EL LLAMADO AL SERVICE PARA PODER IMPLEMENTAR EL ALMACENAMIENTO DE LOS DATOS OBTENIDOS A LA BASE DE DATOS.
	 */

	@Autowired
	TipPaisWService servicePais;

	@Override
	public void write(List<? extends TipPaisEntity> items) throws Exception {
		System.out.println("CANTIDAD DE ITEMS: " + items.size());
		servicePais.guardarPais(items);
	}
}
