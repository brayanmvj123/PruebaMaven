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
@Table(name="OPL_PAR_TIPPLAZO_DOWN_TBL")
public class TipplazoParDownEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TIP_PLAZO")
	private Integer tipPlazo;
	
	@Column(name="DESC_PLAZO")
	private String descPlazo;
	
	@Column(name="HOMO_DCVBTA")
	private String homoDcvbta;
	
}
