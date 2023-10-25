package com.bdb.opaloshare.persistence.model.jsonschema.fechaInicioPeriodo;

import io.swagger.annotations.ApiOperation;
import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiOperation(value = "Parametros utilizados por la calculadora", notes = "Parametros")
public class ParametersFechaInicioPeriodo implements Serializable {

    @NotNull
    @Digits(integer = 5, fraction = 0)
    private Long plazo;

    @NotNull
    @Digits(integer = 1, fraction = 0)
    private Integer tipoPlazo;

    @NotNull
    @Pattern(regexp = "^([0-9]{4}/[0-9]{2}/[0-9]{2})$", message = "El formato de la fecha debe ser YYYY/MM/DD.")
    private String fechaVencimiento;

    @NotNull
    @Pattern(regexp = "^([0-9]{4}/[0-9]{2}/[0-9]{2})$", message = "El formato de la fecha debe ser YYYY/MM/DD.")
    private String fechaProxPago;

    @NotNull
    @Digits(integer = 1 , fraction = 0)
    private Integer base;

    @NotNull
    @Digits(integer = 2 , fraction = 0)
    private Integer periodicidad;

    private static final long serialVersionUID = 1L;
}
