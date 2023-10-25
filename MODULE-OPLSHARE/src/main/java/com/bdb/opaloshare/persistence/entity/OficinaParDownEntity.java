package com.bdb.opaloshare.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="OPL_PAR_OFICINA_DOWN_TBL")
public class OficinaParDownEntity implements Serializable{

	@Id
	@Column(name="NRO_OFICINA")
	private Integer nroOficina;
	
	@Column(name="DESC_OFICINA")
	private String descOficina;
	
	@Column(name="OPL_TIPOFICINA_TBL_TIP_OFICINA")
	private Integer oplTipoficinaTblTipOficina;
	
	@Column(name="OPL_OFICINA_TBL_NRO_OFICINA")
	private Integer oplOficinaTblnroOficina;

	@Column(name = "OPL_ESTADOS_TBL_TIP_ESTADO")
	private Integer oplEstadosTblTipEstado;

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
}
