package com.bdb.opaloshare.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Copyright (c) 2019 Banco de Bogotá. All Rights Reserved.
 * <p>
 * ACCIONESBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OPL_PAR_CONEXIONES_DOWN_TBL")
public class ParConexionesDownEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_CONEXION")
    private Long idConexion;

    @Column(name = "TIP_CONEXION")
    private String tipConexion;

    @Column(name = "NOMBRE_HOST")
    private String nombreHost;

    @Column(name = "HOST_IP")
    private String hostIp;

    @Column(name = "PUERTO")
    private Long puerto;

    @Column(name = "RUTA")
    private String ruta;

    @Column(name = "DESC_CONEX")
    private String descConex;

    @ManyToOne
    @JoinColumn(name = "OPL_USERCONEX_TBL_ID_USUARIO")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ParUserconexDownEntity usuario;
}
