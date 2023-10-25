package com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCancelCDT implements Serializable
{
    @NotBlank
    @ApiModelProperty(value = "Descripción: Nombre del canal de ejecución que realiza la petición", name = "canal", dataType = "String", required = true,
            example = "OPALO", notes = "Nombre del canal de ejecución que realiza la petición")
    private String canal;

    @Valid
    @NotEmpty
    private List<InfoCliente> infoCliente = null;

    @NotBlank
    @ApiModelProperty(value = "Descripción: Número de CDT del titular", name = "numCdt", dataType = "String", required = true,
            example = "340410141", notes = "Número de CDT del titular")
    private String numCdt;

    @NotBlank
    @ApiModelProperty(value = "Descripción: Fecha de ejecución de la petición.", name = "fecha", dataType = "String", required = true,
            example = "2021-11-21 10:31:00", notes = "Fecha de ejecución de la petición")
    private String fecha;

    @Valid
    private InfoOficina infoOficina;

    @Valid
    private InfoTrans infoTrans;

    private static final long serialVersionUID = 1L;
}
