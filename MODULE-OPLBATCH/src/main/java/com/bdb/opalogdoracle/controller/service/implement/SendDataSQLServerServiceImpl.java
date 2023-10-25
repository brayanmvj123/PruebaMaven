package com.bdb.opalogdoracle.controller.service.implement;

import java.util.List;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdb.opalogdoracle.controller.service.interfaces.SendDataSQLServeService;
import com.bdb.opaloshare.persistence.entity.MaeDCVTempDownEntity;
import com.bdb.opaloshare.persistence.entity.SalPdcvlEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryCDTsDesmaterializado;
import com.bdb.opaloshare.persistence.repository.RepositoryCondicionesCDT;
import com.bdb.opaloshare.persistence.repository.RepositorySalPdcv;

@Service
@CommonsLog
public class SendDataSQLServerServiceImpl implements SendDataSQLServeService {

	@Autowired
	private RepositoryCDTsDesmaterializado repoOracle;
	
	@Autowired
	private RepositorySalPdcv repoSalPdcv;
	
	@Autowired
	private RepositoryCondicionesCDT repoCDT;
	
	@Override
	public List<MaeDCVTempDownEntity> pruebaSql() {
		return repoOracle.pruebaSQL();
	}

	@Override
	public List<SalPdcvlEntity> listaArchivoP() {
		log.info("EMPEZO");
		return repoSalPdcv.findAll();
	}

	@Override
	public String verificarProceso(String resultado) {
		String mensaje;
		if(resultado.equals("COMPLETED") || resultado.equals("NO_DATA")) {
			repoCDT.actualizarEstadoCDTS();
			mensaje = "{\"status\":\"200-OK\"}";
		}else {
			repoSalPdcv.eliminacionContigente();
			mensaje = "{\"status\":\"500-FAIL\"}";
		}
		return mensaje;
	}

	@Override
	public void eliminarTabla() {
		repoSalPdcv.deleteAll();
	}

	@Override
	public void guardarData(List<? extends SalPdcvlEntity> items) {
		repoSalPdcv.saveAll(items);
	}

	@Override
	public void actualizarData(List<? extends SalPdcvlEntity> items) {
		items.forEach(item ->
			repoSalPdcv.actualizarClientes(item.getNmbtit2() , item.getNmbtit3(), item.getNmbtit4(), 
					item.getTipdoc2(), item.getTipdoc3(), item.getTipdoc4(), 
					item.getNrodoc2(), item.getNrodoc3(), item.getNrodoc4(),
					item.getCtacdtbb())
		);
	}

	@Override
	public void actulizarEstadoaFinalizado() {
		repoCDT.actualizarEstadoFinalizado();
	}

	@Override
	public Boolean verificarTablaArchivoP(Long resultadoRegistros) {
		log.info("ENTRO A verificarTablaArchivoP");
		return resultadoRegistros == 0;
	}

}
