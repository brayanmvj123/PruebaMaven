package com.bdb.oplbacthdiarios.persistence.model;

import lombok.*;

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
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TasaVariableCofnalModel implements Serializable {

        private String fecha;
        private Double tasaVarDtf;
        private Double tasaVarIpc;
        private Double tasaVarIbr;
        private Double tasaVarIbrDiaria;
        private Double tasaVarIbrTrimestral;
        private Double tasaVarIbrSemestral;

}
