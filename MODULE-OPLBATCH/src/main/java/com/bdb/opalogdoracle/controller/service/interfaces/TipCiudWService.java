package com.bdb.opalogdoracle.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.TipCiudEntity;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface TipCiudWService {
	
	/*
	 * ESTA INTERFACE PERMITE CONSTRUIR LOS METODOS QUE SERAN PROPIOS DEL SERVICEIMPLEMENTS (CIUDADSERVICEIMPL) , ESTA CLASE 
	 * SIEMPRE DEBERA SER LLAMADA PARA TRABAJAR CON LOS SERVICESIMPLEMENTS , ESTO SE REALIZA PARA MANTENER UNA ORGANIZACION , 
	 * PODER REUTILIZAR CODIGO Y MANTENER INTEGRIDAD ENTRE LAS DIFERENTES CAPAS. 
	 */

	public void guardarCiudad(List<? extends TipCiudEntity> items);
	
	public void guardarCiudad(ByteArrayOutputStream archivo);
	
	public ByteArrayOutputStream cargarCiudad();
	
}
