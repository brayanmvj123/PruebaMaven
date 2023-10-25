package com.bdb.opaloshare.persistence.model.jsonschema.simuladorcuota;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiOperation(value = "Parametros utilizados por el simulador", notes = "Parametros")
public class ParametersSimuladorCuota implements Serializable {

    @NotNull
    @Min(value=0, message = "No puede ser menor a 0")
    @Max(value=1000000000000000000L, message = "No puede ser mayor a 1000000000000000000 (10^18)")
    @Digits(integer = 19, fraction = 4, message = "No puede ser mayor a 1000000000000000000, no puede tener mas de 19 enteros y 4 decimales")
    @ApiModelProperty(notes = "Corresponde al campo VALOR de inversión del CDT debe ser de 16 enteros y 4 decimales.",
            example = "1000000", required = true)
    private String capital;

    @NotNull
    @Min(value=1, message = "No puede ser menor a 1")
    @Max(value=3, message = "No puede ser mayor a 3")
    @ApiModelProperty(notes = "Se debe tomar la opción que corresponda al producto seleccionado.",
            example = "2", allowableValues = "1 -> 365 , 2 -> 360 , 3 -> Real", required = true)
    private String base;

    @NotNull
    @Min(value=1, message = "No puede ser menor a 1")
    @Max(value=2, message = "No puede ser mayor a 2")
    @ApiModelProperty(notes = "Puede tener la opción de días o meses.", example = "1",
            allowableValues = "1 -> Dias , 2 -> Meses",required = true)
    private String tipoPlazo;

    @NotNull
    @Min(value=1, message = "No puede ser menor a 1")
    @Max(value=9999, message = "No puede ser mayor a 9999")
    @Digits(integer = 4, fraction = 0, message = "No puede ser mayor a 9999, no puede tener mas de 4 enteros y sin decimales")
    @ApiModelProperty(notes = "Representa la diferencia de dias entre la fecha de apertura y fecha de vencimiento (La diferencia debe ser en dias)",
            example = "90", required = true)
    private String diasPlazo;

    @NotNull
    @Min(value=0, message = "No puede ser menor a 1")
    @Max(value=20, message = "No puede ser mayor a 20")
    @ApiModelProperty(notes = "Se debe tomar la opción que corresponda al producto seleccionado.", example = "0",
            allowableValues = "0 -> Al plazo, 1 -> Mensual, 2 -> Bimestral, 3 -> Trimestral, 4 -> Cuatrimestral, 5 -> Cada 5 meses, " +
                    "6 -> Semestral, 7 -> Cada 7 meses, 8 -> Cada 8 meses, 10 -> Cada 10 meses, 12 -> Anual", required = true)
    private String periodicidad;

    @NotNull
    @Min(value=0, message = "No puede ser menor a 0")
    @Max(value=100, message = "No puede ser mayor a 100")
    @Digits(integer = 3, fraction = 4, message = "No puede ser mayor a 100, no puede tener mas de 3 enteros y 4 decimales")
    @ApiModelProperty(notes = "Este cálculo se debe digitar en el simulador o viene del archivo CDTSTESORddmmaa que en " +
            "principio es X 4% y luego el valor de retención se le resta al valor de los intereses y eso es lo que se paga de intereses.",
            example = "20", required = true)
    private String retencion;

    @NotNull
    @Min(value = 1, message = "No puede ser menor a 1 ")
    @Max(value = 7, message = "No puede ser mayor a 7")
    @ApiModelProperty(notes = "Se debe tomar la opción que corresponda al producto seleccionado.", allowableValues = "1 -> FIJA, " +
            "2 -> DTF, 3 -> IPC, 6 -> IBR un mes, 7 -> IBR Overnight, 8 -> IBR Tres Meses, 9 -> IBR seis Meses", example = "1" ,required = true)
    private Integer tipoTasa;

     @NotNull
     @Min(value=0, message = "No puede ser menor a 0")
     @Max(value=100, message = "No puede ser mayor a 100")
     @Digits(integer = 3, fraction = 4, message = "No puede ser mayor a 100, no puede tener mas de 3 enteros y 4 decimales")
     @ApiModelProperty(notes = "Se debe tomar la opción que corresponda al producto seleccionado.", example = "3.85", required = true)
     private String tasa;

    @NotNull
    @Min(value=0, message = "No puede ser menor a 0")
    @Max(value=100, message = "No puede ser mayor a 100")
    @Digits(integer = 3, fraction = 4, message = "No puede ser mayor a 100, no puede tener mas de 3 enteros y 4 decimales")
    @ApiModelProperty(notes = "Se debe tomar la opción que corresponda al producto seleccionado.", example = "3.85", required = true)
    private String spread;

    private static final long serialVersionUID = 1L;
}
