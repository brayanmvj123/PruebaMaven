package com.bdb.oplbacthdiarios.persistence.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AcuRenovatesorDown {
	
	private int sysCargue;
	
	private Long cdtCancelado;
	
	private Long cdtReinvertido;
	
	private Long idCliente;
	
	private String nombre;
	
	private String fechaCancelacion;
	
	private String fechaReinversion;
	
	private Long nroOficina;
	
	private BigDecimal retencionFuente;
	
	private BigDecimal interesCancelado;
	
	private BigDecimal capitalCancelado;
	
	private BigDecimal valorReinvertido;

}
