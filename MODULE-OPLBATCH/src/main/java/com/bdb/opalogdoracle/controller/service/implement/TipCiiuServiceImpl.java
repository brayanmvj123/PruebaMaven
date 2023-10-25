package com.bdb.opalogdoracle.controller.service.implement;

import com.bdb.opalogdoracle.controller.service.interfaces.TipCiiuService;
import com.bdb.opaloshare.persistence.entity.TipCIIUEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryTipCIUU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service("serviceCIIU")
public class TipCiiuServiceImpl implements TipCiiuService {
	
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
	 * agregarCIIU()
	 * ESTE METODO PERMITE A TRAVES DE LA FUNCION .SAVEALL (FUNCION DE JPA) ALMACENAR TODOS LOS REGISTROS TIPO CIIU QUE LLEGUEN AL
	 * METODO.
	 * ***************************************************************************************************************************
	 * 
	 * ***************************************************************************************************************************
	 * guardarCIIU()
	 * ESTE METODO HA SIDO CREADO PARA PODER GUARDAR LA INFORMACION OBTENIDA EN EL CONTROLLER Y PODER LLEVARLA A LA CLASE 
	 * (BATCHCONFIGURE...) ALMACENADA EN EL PACKAGE PROCESOSBATCH. EN RESUMEN ESTE METODO FUNCIONA COMO UN METODO SET().
	 * ***************************************************************************************************************************
	 * 
	 * ***************************************************************************************************************************
	 * cargarCIIU()
	 * ESTE METODO ES EL ENCARGADO DE CARGAR LA INFORMACION OBTENIDA EN EL METODO GUARDARCIIU. PRACTICAMENTE ACTUA COMO EL METODO
	 * GET(). 
	 * ***************************************************************************************************************************
	 */

	@Autowired
	RepositoryTipCIUU repoCIIU;
	
	ByteArrayOutputStream archivo = new ByteArrayOutputStream();
	
	@Override
	public void agregarCIIU(List<? extends TipCIIUEntity> items) {
		// TODO Auto-generated method stub
		repoCIIU.saveAll(items);
	}
	
	@Override
	public void guardarCIIU(ByteArrayOutputStream archivo) {
		// TODO Auto-generated method stub
		this.archivo = archivo;
	}

	@Override
	public ByteArrayOutputStream cargarCIIU() {
		// TODO Auto-generated method stub
		return archivo;
	}

}
