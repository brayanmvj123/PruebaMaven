package com.bdb.opaloshare.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SalPdcvlModel {

	private String cod_trn;
	private String num_Cdt;
	private String OPL_OFICINA_TBL_NRO_OFICINA;
	private String nom_tit;
	private String OPL_TIPID_TBL_COD_ID;
	private String NUM_TIT;
	private Integer plazo;
	private BigDecimal valor;
	private String FECHA_VEN;
	private String OPL_DEPOSITANTE_TBL_TIP_DEPOSITANTE;
	private String fecha;
	private String COD_PROD;
	private String CLASE_PER;
	private String OPL_TIPCIIU_TBL_COD_CIIU;
	private String TIP_TITULARIDAD;
	private String DIR_TIT;
	private String OPL_PAR_TIPDANE_TBL_COD_DANE;
	private String TEL_TIT;
	private String EXTENSION;
	private String RETENCION;
	private BigDecimal TASA_NOM;
	private BigDecimal TASA_EFE;
	private String OPL_TIPTASA_TBL_TIP_TASA;
	private BigDecimal SPREAD;
	private String OPL_TIPBASE_TBL_TIP_BASE;
	private Integer OPL_TIPPERIOD_TBL_TIP_PERIODICIDAD;
	private String OPL_TIPPLAZO_TBL_TIP_PLAZO;
	private String ciu_Dane;
	private String ciudad;
	
}
