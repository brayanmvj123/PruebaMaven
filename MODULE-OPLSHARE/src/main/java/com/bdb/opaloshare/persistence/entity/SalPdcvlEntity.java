package com.bdb.opaloshare.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

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
@Entity
@Table(name="OPL_SAL_PDCVL_DOWN_TBL")
//@IdClass(SalPdcvlPK.class)
public class SalPdcvlEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SECDIATXD")
	private Long secdiatxd;
	
	@Column(name="OFIORITX")
	private String ofioritx;
	
	@Column(name="CODINTTX")
	private String codinttx;

	@Column(name="CTACDTBB")
	private String ctacdtbb;

	@Column(name="TIPOCDT")
	private String tipocdt;

	@Column(name="OFDUENA")
	private String ofduena;

	@Column(name="NMBTIT1")
	private String nmbtit1;

	@Column(name="TIPDOC1")
	private String tipdoc1;

	@Column(name="NRODOC1")
	private String nrodoc1;

	@Column(name="PLAZOTI")
	private Integer plazoti;

	@Column(name="KAPITAL")
	private BigDecimal kapital;

	@Column(name="FCHVENC")
	private String fchvenc;

	@Column(name="CTAAABO")
	private String ctaaabo;

	@Column(name="DEPOSIT")
	private String deposit;

	@Column(name="RESPCDTS")
	private String respcdts;

	@Column(name="FECHAPE")
	private String fechape;

	@Column(name="TIPPROD")
	private String tipprod;

	@Column(name="CLASPER")
	private String clasper;

	@Column(name="ACTECO")
	private String acteco;

	@Column(name="NMBTIT2")
	private String nmbtit2;

	@Column(name="TIPDOC2")
	private String tipdoc2;

	@Column(name="NRODOC2")
	private String nrodoc2;

	@Column(name="NMBTIT3")
	private String nmbtit3;

	@Column(name="TIPDOC3")
	private String tipdoc3;

	@Column(name="NRODOC3")
	private String nrodoc3;

	@Column(name="NMBTIT4")
	private String nmbtit4;

	@Column(name="TIPDOC4")
	private String tipdoc4;

	@Column(name="NRODOC4")
	private String nrodoc4;

	@Column(name="TIPRELA")
	private String tiprela;

	@Column(name="DIRRESI")
	private String dirresi;

	@Column(name="BARRIO")
	private String barrio;

	@Column(name="CIUDAD")
	private String ciudad;

	@Column(name="TELERES")
	private String teleres;

	@Column(name="EXT_OFI")
	private String extOfi;

	@Column(name="EST_TIT")
	private String estTit;

	@Column(name="TIPRETE")
	private String tiprete;

	@Column(name="TASNOMI")
	private BigDecimal tasnomi;

	@Column(name="TASEFEC")
	private BigDecimal tasefec;

	@Column(name="TASADTF")
	private BigDecimal tasadtf;

	@Column(name="TIPTASA")
	private String tiptasa;

	@Column(name="SPREAD")
	private BigDecimal spread;

	@Column(name="FCHAPER")
	private String fchaper;

	@Column(name="BASELIQ")
	private String baseliq;

	@Column(name="PERIODI")
	private Integer periodi;

	@Column(name="TIPPLAZ")
	private String tipplaz;

	@Column(name="CIUDANE")
	private String ciudane;
	
}
