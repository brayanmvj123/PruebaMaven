package com.bdb.opaloshare.persistence.model.jsonschema.obtenerTasa;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JSONRequestTasaVariableCofnal implements Serializable {

    @NotNull
    @Valid
    @ApiModelProperty(notes = "Aplicación la cual consume el servicio", required = true)
    private String canal;

    @NotNull
    @Valid
    @ApiModelProperty(notes = "Parametros enviados para filtrar la busqueda", required = true)
    private ParametersTasaVariableCofnal parametros;

}
