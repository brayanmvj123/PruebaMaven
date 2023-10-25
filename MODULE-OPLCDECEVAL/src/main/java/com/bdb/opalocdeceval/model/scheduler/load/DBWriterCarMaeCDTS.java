package com.bdb.opalocdeceval.model.scheduler.load;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdb.opalocdeceval.controller.service.interfaces.CarMaeCDTSService;
import com.bdb.opaloshare.persistence.entity.MaeCDTSCarEntity;

public class DBWriterCarMaeCDTS implements ItemWriter<MaeCDTSCarEntity> {

	@Autowired
	private CarMaeCDTSService serviceCarMae;
	
	@Override
	public void write(List<? extends MaeCDTSCarEntity> items) throws Exception {
		// TODO Auto-generated method stub
		serviceCarMae.guardarCDT(items);
	}

}
