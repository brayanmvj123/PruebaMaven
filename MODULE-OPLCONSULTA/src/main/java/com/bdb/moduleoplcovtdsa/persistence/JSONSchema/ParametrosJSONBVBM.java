package com.bdb.moduleoplcovtdsa.persistence.JSONSchema;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ParametrosJSONBVBM {

    @NotNull
    @Size(max = 15, message = "Ingrese una identificacion válido de máximo 15 caracteres.")
    @ApiModelProperty(notes = "Identificación del cliente", required = true, name = "Identificación",
            dataType = "String")
    private String identificacion;

    @NotNull
    @Size(max = 2, message = "Ingrese una tipo de documento válido de máximo 1 caracter.")
    @ApiModelProperty(notes = "Tipo de documento del cliente", required = true, name = "Tipo de documento",
            dataType = "String")
    private String tipoDocumento;
}
