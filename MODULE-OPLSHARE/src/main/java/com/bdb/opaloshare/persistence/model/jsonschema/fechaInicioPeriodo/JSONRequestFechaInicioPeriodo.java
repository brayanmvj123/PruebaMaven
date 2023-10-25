package com.bdb.opaloshare.persistence.model.jsonschema.fechaInicioPeriodo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JSONRequestFechaInicioPeriodo implements Serializable {

    @NotNull
    @Valid
    @ApiModelProperty(notes = "Aplicación la cual consume el servicio", required = true)
    private String canal;

    @NotNull
    @Valid
    @ApiModelProperty(notes = "Parametros enviados a consultar la fecha de inicio de periodo", required = true)
    private ParametersFechaInicioPeriodo parametros;

    private static final long serialVersionUID = 1L;
}
