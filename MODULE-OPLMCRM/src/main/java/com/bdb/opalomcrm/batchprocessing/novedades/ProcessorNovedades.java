package com.bdb.opalomcrm.batchprocessing.novedades;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.bdb.opalomcrm.common.Constants;
import com.bdb.opaloshare.persistence.entity.SalNovCrmCliEntity;

@Component
public class ProcessorNovedades implements ItemProcessor<SalNovCrmCliEntity, SalNovCrmCliEntity>{

	@Override
	public SalNovCrmCliEntity process(SalNovCrmCliEntity item) throws Exception {
		
		if(item.getStatus().equals(Constants.NOV_MARCA_BUS_SOAP_N)) {
			return item;
		}
		return null;
			
	}

}
