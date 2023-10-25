package com.bdb.opalogdoracle.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.TipDeparEntity;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface TipDepWService {
	
	/*
	 * ESTA INTERFACE PERMITE CONSTRUIR LOS METODOS QUE SERAN PROPIOS DEL SERVICEIMPLEMENTS (DEPARTAMENTOSERVICEIMPL) , ESTA CLASE 
	 * SIEMPRE DEBERA SER LLAMADA PARA TRABAJAR CON LOS SERVICESIMPLEMENTS , ESTO SE REALIZA PARA MANTENER UNA ORGANIZACION , 
	 * PODER REUTILIZAR CODIGO Y MANTENER INTEGRIDAD ENTRE LAS DIFERENTES CAPAS. 
	 */

	public void guardarDepartamento(List<? extends TipDeparEntity> items);
	
	public void guardarDepartamento(ByteArrayOutputStream archivo);
	
	public ByteArrayOutputStream cargarDepartamento();
}
