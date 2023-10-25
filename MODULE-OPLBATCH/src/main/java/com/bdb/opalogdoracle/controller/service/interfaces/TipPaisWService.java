package com.bdb.opalogdoracle.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.TipPaisEntity;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface TipPaisWService {
	
	/*
	 * ESTA INTERFACE PERMITE CONSTRUIR LOS METODOS QUE SERAN PROPIOS DEL SERVICEIMPLEMENTS (PAISSERVICEIMPL) , ESTA CLASE 
	 * SIEMPRE DEBERA SER LLAMADA PARA TRABAJAR CON LOS SERVICESIMPLEMENTS , ESTO SE REALIZA PARA MANTENER UNA ORGANIZACION , 
	 * PODER REUTILIZAR CODIGO Y MANTENER INTEGRIDAD ENTRE LAS DIFERENTES CAPAS. 
	 */

	public void guardarPais(List<? extends TipPaisEntity> items);
	
	public void guardarPais(ByteArrayOutputStream archivo);
	
	public ByteArrayOutputStream cargarPais();
	
}
