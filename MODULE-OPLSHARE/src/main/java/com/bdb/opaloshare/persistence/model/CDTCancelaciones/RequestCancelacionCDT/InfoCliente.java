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
public class InfoCliente implements Serializable
{
    @NotBlank
    @ApiModelProperty(value = "Descripción: Número de identificación del titular", name = "id", dataType = "String", required = true,
            example = "8300237821", notes = "Número de identificación del titular")
    private String id;

    @NotBlank
    @ApiModelProperty(value = "Descripción: Nombre del titular del CDT", name = "nombre", dataType = "String", required = true,
            example = "ASOCIACION NACIONAL DE EMPRESAS DE SERVICIOS PUBLI", notes = "Nombre del titular del CDT")
    private String nombre;

    @NotNull
    @ApiModelProperty(value = "Descripción: Tipo de identificación del titular por código: 1=CC, 2=CE, 3=NIP, etc.", name = "tipoTit", dataType = "Integer", required = true,
            example = "1", notes = "Tipo de identificación del titular por código: 1=CC, 2=CE, 3=NIP, etc.")
    private Integer tipoTit;

    private static final long serialVersionUID = 1L;
}
