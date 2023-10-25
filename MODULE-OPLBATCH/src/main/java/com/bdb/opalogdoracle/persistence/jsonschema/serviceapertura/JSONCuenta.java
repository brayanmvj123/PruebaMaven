package com.bdb.opalogdoracle.persistence.jsonschema.serviceapertura;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JSONCuenta {
    /**
     * Número de cuenta o número de cheque.
     */
    @NotNull
    @Pattern(regexp = "^([0-9]*)$", message = "Ingrese un número de cuenta válido.")
    private String numero;

    /**
     * Nombre de quien figura en la cuenta en CRM o el beneficiario del cheque
     */
    @NotNull
    private String titular;
}
