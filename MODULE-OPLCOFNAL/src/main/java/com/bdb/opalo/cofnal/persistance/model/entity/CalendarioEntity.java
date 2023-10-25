package com.bdb.opalo.cofnal.persistance.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.Immutable;

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
@Table(name = "vwcalendar")
@Immutable
public class CalendarioEntity implements Serializable {

    @Id
    @Column(name = "ID")
    Integer item;

    @Column(name = "DIA")
    Integer dia;

    @Column(name = "MES")
    Integer mes;

    @ApiModelProperty(notes = "<p>Valor:</p> <ul><li>0 => dia habil.</li>" +
            "<li>16 y 17 => fin de semana.</li>" +
            "<li>1 => festivos.</li></ul>")
    @Column(name = "VALOR")
    Integer valor;
}
