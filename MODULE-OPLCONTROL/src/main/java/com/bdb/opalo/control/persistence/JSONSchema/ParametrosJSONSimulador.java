package com.bdb.opalo.control.persistence.JSONSchema;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ParametrosJSONSimulador {
    @NotNull
    @Min(value=0, message = "No puede ser menor a 0")
    @Max(value=100, message = "No puede ser mayor a 100")
    @Digits(integer = 3, fraction = 4, message = "No puede ser mayor a 100, no puede tener mas de 3 enteros y 4 decimales")
    private String tasaFija;

    @NotNull
    @Min(value=1, message = "No puede ser menor a 1")
    @Max(value=20, message = "No puede ser mayor a 20")
    private String periodicidad;

    @NotNull
    @Min(value=1, message = "No puede ser menor a 1")
    @Max(value=3, message = "No puede ser mayor a 3")
    private String base;

    @NotNull
    @Min(value=1, message = "No puede ser menor a 1")
    @Max(value=9999, message = "No puede ser mayor a 9999")
    @Digits(integer = 4, fraction = 0, message = "No puede ser mayor a 9999, no puede tener mas de 4 enteros y sin decimales")
    private String diasPlazo;

    @NotNull
    @Min(value=0, message = "No puede ser menor a 0")
    @Max(value=1000000000000000000L, message = "No puede ser mayor a 1000000000000000000 (10^18)")
    @Digits(integer = 19, fraction = 4, message = "No puede ser mayor a 1000000000000000000, no puede tener mas de 19 enteros y 4 decimales")
    private String capital;

    @NotNull
    @Min(value=0, message = "No puede ser menor a 0")
    @Max(value=100, message = "No puede ser mayor a 100")
    @Digits(integer = 3, fraction = 4, message = "No puede ser mayor a 100, no puede tener mas de 3 enteros y 4 decimales")
    private String retencion;

}
