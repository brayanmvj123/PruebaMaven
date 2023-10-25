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
@NoArgsConstructor
@AllArgsConstructor
public class Parametros implements Serializable {

    @NotBlank
    @ApiModelProperty(value = "Descripción: Número de identificación del titular", name = "id", dataType = "String", required = true,
            example = "8300237821", notes = "Número de identificación del titular")
    private String id;

    @NotBlank
    @ApiModelProperty(value = "Descripción: Tipo de identificación del titular por código: 1=CC, 2=CE, 3=NIP, etc.", name = "tipoTit", dataType = "Integer", required = true,
            example = "1", notes = "Tipo de identificación del titular por código: 1=CC, 2=CE, 3=NIP, etc.")
    private String tipoId;

    private static final long serialVersionUID = 1L;
}
