package com.bdb.opaloshare.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

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
@Table(name = "OPL_PAR_VISTASXROL_DOWN_TBL")
@IdClass(value = ViewByRoleFKEntity.class)
public class ParVistasxrolDownEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "OPL_ROLESXGDA_TBL_ID_ROL")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ParRolesxgdaDownEntity rol;

    @Id
    @ManyToOne
    @JoinColumn(name = "OPL_VISTASAPP_TBL_ID_FUNCIONALIDAD")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ParVistasappDownEntity vista;
}
