package com.bdb.opaloshare.persistence.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="OPL_CAR_DERPATRIDEP_DOWN_TBL")
public class CarDerpatridepDownEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ITEM")
	private Long item;
	
	@Column(name="COD_DEPOSITANTE")
	private String codDepositante;
	
	@Column(name="COD_ADMIN")
	private String codAdmin;
	
	@Column(name="COD_EMISOR")
	private String codEmisor;
	
	@Column(name="ISIN")
	private String isin;
	
	@Column(name="COD_DEP")
	private String codDep;
	
	@Column(name="TIP_DER")
	private String tipDer;
	
	@Column(name="FECHA_VENC")
	private String fechaVenc;
	
	@Column(name="CTA_INV")
	private String ctaInv;
	
	@Column(name="TIP_ID")
	private String tipId;
	
	@Column(name="ID_TIT")
	private String idTit;
	
	@Column(name="NOM_TIT")
	private String nomTit;
	
	@Column(name="SAL_CONT")
	private String salCont;
	
	@Column(name="COB_DIV_EFE")
	private String cobDivEfe;
	
	@Column(name="COB_DIV_ACC")
	private String cobDivAcc;
	
	@Column(name="COB_CAP")
	private String cobCap;
		
	@Column(name="COB_REND")
	private String cobRend;
		
	@Column(name="REINV")
	private String reinv;
	
	@Column(name="REC_CAP")
	private String recCap;
	
	@Column(name="REC_DIV_ACC")
	private String recDivAcc;
	
	@Column(name="REC_REND")
	private String recRend;
	
	@Column(name="RTE_FTE")
	private String rteFte;
	
	@Column(name="ENAJENACION")
	private String enajenacion;
	
	@Column(name="ADMIN_DCVL")
	private String adminDcvl;
	
	@Column(name="PREIMPRESO")
	private String preimpreso;
	
	@Column(name="FECHA_INI")
	private String fechaIni;
	
	@Column(name="FECHA_FIN")
	private String fechaFin;
	
	@Column(name="TAS_EFE")
	private String tasEfe;
	
	@Column(name="FACTOR")
	private String factor;
	
	@Column(name="PG_CUD")
	private String pgCud;
	
	@Column(name="PG_PDI")
	private String pgPdi;
	
	@Column(name="COB_CHEQUE")
	private String cobCheque;
	
	@Column(name="COB_CONS")
	private String cobCons;
	
	@Column(name="GRAV")
	private String grav;
	
	@Column(name="BANCO")
	private String banco;
	
	@Column(name="NRO_CTA")
	private String nroCta;
	
	@Column(name="EST_PG_PDI")
	private String estPgPdi;
	
	@Column(name="IMP_ICA")
	private String impIca;
	
	@Column(name="IMP_CRE")
	private String impCre;
	
	@Column(name="IMP_ADI")
	private String impAdi;
	
	@Column(name="FECHA_CONS")
	private String fechaCons;
	
	@Column(name="EST_PRO_REIN")
	private String estProRein;
	
	@Column(name="MON_PRO_AUT")
	private String monProCAut;
	
	@Column(name="MON_PRO_CON")
	private String monProCon;
	
	@Column(name="REND_ADI")
	private String rendAdi;
	
	@Column(name="COMP_REIN")
	private String compRein;
	
	@Column(name="TOT_COB_COP")
	private String totCobCop;
	
	@Column(name="TOT_PG_COP")
	private String totPgCop;
	
}
