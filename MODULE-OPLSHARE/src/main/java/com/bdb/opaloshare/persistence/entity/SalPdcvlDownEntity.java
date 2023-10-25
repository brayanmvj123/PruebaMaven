package com.bdb.opaloshare.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "OPL_SAL_PDCVL_DOWN_TBL")
public class SalPdcvlDownEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SECDIATXD")
    private Long secdiatxd;

    @Column(name = "OFIORITX")
    private String ofioritx;

    @Column(name = "CODINTTX")
    private String codIntTx;

    @Column(name = "CTACDTBB")
    private String ctacdtbb;

    @Column(name = "TIPOCDT")
    private String tipoCdt;

    @Column(name = "OFDUENA")
    private String ofDuena;

    @Column(name = "NMBTIT1")
    private String nmbTit1;

    @Column(name = "TIPDOC1")
    private String tipDoc1;

    @Column(name = "NRODOC1")
    private String nroDoc1;

    @Column(name = "PLAZOTI")
    private int plazoTi;

    @Column(name = "KAPITAL")
    private BigDecimal kapital;

    @Column(name = "FCHVENC")
    private String fchVenc;

    @Column(name = "CTAAABO")
    private String ctaaabo;

    @Column(name = "DEPOSIT")
    private String deposit;

    @Column(name = "RESPCDTS")
    private String respCdts;

    @Column(name = "FECHAPE")
    private String fechaPe;

    @Column(name = "TIPPROD")
    private String tipProd;

    @Column(name = "CLASPER")
    private String clasPer;

    @Column(name = "ACTECO")
    private String acteco;

    @Column(name = "NMBTIT2")
    private String nmbTit2;

    @Column(name = "TIPDOC2")
    private String tipDoc2;

    @Column(name = "NRODOC2")
    private String nroDoc2;

    @Column(name = "NMBTIT3")
    private String nmbTit3;

    @Column(name = "TIPDOC3")
    private String tipDoc3;

    @Column(name = "NRODOC3")
    private String nroDoc3;

    @Column(name = "NMBTIT4")
    private String nmbTit4;

    @Column(name = "TIPDOC4")
    private String tipDoc4;

    @Column(name = "NRODOC4")
    private String nroDoc4;

    @Column(name = "TIPRELA")
    private String tiprela;

    @Column(name = "DIRRESI")
    private String dirresi;

    @Column(name = "BARRIO")
    private String barrio;

    @Column(name = "CIUDAD")
    private String ciudad;

    @Column(name = "TELERES")
    private String teleres;

    @Column(name = "EXT_OFI")
    private String extOfi;

    @Column(name = "EST_TIT")
    private String estTit;

    @Column(name = "TIPRETE")
    private String tiprete;

    @Column(name = "TASNOMI")
    private BigDecimal tasNomi;

    @Column(name = "TASEFEC")
    private BigDecimal tasaEfec;

    @Column(name = "TASADTF")
    private BigDecimal tasaDtf;

    @Column(name = "TIPTASA")
    private String tipTasa;

    @Column(name = "SPREAD")
    private BigDecimal spread;

    @Column(name = "FCHAPER")
    private String fchaPer;

    @Column(name = "BASELIQ")
    private String baseLiq;

    @Column(name = "PERIODI")
    private int periodi;

    @Column(name = "TIPPLAZ")
    private String tipPlaz;
}
