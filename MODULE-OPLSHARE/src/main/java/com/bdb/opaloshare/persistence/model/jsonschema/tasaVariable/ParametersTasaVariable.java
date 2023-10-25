package com.bdb.opaloshare.persistence.model.jsonschema.tasaVariable;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiOperation(value = "Parametros utilizados por el simulador", notes = "Parametros")
public class ParametersTasaVariable implements Serializable {

    @NotNull
    @Min(value = 1, message = "No puede ser menor a 1 ")
    @Max(value = 7, message = "No puede ser mayor a 7")
    @ApiModelProperty(notes = "Se debe tomar la opción que corresponda al producto seleccionado.", allowableValues = "1 -> FIJA, " +
            "2 -> DTF, 3 -> IPC, 6 -> IBR un mes, 7 -> IBR Overnight, 8 -> IBR Tres Meses, 9 -> IBR seis Meses", example = "1" ,required = true)
    private Integer tipotasa;

    @ApiModelProperty(notes = "fecha a consultar", required = false)
    @Pattern(regexp = "^([0-9]{4}/[0-9]{2}/[0-9]{2})$", message = "El formato de la fecha debe ser YYYY/MM/DD.")
    private String fecha;

    private static final long serialVersionUID = 1L;
}
