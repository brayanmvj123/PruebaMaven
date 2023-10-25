package com.bdb.opaloshare.persistence.entity;

import com.bdb.opaloshare.util.enums.EnumEstadoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBF was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OPL_HIS_LOGIN_DOWN_TBL")
public class HisLoginDownEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM")
    private Long item;

    @Column(name = "USUARIO")
    private String usuario;

    @Column(name = "NOMBRES")
    private String nombres;

    @Column(name = "APELLIDOS")
    private String apellidos;

    @Column(name = "IDENTIFICACION")
    private String identificacion;

    @Column(name = "FECHA_CONEXION")
    private Date fecha_conexion;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "TOKEN")
    @JsonIgnore
    private String token;

    @OneToMany(targetEntity = HisUsuarioxrolDownEntity.class,
            mappedBy = "login",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<HisUsuarioxrolDownEntity> usuarioXroles;

    public List<String> toArrayValues(String tipoArchivo) {
        List<String> listRol = new ArrayList();
        List<String> values = new ArrayList();
        if (tipoArchivo.equals("1")) {
            values.add(this.usuario);
            values.add(String.valueOf(this.fecha_conexion));
        }
        if (tipoArchivo.equals("2")) {
            values.add(this.identificacion);
            values.add(this.usuario);
            //values.add(this.nombres);
            //values.add(this.apellidos);
            if (this.usuarioXroles.size() < 1) {
                values.add("Sin Roles Asociados...");
            } else {
                this.usuarioXroles.forEach((nameRol) -> listRol.add(nameRol.getRol().getNombreRol()));
                values.add(listRol.stream().map(Object::toString).collect(Collectors.joining(",")));
            }
            if (this.estado.equals("0")) {
                values.add(EnumEstadoUsuario.PERMANENTE.name());
            }
            if (this.estado.equals("1")) {
                values.add(EnumEstadoUsuario.ACTIVO.name());
            }
            if (this.estado.equals("2")) {
                values.add(EnumEstadoUsuario.INACTIVO.name());
            }
            values.add(new SimpleDateFormat("dd/MM/yyyy").format(this.fecha_conexion));
        }
        return values;
    }
}
