package com.bdb.opaloshare.persistence.entity;

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
import java.math.BigDecimal;
import java.util.List;

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
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OPL_HIS_TRADUCTORES_DOWN_TBL")
public class HisTraductoresDownEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ITEM")
    private Long item;

    @Column(name = "DESC_TRAN")
    private String descTran;

    @Column(name = "COD_ENTIDAD")
    private String codEntidad;

    @Column(name = "APL_FUENTE")
    private String aplFuente;

    @Column(name = "COD_TRAN")
    private String codTran;

    @Column(name = "FECHA_V")
    private String fechaV;

    @Column(name = "ORIGEN_V")
    private String origenV;

    @Column(name = "DESTINO_V")
    private String destinoV;

    @Column(name = "PROD_PRIN")
    private String prodPrin;

    @Column(name = "NRO_PROD_V")
    private String nroProdV;

    @Column(name = "TIP_NEGOCIO")
    private String tipNegocio;

    @Column(name = "NUM_NEGOCIO")
    private BigDecimal numNegocio;

    @Column(name = "COD_CAJERO")
    private String codCajero;

    @Column(name = "FILLER")
    private String filler;

    @Column(name = "CAMPOS_MONET")
    private String camposMonet;

    @Column(name = "TAMANO")
    private String tamano;

    @OneToMany(mappedBy = "hisTraductoresDownEntity",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<HisCamposMonetDownEntity> camposMonetarios;
}
