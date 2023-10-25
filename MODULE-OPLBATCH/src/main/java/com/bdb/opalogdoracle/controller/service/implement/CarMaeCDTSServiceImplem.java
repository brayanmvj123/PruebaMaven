package com.bdb.opalogdoracle.controller.service.implement;

import com.bdb.opalogdoracle.controller.service.interfaces.CarMaeCDTSService;
import com.bdb.opaloshare.persistence.entity.*;
import com.bdb.opaloshare.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarMaeCDTSServiceImplem implements CarMaeCDTSService {

	@Autowired
	private RepositoryCarMaeCdts repoCarCDTS;
	
	@Autowired
	private RepositoryTipId repoTipId;
	
	@Autowired
	private RepositoryTipPlazo repoTipPlazo;
	
	@Autowired
	private RepositoryTipBase repoTipBase;
	
	@Autowired
	private RepositoryTipPeriodicidad repoTipPeriod;
	
	@Autowired
	private RepositoryTipTasa repoTipTasa;
	
	@Autowired
	private RepositoryTipPosicion repoTipPosicion;
	
	@Autowired
	private RepositoryCDTsDesmaterializado repoTmpCDT;

	@Autowired
	private RepositoryTipVarentorno repositoryTipVarentorno;
	
	ByteArrayOutputStream archivo = new ByteArrayOutputStream();
	
	@Override
	public void guardarCDT(List<? extends MaeCDTSCarEntity> items) {
		repoCarCDTS.saveAll(items);
	}

	@Override
	public void almacenarCDT(ByteArrayOutputStream archivo) {
		this.archivo = archivo;
	}

	@Override
	public ByteArrayOutputStream cargarCDT() {
		return archivo;
	}

	@Override
	public void homologarCodId() {
		List<TipidParDownEntity> codigos = repoTipId.findByHomoDcvbtaNot("0");
		codigos.forEach(codigo -> repoCarCDTS.homologarCodId(codigo.getHomoDcvbta()));
		//repoCarCDTS.homologarCodId(tipoDocumento);
	}

	@Override
	public void homologarTipPlazo() {
		List<TipplazoParDownEntity> codigos = repoTipPlazo.findAll();
		codigos.forEach(codigo -> repoCarCDTS.homologarTipPlazo(codigo.getHomoDcvbta()));
	}

	@Override
	public void homologarTipBase() {
		List<TipbaseParDownEntity> codigos = repoTipBase.findByHomoDcvbtaNot("3");
		codigos.forEach(codigo -> repoCarCDTS.homologarTipBase(codigo.getHomoDcvbta()));
	}

	@Override
	public void homologarTipPeriodicidad() {
		List<TipPeriodParDownEntity> codigos = repoTipPeriod.findAll();
		codigos.forEach(codigo -> repoCarCDTS.homologarTipPeriodicidad(codigo.getHomoDcvbta()));
	}

	@Override
	public void homologarTipTasa() {
		List<TiptasaParDownEntity> codigos = repoTipTasa.findAll();
		codigos.forEach(codigo -> repoCarCDTS.homologarTipTasa(codigo.getHomoDcvbta()));
	}

	@Override
	public void homologarTipPosicion() {
		List<TipposicionParDownEntity> codigos = repoTipPosicion.findAll();
		codigos.forEach(codigo -> repoCarCDTS.homologarTipPosicion(codigo.getHomoDcvbta()));
	}

	@Override
	public void actualizarTmp() {
		List<MaeDCVTempDownEntity> objeto = new ArrayList<>();
		List<MaeCDTSCarEntity> lista = repoCarCDTS.findAll();
		for(MaeCDTSCarEntity registro : lista) {
			MaeDCVTempDownEntity maeTemCDT = new MaeDCVTempDownEntity();
			maeTemCDT.setFechaReg(registro.getFechaReg());
			maeTemCDT.setCodIsin(registro.getCodIsin());
			maeTemCDT.setNumCDT(registro.getNumCDT());
			maeTemCDT.setNumFol(registro.getPlazo());
			maeTemCDT.setCtaInv(registro.getCtaInv());
			maeTemCDT.setIdTit(registro.getIdTit());
			maeTemCDT.setNomTit(registro.getNomTit());
			maeTemCDT.setFechaEmi(registro.getFechaEmi());
			maeTemCDT.setFechaVen(registro.getFechaVen());
			maeTemCDT.setFechaProxPg(registro.getFechaProPago());
			maeTemCDT.setSpread(registro.getSpread());
			maeTemCDT.setTasEfe(registro.getTasEfe());
			maeTemCDT.setTasNom(registro.getTasNom());
			maeTemCDT.setOficina(registro.getOficina());
			maeTemCDT.setVlrCDT(registro.getVlrCDT());
			maeTemCDT.setPlazo(registro.getPlazo());
			maeTemCDT.setOplTipidTblCodId(registro.getTipId());
			maeTemCDT.setOplTipbaseTblTipBase(registro.getTipBase());
			maeTemCDT.setOplTipplazoTblTipPlazo(registro.getTipPlazo());
			maeTemCDT.setOplTipperiodTblTipPeriodicidad(registro.getTipPeriod());
			maeTemCDT.setOplTipposicionTblTipPosicion(registro.getPosicion());
			maeTemCDT.setOplTiptasaTblTipTasa(registro.getTipTasa());
//			maeTemCDT.setCodProd(registro.getCodProd());
			objeto.add(maeTemCDT);
		}
		repoTmpCDT.saveAll(objeto);
	}

	@Override
	public String conocerNombreArchivoCarga() {
		return repositoryTipVarentorno.findByDescVariable("FILE_INPUT_MAECDTDCV").getValVariable();
	}

	@Override
	public void eliminarTmpMaeDcv() {
		repoTmpCDT.deleteAll();
	}

	@Override
	public void verificacionNumCDTDigital() {
		repoCarCDTS.actualizarNumCdtconRelleno();
		repoCarCDTS.actualizarNumCdtsinRelleno();
	}

	@Override
	public void eliminarInformacion() {
		repoCarCDTS.deleteAll();
	}

	@Override
	public void homologarCodIdNIT() {
		repoCarCDTS.homologarCodIdNit();
	}

}
