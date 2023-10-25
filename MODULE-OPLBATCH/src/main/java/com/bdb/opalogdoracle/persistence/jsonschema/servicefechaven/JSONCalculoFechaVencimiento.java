package com.bdb.opalogdoracle.persistence.jsonschema.servicefechaven;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JSONCalculoFechaVencimiento {

    @NotNull
    @Digits(integer = 5, fraction = 0)
    private Long plazo;

    @NotNull
    @Digits(integer = 1, fraction = 0)
    private Integer tipoPlazo;

    @NotNull
    @Pattern(regexp = "^([0-9]{4}/[0-9]{2}/[0-9]{2})$", message = "El formato de la fecha debe ser YYYY/MM/DD.")
    private String fechaApertura;

    @NotNull
    @Digits(integer = 1 , fraction = 0)
    private Integer base;

}
