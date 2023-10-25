package com.bdb.opaloshare.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name="OPL_HIS_CLIXCDT_LARGE_TBL")
public class HisClixCDTLarge implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ITEM")
	private Long item;
	
	@Column(name="OPL_CLIENTES_TBL_NUM_TIT")
	private String oplClientesTblNumTit;
	
	@Column(name="OPL_CDTS_TBL_NUM_CDT")
	private String oplCdtsTblNumCdt;
	
	@Column(name="CTA_INV")
	private String ctaInv;
	
	@Column(name="TIP_TITULAR")
	private String tipTitular;

}
