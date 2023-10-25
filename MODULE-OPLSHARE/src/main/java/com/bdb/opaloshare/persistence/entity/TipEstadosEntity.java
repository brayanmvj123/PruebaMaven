package com.bdb.opaloshare.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="OPL_PAR_ESTADOS_DOWN_TBL")
public class TipEstadosEntity implements Serializable {

	@Id
	@Column(name="TIP_ESTADO")
	private Integer tipEstado;

	@Column(name="DESC_ESTADO")
	private String descEstado;

	private static final long serialVersionUID = 1L;
}
