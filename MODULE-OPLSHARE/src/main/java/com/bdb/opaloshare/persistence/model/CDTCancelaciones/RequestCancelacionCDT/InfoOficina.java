package com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoOficina implements Serializable
{
    @NotNull
    @ApiModelProperty(value = "Descripción: Número de oficina de origen", name = "ofiOrigen", dataType = "Integer", required = true,
            example = "2014", notes = "Número de oficina de origen")
    private Integer ofiOrigen;

    @NotNull
    @ApiModelProperty(value = "Descripción: Número de oficina de destino", name = "ofiDestino", dataType = "Integer", required = true,
            example = "2014", notes = "Número de oficina de destino")
    private Integer ofiDestino;

    private static final long serialVersionUID = 1L;
}
