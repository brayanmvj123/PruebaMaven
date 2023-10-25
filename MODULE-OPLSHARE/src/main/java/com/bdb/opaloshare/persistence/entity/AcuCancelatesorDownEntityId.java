package com.bdb.opaloshare.persistence.entity;

import java.io.Serializable;

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
public class AcuCancelatesorDownEntityId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sysCargue;
	private Long idTransaccion;
	private Long cdtCancelado;
}
