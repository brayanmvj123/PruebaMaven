package com.bdb.oplbacthdiarios.persistence.jsonschema.tasaVariableCofnal;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiOperation(value = "Parametros utilizados para filtrar la consulta.", notes = "Parametros")
public class ParametersTasaVariableCofnal implements Serializable {

    @NotNull
    @Pattern(regexp = "^([0-9]{4}/[0-9]{2}/[0-9]{2})$", message = "El formato de la fecha debe ser YYYY/MM/DD.")
    @ApiModelProperty(notes = "Fecha de inicio.", example = "2020/09/03", required = true)
    private String fechaInicio;

    @NotNull
    @Pattern(regexp = "^([0-9]{4}/[0-9]{2}/[0-9]{2})$", message = "El formato de la fecha debe ser YYYY/MM/DD.")
    @ApiModelProperty(notes = "Fecha fin.", example = "2020/12/03", required = true)
    private String fechaFin;
}
