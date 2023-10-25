package com.bdb.opaloapcdt.controller.service.interfaces;

import java.util.List;

import com.bdb.opaloapcdt.persistence.JSONSchema.JSONClientDatos;
import com.bdb.opaloapcdt.persistence.JSONSchema.JSONCondCDT;
import com.bdb.opaloapcdt.persistence.Model.InformacionClixCDT;

public interface CDTxClienteService {

	InformacionClixCDT almacenarCDTxCliente(List<JSONClientDatos> cliente , JSONCondCDT cdt , boolean condicionesCDT);

}
