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
@Table(name="OPL_PAR_TIPDEPAR_DOWN_TBL")
public class TipDeparEntity implements Serializable{
	
	@Id
	@Column(name = "COD_DEP")
	private Integer codDep;
	
	@Column(name = "DES_DEP")
	private String desDep;
	
	@Column(name = "HOMO_CRM")
	private String homoCrm;
	
	@Column(name = "OPL_TIPPAIS_TBL_COD_PAIS")
	private Integer oplTippaisTblCodPais;

}
