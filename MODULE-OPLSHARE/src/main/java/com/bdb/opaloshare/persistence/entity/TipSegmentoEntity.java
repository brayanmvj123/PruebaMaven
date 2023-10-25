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
@Table(name="OPL_PAR_TIPSEGMENTO_DOWN_TBL")
public class TipSegmentoEntity implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="COD_SEGMENTO")
	private Integer codSegmento;
	
	@Column(name="DES_SEGMENTO")
	private String desSegmento;
	
	@Column(name="HOMO_CRM")
	private String homoCrm;

}
