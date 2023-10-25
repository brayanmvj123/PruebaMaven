package com.bdb.opalogdoracle.controller.service.interfaces;

import java.util.List;

import com.bdb.opaloshare.persistence.entity.MaeDCVTempDownEntity;
import com.bdb.opaloshare.persistence.entity.SalPdcvlEntity;

public interface SendDataSQLServeService {

	List<MaeDCVTempDownEntity> pruebaSql();
	
	List<SalPdcvlEntity> listaArchivoP();
	
	String verificarProceso(String resultado);
	
	void eliminarTabla();
	
	void guardarData(List<? extends SalPdcvlEntity> items);
	
	void actualizarData(List<? extends SalPdcvlEntity> items);

	void actulizarEstadoaFinalizado();

	Boolean verificarTablaArchivoP(Long resultadoRegistros);

}
