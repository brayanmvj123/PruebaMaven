package com.bdb.opalocdeceval.controller.service.interfaces;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.bdb.opaloshare.persistence.entity.MaeCDTSCarEntity;

public interface CarMaeCDTSService {

	public void guardarCDT(List<? extends MaeCDTSCarEntity> items);
	
	public void eliminarInformacion();
	
	public void almacenarCDT(ByteArrayOutputStream archivo);
	
	public ByteArrayOutputStream cargarCDT();
	
	public void homologarCodId();
	
	public void homologarCodIdNIT();
	
	public void homologarTipPlazo();
	
	public void homologarTipBase();
	
	public void homologarTipPeriodicidad();
	
	public void homologarTipTasa();
	
	public void homologarTipPosicion();
	
	public void actualizarTmp();
}
