package com.bdb.opalogdoracle.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.TipCIIUEntity;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface TipCiiuService {
	
	/*
	 * ESTA INTERFACE PERMITE CONSTRUIR LOS METODOS QUE SERAN PROPIOS DEL SERVICEIMPLEMENTS (CIIUSERVICEIMPL) , ESTA CLASE 
	 * SIEMPRE DEBERA SER LLAMADA PARA TRABAJAR CON LOS SERVICESIMPLEMENTS , ESTO SE REALIZA PARA MANTENER UNA ORGANIZACION , 
	 * PODER REUTILIZAR CODIGO Y MANTENER INTEGRIDAD ENTRE LAS DIFERENTES CAPAS. 
	 */

	void agregarCIIU(List<? extends TipCIIUEntity> items);
	
	void guardarCIIU(ByteArrayOutputStream archivo);
	
	ByteArrayOutputStream cargarCIIU();
	
}
