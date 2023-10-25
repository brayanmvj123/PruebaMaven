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
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OPL_HIS_CAMPOS_MONET_DOWN_TBL")
public class HisCamposMonetDownEntity implements Serializable, Comparable<HisCamposMonetDownEntity> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ITEM")
    private Long item;

    @Column(name = "DESC_CAMPO")
    private String descCampo;

    @Column(name = "ID_CAMPO")
    private String idCampo;

    @Column(name = "VALOR")
    private String valor;

    @ManyToOne
    @JoinColumn(name = "OPL_TRADUCTORES_TBL_ITEM")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private HisTraductoresDownEntity hisTraductoresDownEntity;

    @Override
    public int compareTo(HisCamposMonetDownEntity hisCamposMonetDownEntity) {
        return this.getIdCampo().compareTo(hisCamposMonetDownEntity.getIdCampo());
    }
}
