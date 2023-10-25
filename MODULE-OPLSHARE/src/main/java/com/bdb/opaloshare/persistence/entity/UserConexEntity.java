package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="OPL_PAR_USERCONEX_DOWN_TBL")
public class UserConexEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_USUARIO")
	private Integer idUsuario;
	
	@Column(name="NOMBRE_USUARIO")
	private String nombreUsuario;
	
	@Column(name="CONTRASENA")
	private String contrasena;

}
