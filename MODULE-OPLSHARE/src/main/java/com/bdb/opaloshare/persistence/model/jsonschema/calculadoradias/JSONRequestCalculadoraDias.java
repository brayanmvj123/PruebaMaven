package com.bdb.opaloshare.persistence.model.jsonschema.calculadoradias;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiOperation(value = "Parametros utilizados por la calculadora", notes = "Parametros")
public class JSONRequestCalculadoraDias implements Serializable {

    @NotNull
    @Pattern(regexp = "^([0-9]{4}/[0-9]{2}/[0-9]{2})$", message = "El formato de la fecha debe ser YYYY/MM/DD.")
    @ApiModelProperty(notes = "Fecha de la apertura del CDT Digital.", example = "2020/09/03", required = true)
    private String fechaApertura;

    @NotNull
    @Pattern(regexp = "^([0-9]{4}/[0-9]{2}/[0-9]{2})$", message = "El formato de la fecha debe ser YYYY/MM/DD.")
    @ApiModelProperty(notes = "Fecha del vencimiento del CDT Digital.", example = "2020/12/03", required = true)
    private String fechaVencimiento;

    @NotNull
    @Min(value = 1, message = "La base debe ser mayot o igual a 1.")
    @Max(value = 3, message = "La base debe ser menor o igual a 3.")
    @Digits(integer = 1 , fraction = 0)
    @ApiModelProperty(notes = "Se debe tomar la opción que corresponda al producto seleccionado.",
            example = "2", allowableValues = "1 , 2 , 3 ", required = true)
    private Integer base;

    private static final long serialVersionUID = 1L;

}
