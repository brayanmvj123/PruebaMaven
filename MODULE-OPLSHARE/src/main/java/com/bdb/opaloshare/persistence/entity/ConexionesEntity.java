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
@Table(name="OPL_PAR_CONEXIONES_DOWN_TBL")
public class ConexionesEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_CONEXION")
	private Integer idConexion;
	
	@Column(name="TIP_CONEXION")
	private String tipConexion;
	
	@Column(name="NOMBRE_HOST")
	private String nombre;
	
	@Column(name="HOST_IP")
	private String hostIp;
	
	@Column(name="PUERTO")
	private Integer puerto;
	
	@Column(name="OPL_USERCONEX_TBL_ID_USUARIO")
	private Integer oplUserconexTblIdUsuario;
	
	@Column(name="RUTA")
	private String ruta;
	
	@Column(name="DESC_CONEX")
	private String descConex;
	
	public String user;
	
	public String pass;
	
	public ConexionesEntity(String user , String pass , String hostIp , Integer puerto , String ruta) {
		this.hostIp = hostIp;
		this.puerto = puerto;
		this.user = user;
		this.pass = pass;
		this.ruta = ruta;
	}
	
	public ConexionesEntity(String ruta) {
		this.ruta = ruta;
	}

}
