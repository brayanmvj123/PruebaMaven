package com.bdb.opaloshare.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="OPL_PAR_TIPOFICINA_DOWN_TBL")
public class TipOficinaParDownEntity implements Serializable {

	@Id
	@Column(name="TIP_OFICINA")
	@Schema(description = "Descripción: Id de oficina", name = "tipOficina", type = "Integer", required = true,
			example = "1")
	private Integer tipOficina;
	
	@Column(name = "DESC_OFICINA")
	@Schema(description = "Descripción: Descripción de oficina", name = "descOficina", type = "String", required = true,
			example = "OFICINA")
	private String descOficina;

	private static final long serialVersionUID = 1L;
}
