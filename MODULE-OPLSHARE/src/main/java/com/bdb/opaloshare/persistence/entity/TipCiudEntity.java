package com.bdb.opaloshare.persistence.entity;

import java.io.Serializable;

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
@Table(name="OPL_PAR_TIPCIUD_DOWN_TBL")
public class TipCiudEntity implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="COD_CIUD")
	private Integer codCiud;
	
	@Column(name="DES_CIUD")
	private String desCiud;
	
	@Column(name="HOMO_CRM")
	private String homoCrm;
	
	@Column(name="OPL_TIPDEPAR_TBL_COD_DEP")
	private Integer oplTipdeparTblCodDep;
	
	@Column(name="OPL_TIPPAIS_TBL_COD_PAIS")
	private Integer oplTippaisTblCodPais;
}
