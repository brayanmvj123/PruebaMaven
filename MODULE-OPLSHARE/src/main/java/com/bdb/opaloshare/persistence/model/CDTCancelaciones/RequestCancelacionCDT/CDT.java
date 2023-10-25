package com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CDT implements Serializable {

    @NotBlank
    @ApiModelProperty(value = "Descripción: Número antiguo del CDT, valor antes de la renovación", name = "numCdt", dataType = "nuevoNumCdt", required = true,
            example = "777", notes = "Número de CDT del titular")
    private String nuevoNumCdt;

    @NotBlank
    @ApiModelProperty(value = "Descripción: Número nuevo del CDT renovado, valor suministrado en el request", name = "anteriorNumCdt", dataType = "String", required = true,
            example = "340410141", notes = "Número de CDT del titular")
    private String anteriorNumCdt;

    private static final long serialVersionUID = 1L;

}
