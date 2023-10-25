package com.bdb.opalotasasfija.persistence.JSONSchema.request.simuladorcuota;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JSONRequestSimuladorCuota implements Serializable {

    @NotNull
    @Valid
    @ApiModelProperty(notes = "Aplicación la cual consume el servicio", required = true)
    private String canal;

    @NotNull
    @Valid
    @ApiModelProperty(notes = "Parametros enviados a simular la tasa deseda", required = true)
    private ParametersSimuladorCuota parametros;

}
