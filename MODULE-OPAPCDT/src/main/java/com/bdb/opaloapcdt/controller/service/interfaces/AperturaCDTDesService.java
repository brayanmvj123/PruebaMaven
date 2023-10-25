package com.bdb.opaloapcdt.controller.service.interfaces;

import com.bdb.opaloapcdt.persistence.Model.InformacionCDT;
import com.bdb.opaloapcdt.persistence.Model.InformacionCliente;
import com.bdb.opaloapcdt.persistence.Model.InformacionClixCDT;
import com.bdb.opaloapcdt.persistence.Model.InformacionTranpg;

import java.time.LocalDateTime;

public interface AperturaCDTDesService {

	void crearCDTDes(InformacionCliente clientes , InformacionCDT cdts, InformacionClixCDT clixcdts,
					 InformacionTranpg transacPg, LocalDateTime horaInicio, LocalDateTime horaConsumo) throws Exception;

	String asignarNumCdtDigital();

	Boolean saberSiExisteNumCdt(String numCdtDigital);

}
