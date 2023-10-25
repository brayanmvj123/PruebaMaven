package com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestQueryCDT {

    @NotBlank
    @ApiModelProperty(value = "Descripción: Tipo de identificación del titular por código: 1=CC, 2=CE, 3=NIP, etc.", name = "tipId", dataType = "String", required = true,
            example = "1", notes = "Tipo de identificación del titular por código: 1=CC, 2=CE, 3=NIP, etc.")
    private String tipId;

    @NotBlank
    @ApiModelProperty(value = "Descripción: Número de identificación del titular", name = "numTit", dataType = "String", required = true,
            example = "008908032367", notes = "Número de identificación del titular")
    private String numTit;

    @NotNull
    @ApiModelProperty(value = "Descripción: Número de CDT del titular", name = "numCdt", dataType = "Long", required = true,
            example = "250311982", notes = "Número de CDT del titular")
    private Long numCdt;

    @NotNull
    @ApiModelProperty(value = "Descripción: Número de oficina de apertura y/o renovación", name = "nroOficina", dataType = "Long", required = true,
            example = "1198", notes = "Número de oficina de apertura y/o renovación del CDT")
    private Integer oficina;

    @ApiModelProperty(value = "Descripción: Código Isin", name = "codIsin", dataType = "String", required = false,
            example = "COB01CD0EC24", notes = "Código Isin")
    private String codIsin;

    @ApiModelProperty(value = "Descripción: Número de cuenta del inversionista titular", name = "ctaInv", dataType = "String", required = false,
            example = "00394237", notes = "Número de cuenta del inversionista titular")
    private String ctaInv;

    @NotNull
    @ApiModelProperty(value = "Descripción: Canal", name = "canal", dataType = "String", required = true,
            example = "opalo", notes = "Canal")
    private String canal;
}
