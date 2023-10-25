package com.bdb.opaloshare.persistence.model.trad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
 * This file war write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JSONMonetaryField {

    /**
     * Identificador del campo dentro de la transacción.
     */
    @NotNull
    @Pattern(regexp = "^([0-9]{2})$")
    private String identificador;

    /**
     * Valor del campo dentro de la transacción.
     */
    @NotNull
    @Digits(integer = 16, fraction = 2)
    private BigDecimal valor;
}
