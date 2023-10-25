package com.bdb.opalogdoracle.Scheduler.load;

import com.bdb.opalogdoracle.controller.service.interfaces.TipDepWService;
import com.bdb.opaloshare.persistence.entity.TipDeparEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DBWriterTipDep implements ItemWriter<TipDeparEntity> {
	
	/*
	 * REALIZA EL LLAMADO AL SERVICE PARA PODER IMPLEMENTAR EL ALMACENAMIENTO DE LOS DATOS OBTENIDOS A LA BASE DE DATOS.
	 */
	
	@Autowired
	TipDepWService serviceDepartamento;
	
	@Override
	public void write(List<? extends TipDeparEntity> items) throws Exception {
		// TODO Auto-generated method stub
		serviceDepartamento.guardarDepartamento(items);
	}

}
