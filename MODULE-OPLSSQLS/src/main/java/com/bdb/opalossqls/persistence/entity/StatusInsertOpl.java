package com.bdb.opalossqls.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="DCV_STATUS_INSERT_OPL")
public class StatusInsertOpl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="item")
	private Long item;
	
	@Column(name="fecha")
	private LocalDate fecha;
	
	@Column(name="estado")
	private String estado;
	
	public StatusInsertOpl(Long item , String estado) {
		this.item = item;
		this.estado = estado;
	}
	
	public StatusInsertOpl(Long item) {
		this.item = item;
	}

}
