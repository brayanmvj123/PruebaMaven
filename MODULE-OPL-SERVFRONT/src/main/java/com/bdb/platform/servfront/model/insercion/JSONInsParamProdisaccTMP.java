package com.bdb.platform.servfront.model.insercion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JSONInsParamProdisaccTMP {
    @NotNull
    private Integer codEmi;
    @NotNull
    private String fechaAsam;
    @NotNull
    private String codIsin;
    @NotNull
    private BigDecimal cantAcc;
    @NotNull
    private BigDecimal valDiv;
    @NotNull
    private BigDecimal valNom;
    @NotNull
    private Integer tipAcc;
    @NotNull
    private String usrRadic;
    @NotNull
    private Integer yearUvt;
    @NotNull
    private BigDecimal valUvt;
    @NotNull
    private String usrGestor;
    @NotNull
    private String observaciones;
    @NotNull
    private Integer estado;
}
