package com.bdb.opalogdoracle.Scheduler.load;

import com.bdb.opalogdoracle.controller.service.interfaces.CarMaeCDTSService;
import com.bdb.opaloshare.persistence.entity.MaeCDTSCarEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DBWriterCarMaeCDTS implements ItemWriter<MaeCDTSCarEntity> {

	@Autowired
	private CarMaeCDTSService serviceCarMae;
	
	@Override
	public void write(List<? extends MaeCDTSCarEntity> items) throws Exception {
		// TODO Auto-generated method stub
		serviceCarMae.guardarCDT(items);
	}

}
