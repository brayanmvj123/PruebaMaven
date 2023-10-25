package com.bdb.opalogdoracle.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.MaeCDTSCarEntity;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface CarMaeCDTSService {

	void guardarCDT(List<? extends MaeCDTSCarEntity> items);
	
	void eliminarInformacion();
	
	void almacenarCDT(ByteArrayOutputStream archivo);
	
	ByteArrayOutputStream cargarCDT();
	
	void homologarCodId();
	
	void homologarCodIdNIT();
	
	void homologarTipPlazo();
	
	void homologarTipBase();
	
	void homologarTipPeriodicidad();
	
	void homologarTipTasa();
	
	void homologarTipPosicion();
	
	void actualizarTmp();

	String conocerNombreArchivoCarga();

	void eliminarTmpMaeDcv();

	/* EL SIGUEINTE METODO SE CREA PARA AJUSTAR TEMPORALMENTE EL PROCESO DE HOMOLOGACION ENTRE LAS TABLAS DE CAR Y TMP
	DEL ARCHIVO MAESTRO ENVIADO POR DECEVAL BTA.
	 */
	void verificacionNumCDTDigital();

}
