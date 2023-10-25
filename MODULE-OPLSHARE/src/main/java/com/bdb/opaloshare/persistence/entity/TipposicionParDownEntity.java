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
@Table(name="OPL_PAR_TIPPOSICION_DOWN_TBL")
public class TipposicionParDownEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TIP_POSICION")
	private Integer tipPosicion;
	
	@Column(name="DESC_POSICION")
	private String descPosicion;
	
	@Column(name="HOMO_DCVBTA")
	private String homoDcvbta;
	
}
