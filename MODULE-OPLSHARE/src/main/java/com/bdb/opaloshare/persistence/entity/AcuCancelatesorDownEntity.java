package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(AcuCancelatesorDownEntityId.class)
@Table(name="OPL_ACU_CANCELATESOR_DOWN_TBL")
public class AcuCancelatesorDownEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Id @Column(name="SYS_CARGUE")
	private int sysCargue;
	
	@Id @Column(name="ID_TRANSACCION")
	private Long idTransaccion;
	
	@Id @Column(name="CDT_CANCELADO")
	private Long cdtCancelado;
	
	@Column(name="ID_CLIENTE")
	private Long idCliente;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="FECHA_CANCELACION")
	private LocalDate fechaCancelacion;
	
	@Column(name="FECHA_ABONO")
	private LocalDate fechaAbono;
	
	@Column(name="NRO_OFICINA")
	private Long nroOficina;
	
	@Column(name="TIPO_CUENTA")
	private String tipoCuenta;
	
	@Column(name="NUMERO_CUENTA")
	private String numeroCuenta;
	
	@Column(name="ID_BENEFICIARIO")
	private String idBeneficiario;
	
	@Column(name="NOMBRE_BENEFICIARIO")
	private String nombreBeneficiario;
	
	@Column(name="RETENCION_FUENTE")
	private BigDecimal retencionFuente;
	
	@Column(name="INTERES_CANCELADO")
	private BigDecimal interesCancelado;
	
	@Column(name="CAPITAL_CANCELADO")
	private BigDecimal capitalCancelado;
	
	@Column(name="VALOR_ABONADO")
	private BigDecimal valorAbonado;
	
	public List<String> toArrayValues() {
		
		List<String> values = new ArrayList<>();
 
		//values.add(String.valueOf(sysCargue));
		//values.add(String.valueOf(idTransaccion));
		values.add(String.valueOf(cdtCancelado));
		values.add(nombre);
		values.add(String.valueOf(idCliente));
		values.add(fechaCancelacion.toString());
		values.add(fechaAbono.toString());
		values.add(String.valueOf(nroOficina));
		values.add(tipoCuenta);
		values.add(numeroCuenta);
		values.add(nombreBeneficiario);
		values.add(idBeneficiario);
		values.add(String.valueOf(retencionFuente));
		//values.add(String.valueOf(interesCancelado));
		//values.add(String.valueOf(capitalCancelado));
		values.add(String.valueOf(valorAbonado));
		values.add(String.valueOf(valorAbonado));// para el total
		
		return values;
	}


}
