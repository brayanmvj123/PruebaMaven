package com.bdb.opalo.cofnal.persistance.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by William Vasquez <WVASQU1@bancodebogota.com.co>.
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vwtasasvar")
@org.hibernate.annotations.Immutable
public class TasaVariableCofnalEntity implements Serializable {

    @Id
    @Column(name = "fectasvar")
    private String fecha;

    @Column(name = "tasvardtf")
    private Double tasaVarDtf;

    @Column(name = "tasvaripc")
    private Double tasaVarIpc;

    @Column(name = "tasvaribr")
    private Double tasaVarIbr;

    @Column(name = "tasvaribrdiaria")
    private Double tasaVarIbrDiaria;

    @Column(name = "tasvaribrtrimest")
    private Double tasaVarIbrTrimestral;

    @Column(name = "tasvaribrsemestr")
    private Double tasaVarIbrSemestral;

}
