package com.bdb.opaloapcdt.controller.service.interfaces;

import java.sql.SQLException;

import com.bdb.opaloapcdt.persistence.JSONSchema.JSONCondCDT;
import com.bdb.opaloapcdt.persistence.Model.InformacionCDT;

public interface CondicionesCDTService {

	InformacionCDT almacenarCondicionesCDT(JSONCondCDT request, boolean datosCliente) throws SQLException;
	
}
