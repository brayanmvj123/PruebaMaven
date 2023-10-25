package com.bdb.opalogdoracle.Scheduler.load;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdb.opalogdoracle.persistence.SalPdcvlSecundarioModel;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.SalPdcvlEntity;
import com.bdb.opaloshare.persistence.entity.SalPdcvlModel;

public class ProcessorSQL implements ItemProcessor<SalPdcvlSecundarioModel, SalPdcvlEntity> {

	@Autowired
	private SharedService serviceShared;
	
	@Override
	public SalPdcvlEntity process(SalPdcvlSecundarioModel item) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("entra");
		SalPdcvlEntity saldoSecundario = new SalPdcvlEntity();
		String[] parametros = item.getListaClientesSecundario().split("!");
		System.out.println("sigue");
		
		for(int i = 0 ; i < parametros.length ; i++) {
			if(i == 0) {
				saldoSecundario.setNmbtit2(serviceShared.isNullorData(parametros[0]));
				saldoSecundario.setNrodoc2(serviceShared.isNullorData(parametros[1]));
				saldoSecundario.setTipdoc2(serviceShared.isNullorData(parametros[2]));
			}
			if(i == 3) {
				saldoSecundario.setNmbtit3(serviceShared.isNullorData(parametros[3]));
				saldoSecundario.setNrodoc3(serviceShared.isNullorData(parametros[4]));
				saldoSecundario.setTipdoc3(serviceShared.isNullorData(parametros[5]));
			}
			if(i == 6) {
				saldoSecundario.setNmbtit4(serviceShared.isNullorData(parametros[6]));
				saldoSecundario.setNrodoc4(serviceShared.isNullorData(parametros[7]));
				saldoSecundario.setTipdoc4(serviceShared.isNullorData(parametros[8]));
			}
		}
		
		saldoSecundario.setCtacdtbb(item.getNumCdt());
		return saldoSecundario;
	}

	

}
