package com.bdb.opaloshare.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="OPL_PAR_TIPID_DOWN_TBL")
public class TipidParDownEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_ID")
	private Integer codId;
	
	@Column(name="NOM_ID")
	private String nomId;
	
	@Column(name="HOMO_DCVBTA")
	private String homoDcvbta;
	
	@Column(name="HOMO_DCVSA")
	private String homoDcvsa;
	
	@Column(name="HOMO_CRM")
	private String homoCrm;
	
	@Column(name="HOMO_MERC")
	private String homoMerc;
	
	
	
}
