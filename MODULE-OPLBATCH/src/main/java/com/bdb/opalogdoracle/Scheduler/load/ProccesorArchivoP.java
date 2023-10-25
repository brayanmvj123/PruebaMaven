package com.bdb.opalogdoracle.Scheduler.load;

import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.SalPdcvlEntity;
import com.bdb.opaloshare.persistence.entity.SalPdcvlModel;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class ProccesorArchivoP implements ItemProcessor<SalPdcvlModel, SalPdcvlEntity>{

	@Autowired
	private SharedService sharedService;

	@Override
	public SalPdcvlEntity process(SalPdcvlModel item) throws Exception {
		SalPdcvlEntity sal = new SalPdcvlEntity();
		sal.setOfioritx(item.getOPL_OFICINA_TBL_NRO_OFICINA());
		sal.setCodinttx("001");
		sal.setCtacdtbb(item.getNum_Cdt());
		sal.setTipocdt("2");
		sal.setOfduena(item.getOPL_OFICINA_TBL_NRO_OFICINA());
		sal.setNmbtit1(sharedService.truncarCampo(item.getNom_tit(),0 ,40 , 40));
		sal.setTipdoc1(item.getOPL_TIPID_TBL_COD_ID());
		sal.setNrodoc1(item.getNUM_TIT());
		sal.setPlazoti(item.getPlazo());
		sal.setKapital(item.getValor());
		sal.setFchvenc(item.getFECHA_VEN());
		sal.setCtaaabo("00000000000000");
		sal.setDeposit(item.getOPL_DEPOSITANTE_TBL_TIP_DEPOSITANTE());
		sal.setRespcdts("000");
		sal.setFechape(item.getFecha());
		sal.setTipprod(item.getCOD_PROD());
		sal.setClasper(item.getCLASE_PER());
		sal.setActeco(item.getOPL_TIPCIIU_TBL_COD_CIIU());
		sal.setTiprela(item.getTIP_TITULARIDAD());
		sal.setDirresi(sharedService.truncarCampo(item.getDIR_TIT(),0,40,40));
		sal.setTeleres(sharedService.truncarCampo(item.getTEL_TIT(),0,10,10));
		//sal.setExtOfi(item.getEXTENSION());
		sal.setTiprete(item.getRETENCION());
		sal.setTasnomi(item.getTASA_NOM());
		sal.setTasefec(item.getTASA_EFE());
		sal.setTiptasa(item.getOPL_TIPTASA_TBL_TIP_TASA());
		sal.setSpread(item.getSPREAD());
		sal.setFchaper(item.getFecha());
		sal.setBaseliq(item.getOPL_TIPBASE_TBL_TIP_BASE());
		sal.setPeriodi(item.getOPL_TIPPERIOD_TBL_TIP_PERIODICIDAD());
		sal.setTipplaz(item.getOPL_TIPPLAZO_TBL_TIP_PLAZO());
		sal.setCiudane(item.getCiu_Dane());
		sal.setCiudad(item.getCiudad());
		return sal;
	}
}
