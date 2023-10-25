package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="OPL_CAR_MAECDTS_DOWN_TBL")
public class MaeCDTSCarEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ITEM")
	private Long item;
	
	@Column(name="ITEM_DCV")
	private Long itemDcv;
	
	@Column(name = "FECHA_REG")
	private String fechaReg;
	
	@Column(name = "COD_ISIN")
	private String codIsin;
		
	@Column(name="NUM_CDT")
	private String numCDT;
	
	@Column(name = "CTA_INV")
	private String ctaInv;
	
	@Column(name="TIP_ID")
	private String tipId;
	
	@Column(name="ID_TIT")
	private String idTit;
	
	@Column(name = "NOM_TIT")
	private String nomTit;
		
	@Column(name = "FECHA_EMI")
	private String fechaEmi;
	
	@Column(name="FECHA_VEN")
	private String fechaVen;
	
	@Column(name="FECHA_PROX_PG")
	private String fechaProPago;
	
	@Column(name = "TIP_PLAZO")
	private String tipPlazo;
		
	@Column(name = "TIP_BASE")
	private String tipBase;
	
	@Column(name = "TIP_PERIOD")
	private String tipPeriod;
	
	@Column(name="TIP_TASA")
	private String tipTasa;
	
	@Column(name = "SPREAD")
	private BigDecimal spread;
	
	@Column(name = "TAS_NOM")
	private BigDecimal tasNom;
	
	@Column(name = "TAS_EFE")
	private BigDecimal tasEfe;
	
	@Column(name = "PLAZO")
	private String plazo;
	
	@Column(name = "OFICINA")
	private String oficina;
	
	@Column(name="VLR_CDT")
	private BigDecimal vlrCDT;
	
	@Column(name = "POSICION")
	private String posicion;

//	@Column(name = "COD_PROD")
//	private Long codProd;

	/*
	@Column(name = "BASE")
	private String base;
	
	@Column(name = "PERIODICIDAD")
	private String periocidad;
	
	@Column(name = "PLAZO")
	private String plazo;
	
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
	
	
		
	/*public OplMaeCDTSCarEntity(Long item, String numCDT, BigDecimal vlrCDT, String fechaVen, String fechaProPago,
			String tipTasa, String desTasa ) {
		super();
		this.item = item;
		this.numCDT = numCDT;
		this.vlrCDT = vlrCDT;
		this.fechaVen = fechaVen;
		this.fechaProPago = fechaProPago;
		this.tipTasa = tipTasa;
		this.desTasa = desTasa;
	}
	
	public OplMaeCDTSCarEntity(Long item, String codIsin, String numCDT, String tipId, String idTit, String nomTit, String ctaInv,
			String plazo, String tipPlazo, String fechaEmi, String fechaVen, BigDecimal vlrCDT, String base, String periocidad, 
			BigDecimal spread, BigDecimal tasNom, BigDecimal tasEfe, String oficina, String posicion, String estado,
			String correo, String tipTasa, String desTasa , String fechaProPago , String fraccionado ) {
		super();
		this.item = item;
		this.codIsin = codIsin;
		this.numCDT = numCDT;
		this.ctaInv = ctaInv;
		this.tipId = tipId;
		this.idTit = idTit;
		this.nomTit = nomTit;
		this.fechaEmi = fechaEmi;
		this.fechaVen = fechaVen;
		this.tipPlazo = tipPlazo;
		this.plazo = plazo;
		this.base = base;
		this.periocidad = periocidad;;
		this.spread = spread;
		this.tasNom = tasNom;
		this.tasEfe = tasEfe;
		this.posicion = posicion;
		this.oficina = oficina;
		this.vlrCDT = vlrCDT;
		this.estado = estado;
		this.correo = correo;
		this.tipTasa = tipTasa;
		this.desTasa = desTasa;
		this.fechaProPago = fechaProPago;
		this.fraccionado = fraccionado;
	}*/
	
}
