package com.bdb.opalossqls.controller.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bdb.opaloshare.persistence.entity.SalPdcvlEntity;

public interface SendSQLServerService {

	ResponseEntity<String> almacenarCDTDecevalBta(ResponseEntity<List<SalPdcvlEntity>> response);
	
	String getEstado();
	
	String actualizarClientesCDT(ResponseEntity<List<SalPdcvlEntity>> response);

	Long verificarTablaArchivoP();

	void eliminarDataTablaArchivoP();


		
}
