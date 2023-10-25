package com.bdb.opaloapcdt.controller.service.interfaces;

import java.util.List;

import com.bdb.opaloapcdt.persistence.JSONSchema.JSONClientDatos;
import com.bdb.opaloapcdt.persistence.Model.InformacionCliente;

public interface DatosCliente {

	InformacionCliente insertarDatosCliente(List<JSONClientDatos> request);
	
}
