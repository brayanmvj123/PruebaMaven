package com.bdb.opaloshare.persistence.entity;

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
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

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
@Table(name = "OPL_PAR_ROLESXGDA_DOWN_TBL")
public class ParRolesxgdaDownEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_ROL")
    private Long idRol;

    @Column(name = "NOMBRE_ROL")
    private String nombreRol;

    @Column(name = "GRUPO_DA")
    private String grupoDa;

    @OneToMany(targetEntity = HisUsuarioxrolDownEntity.class,
            mappedBy = "rol",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<HisUsuarioxrolDownEntity> usuarioXroles;

    @OneToMany(targetEntity = ParVistasxrolDownEntity.class,
            mappedBy = "rol",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<ParVistasxrolDownEntity> vistasXroles;
}
