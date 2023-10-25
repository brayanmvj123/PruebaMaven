package com.bdb.opalogdoracle.Scheduler.load;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdb.opalogdoracle.controller.service.interfaces.SendDataSQLServeService;
import com.bdb.opaloshare.persistence.entity.SalPdcvlEntity;

public class DBWriterUpdateCliente implements ItemWriter<SalPdcvlEntity> {

	@Autowired
	private SendDataSQLServeService serviceSQLServer;
	
	@Override
	public void write(List<? extends SalPdcvlEntity> items) throws Exception {
		// TODO Auto-generated method stub
		serviceSQLServer.actualizarData(items);
	}

}
