package com.bdb.opalogdoracle.persistence.jsonschema.serviceapertura;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JSONPlazoCDT {

    @NotNull
    @Pattern(regexp = "^([1-2])$", message = "El tipo de plazo debe ser 1 (Meses), o 2 (Días).")
    private String tipo;

    @NotNull(message = "Debe agregar la cantidad del plazo seleccionado.")
    private Integer cantidadPlazo;
}
