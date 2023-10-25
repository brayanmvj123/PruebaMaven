package com.bdb.opalocdeceval.controller.service.implement;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdb.opalocdeceval.controller.service.interfaces.CarMaeCDTSService;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.MaeCDTSCarEntity;
import com.bdb.opaloshare.persistence.entity.MaeDCVTempDownEntity;
import com.bdb.opaloshare.persistence.entity.TipPeriodParDownEntity;
import com.bdb.opaloshare.persistence.entity.TipbaseParDownEntity;
import com.bdb.opaloshare.persistence.entity.TipidParDownEntity;
import com.bdb.opaloshare.persistence.entity.TipplazoParDownEntity;
import com.bdb.opaloshare.persistence.entity.TipposicionParDownEntity;
import com.bdb.opaloshare.persistence.entity.TiptasaParDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryCDTsDesmaterializado;
import com.bdb.opaloshare.persistence.repository.RepositoryCarMaeCdts;
import com.bdb.opaloshare.persistence.repository.RepositoryTipBase;
import com.bdb.opaloshare.persistence.repository.RepositoryTipId;
import com.bdb.opaloshare.persistence.repository.RepositoryTipPeriodicidad;
import com.bdb.opaloshare.persistence.repository.RepositoryTipPlazo;
import com.bdb.opaloshare.persistence.repository.RepositoryTipPosicion;
import com.bdb.opaloshare.persistence.repository.RepositoryTipTasa;

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
	private SharedService serviceShared;
	
	ByteArrayOutputStream archivo = new ByteArrayOutputStream();
	
	@Override
	public void guardarCDT(List<? extends MaeCDTSCarEntity> items) {
		// TODO Auto-generated method stub
		repoCarCDTS.saveAll(items);
	}

	@Override
	public void almacenarCDT(ByteArrayOutputStream archivo) {
		// TODO Auto-generated method stub
		this.archivo = archivo;
	}

	@Override
	public ByteArrayOutputStream cargarCDT() {
		// TODO Auto-generated method stub
		return archivo;
	}

	@Override
	public void homologarCodId() {
		// TODO Auto-generated method stub
		List<TipidParDownEntity> codigos = repoTipId.findByHomoDcvbtaNot("0");
		codigos.stream().forEach(codigo -> repoCarCDTS.homologarCodId(codigo.getHomoDcvbta()));
		//repoCarCDTS.homologarCodId(tipoDocumento);
	}

	@Override
	public void homologarTipPlazo() {
		// TODO Auto-generated method stub
		List<TipplazoParDownEntity> codigos = repoTipPlazo.findAll();
		codigos.stream().forEach(codigo -> repoCarCDTS.homologarTipPlazo(codigo.getHomoDcvbta()));
	}

	@Override
	public void homologarTipBase() {
		// TODO Auto-generated method stub
		List<TipbaseParDownEntity> codigos = repoTipBase.findByHomoDcvbtaNot("3");
		codigos.stream().forEach(codigo -> repoCarCDTS.homologarTipBase(codigo.getHomoDcvbta()));
	}

	@Override
	public void homologarTipPeriodicidad() {
		// TODO Auto-generated method stub
		List<TipPeriodParDownEntity> codigos = repoTipPeriod.findAll();
		codigos.stream().forEach(codigo -> repoCarCDTS.homologarTipPeriodicidad(codigo.getHomoDcvbta()));
	}

	@Override
	public void homologarTipTasa() {
		// TODO Auto-generated method stub
		List<TiptasaParDownEntity> codigos = repoTipTasa.findAll();
		codigos.stream().forEach(codigo -> repoCarCDTS.homologarTipTasa(codigo.getHomoDcvbta()));
	}

	@Override
	public void homologarTipPosicion() {
		// TODO Auto-generated method stub
		List<TipposicionParDownEntity> codigos = repoTipPosicion.findAll();
		codigos.stream().forEach(codigo -> repoCarCDTS.homologarTipPosicion(codigo.getHomoDcvbta()));
	}

	@Override
	public void actualizarTmp() {
		// TODO Auto-generated method stub
		List<MaeDCVTempDownEntity> objeto = new ArrayList<MaeDCVTempDownEntity>();
		List<MaeCDTSCarEntity> lista = repoCarCDTS.findAll();
		for(MaeCDTSCarEntity registro : lista) {
			MaeDCVTempDownEntity maeTemCDT = new MaeDCVTempDownEntity();
			//System.out.println(registro.getFechaReg());
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
			objeto.add(maeTemCDT);
		}
		repoTmpCDT.saveAll(objeto);
	}

	@Override
	public void eliminarInformacion() {
		// TODO Auto-generated method stub
		repoCarCDTS.deleteAll();
	}

	@Override
	public void homologarCodIdNIT() {
		// TODO Auto-generated method stub
		repoCarCDTS.homologarCodIdNit();
	}

}
