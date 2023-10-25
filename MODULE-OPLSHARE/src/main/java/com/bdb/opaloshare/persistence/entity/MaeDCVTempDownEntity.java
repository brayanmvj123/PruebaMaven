package com.bdb.opaloshare.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_TMP_MAEDCV_DOWN_TBL")
public class MaeDCVTempDownEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM")
    private Long item;

    @Column(name = "FECHA_REG")
    private String fechaReg;

    @Column(name = "COD_ISIN")
    private String codIsin;

    @Column(name = "NUM_CDT")
    private String numCDT;

    @Column(name = "NUM_FOL")
    private String numFol;

    @Column(name = "CTA_INV")
    private String ctaInv;

    @Column(name = "ID_TIT")
    private String idTit;

    @Column(name = "NOM_TIT")
    private String nomTit;

    @Column(name = "FECHA_EMI")
    private String fechaEmi;

    @Column(name = "FECHA_VEN")
    private String fechaVen;

    @Column(name = "FECHA_PROX_PG")
    private String fechaProxPg;

    @Column(name = "SPREAD")
    private BigDecimal spread;

    @Column(name = "TAS_EFE")
    private BigDecimal tasEfe;
    
    @Column(name = "TAS_NOM")
    private BigDecimal tasNom;

    @Column(name = "VAL_CDT")
    private BigDecimal vlrCDT;

    @Column(name = "OFICINA")
    private String oficina;

    @Column(name="PLAZO")
    private String plazo;

    @Column(name = "OPL_TIPID_TBL_COD_ID")
    private String oplTipidTblCodId;

    @Column(name = "OPL_TIPPLAZO_TBL_TIP_PLAZO")
    private String oplTipplazoTblTipPlazo;

    @Column(name = "OPL_TIPBASE_TBL_TIP_BASE")
    private String oplTipbaseTblTipBase;

    @Column(name = "OPL_TIPPERIOD_TBL_TIP_PERIODICIDAD")
    private String oplTipperiodTblTipPeriodicidad;

    @Column(name = "OPL_TIPTASA_TBL_TIP_TASA")
    private String oplTiptasaTblTipTasa;

    @Column(name = "OPL_TIPPOSICION_TBL_TIP_POSICION")
    private String oplTipposicionTblTipPosicion;

    @Column(name = "COD_PROD")
    private Long codProd;

    //private String descTasa;
    
    //private String correo;
		
	/*
	@Column(name = "PLAZO")
	private String plazo;
	
	@Column(name = "BASE")
	private String base;
	
	@Column(name = "PERIODICIDAD")
	private String periocidad;
	
	@Column(name = "VLR_ACU")
	private BigDecimal vlrAcu;
	
	@Column(name = "VLR_CAU")
	private BigDecimal vlrCau;
	
	@Column(name = "VLR_AJU")
	private BigDecimal vlrAju;
	
	@Column(name = "DES_TASA")
	private String desTasa;
	
	@Column(name="ESTADO")
	private String estado;
	
	private String fraccionado;
	*/

	//private String tipTasa;
	//private String desTasa;
	//private String fechaProPago;
	
	public MaeDCVTempDownEntity(Long item, String numCDT, BigDecimal vlrCDT, String fechaVen, String fechaProxPg,
			String oplTiptasaParDownTblTipTasa, String descTasa ) {
		super();
		this.item = item;
		this.numCDT = numCDT;
		this.vlrCDT = vlrCDT;
		this.fechaVen = fechaVen;
		//this.fechaProPago = fechaProxPg;
		//this.tipTasa = oplTiptasaParDownTblTipTasa;
		//this.desTasa = descTasa;
	}
	
	public MaeDCVTempDownEntity(String oplTipidTblCodId,String nomTit,String idTit) {
		this.oplTipidTblCodId = oplTipidTblCodId;
		this.nomTit = nomTit;
		this.idTit = idTit;
	}

}
