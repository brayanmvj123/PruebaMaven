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
@Table(name="OPL_PAR_TIPTASA_DOWN_TBL")
public class TiptasaParDownEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TIP_TASA")
	private Integer tipTasa;
	
	@Column(name="DESC_TASA")
	private String descTasa;
	
	@Column(name="HOMO_DCVBTA")
	private String homoDcvbta;
	
}
