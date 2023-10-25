package com.bdb.opalogdoracle.controller.service.implement;

import com.bdb.opalogdoracle.controller.service.interfaces.TipDepWService;
import com.bdb.opaloshare.persistence.entity.TipDeparEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryTipDepar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service("serviceDepartamento")
public class TipDepWServiceImpl implements TipDepWService {
	
	/*
	 * ESTA CLASE ES LA ENCARGADA DE ALMACENAR LOS DIFERENTES ALGORITMOS PARA DAR VIDA O FUNCIONAMIENTO A LOS DISTINTOS METODOS 
	 * DECLARADOS EN EL SERVICIO , LA CLASE SERVICEIMPLEMENTS SERA LA UNICA DONDE SE DIGITE CODIGO , ESTO CON EL FIN DE RESPETAR
	 * LAS CAPAS DE LA APLICACIÓN.
	 * CUANDO SE CREE UN SERVICEIMPLEMENTS SE DEBERA IMPLEMENTAR LA INTERFACE SERVICE CREADA , ADEMAS AÑADIR EL REPOSITORY PROPIO
	 * DE LA ENTIDAD.
	 * 
	 * ESTA CLASE CONTIENE LOS SIGUIENTES METODOS:
	 * 
	 * ***************************************************************************************************************************
	 * guardarDepartamento()
	 * ESTE METODO PERMITE A TRAVES DE LA FUNCION .SAVEALL (FUNCION DE JPA) ALMACENAR TODOS LOS REGISTROS TIPO DEPARTAMENTO QUE
	 * LLEGUEN AL METODO.
	 * ***************************************************************************************************************************
	 * 
	 * ***************************************************************************************************************************
	 * guardarDepartamento()
	 * ESTE METODO HA SIDO CREADO PARA PODER GUARDAR LA INFORMACION OBTENIDA EN EL CONTROLLER Y PODER LLEVARLA A LA CLASE 
	 * (BATCHCONFIGURE...) ALMACENADA EN EL PACKAGE PROCESOSBATCH. EN RESUMEN ESTE METODO FUNCIONA COMO UN METODO SET().
	 * ***************************************************************************************************************************
	 * 
	 * ***************************************************************************************************************************
	 * cargarDepartamento()
	 * ESTE METODO ES EL ENCARGADO DE CARGAR LA INFORMACION OBTENIDA EN EL METODO GUARDARCIIU. PRACTICAMENTE ACTUA COMO EL METODO 
	 * GET().
	 * ***************************************************************************************************************************
	 */

	@Autowired
	RepositoryTipDepar repoDepartamento;
	
	ByteArrayOutputStream archivo = new ByteArrayOutputStream();
	
	@Override
	public void guardarDepartamento(List<? extends TipDeparEntity> items) {
		// TODO Auto-generated method stub
		repoDepartamento.saveAll(items);
	}
	
	@Override
	public void guardarDepartamento(ByteArrayOutputStream archivo) {
		// TODO Auto-generated method stub
		this.archivo = archivo;
	}

	@Override
	public ByteArrayOutputStream cargarDepartamento() {
		// TODO Auto-generated method stub
		return archivo;
	}

}
