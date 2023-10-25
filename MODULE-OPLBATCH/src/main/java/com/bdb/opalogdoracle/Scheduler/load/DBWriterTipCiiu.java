package com.bdb.opalogdoracle.Scheduler.load;

import com.bdb.opalogdoracle.controller.service.interfaces.TipCiiuService;
import com.bdb.opaloshare.persistence.entity.TipCIIUEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DBWriterTipCiiu implements ItemWriter<TipCIIUEntity> {
	
	/*
	 * REALIZA EL LLAMADO AL SERVICE PARA PODER IMPLEMENTAR EL ALMACENAMIENTO DE LOS DATOS OBTENIDOS A LA BASE DE DATOS.
	 */
	
	@Autowired
	private TipCiiuService serviceCIIU;

	@Override
	public void write(List<? extends TipCIIUEntity> items) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CANTIDAD DE CIIU: " + items.size());
		serviceCIIU.agregarCIIU(items);
	}

}
