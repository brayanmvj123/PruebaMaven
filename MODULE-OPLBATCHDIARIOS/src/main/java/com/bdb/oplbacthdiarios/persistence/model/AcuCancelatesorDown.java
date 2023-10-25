package com.bdb.oplbacthdiarios.persistence.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AcuCancelatesorDown {
	
	private int sysCargue;
	
	private Long idTransaccion;
	
	private Long cdtCancelado;
	
	private Long idCliente;
	
	private String nombre;
	
	private String fechaCancelacion;
	
	private String fechaAbono;
	
	private Long nroOficina;
	
	private String tipoCuenta;
	
	private String numeroCuenta;
	
	private String idBeneficiario;
	
	private String nombreBeneficiario;
	
	private BigDecimal retencionFuente;
	
	private BigDecimal interesCancelado;
	
	private BigDecimal capitalCancelado;
	
	private BigDecimal valorAbonado;

}
