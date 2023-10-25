package com.bdb.opaloapcdt.controller.service.interfaces;

import java.util.List;

import com.bdb.opaloapcdt.persistence.Model.InformacionTranpg;
import com.bdb.opaloapcdt.persistence.JSONSchema.JSONPagCDTDes;
import com.bdb.opaloshare.persistence.entity.HisTranpgEntity;

public interface TransaccionesPagosCDTService {

	InformacionTranpg almacenarTransaccionesPagos(List<JSONPagCDTDes> request, boolean condicionesCDT, String numeroCDT) throws IllegalArgumentException;
	
	void guardarEntity(List<HisTranpgEntity> entity);
	
}
