package com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoCta implements Serializable
{
    @JsonProperty("numCta")
    @ApiModelProperty(value = "Descripción: Número de cuenta a la cual se va a realizar el pago, solo aplica cuanto el tipo de proceso es 1 o 2, " +
            "si el proceso es diferente a 1 y 2, estos campos pueden ser vacíos o tener un valor de 0", name = "numCta", dataType = "Long",
            example = "654536291", notes = "Número de cuenta a la cual se va a realizar el pago, solo aplica cuanto el tipo de proceso es 1 o 2" +
            "si el proceso es diferente a 1 y 2, estos campos pueden ser vacíos o tener un valor de 0")
    public Long numCta;

    @JsonProperty("tipTitCta")
    @ApiModelProperty(value = "Descripción: Tipo de titularidad de la cuenta a la cual se va a realizar el pago, solo aplica cuanto el tipo de proceso es 1 o 2, " +
           "si el proceso es diferente a 1 y 2, estos campos pueden ser vacíos o tener un valor de 0", name = "tipTitCta", dataType = "Integer",
            example = "2", notes = "Tipo de titularidad de la cuenta a la cual se va a realizar el pago, solo aplica cuanto el tipo de proceso es 1 o 2, " +
                       "si el proceso es diferente a 1 y 2, estos campos pueden ser vacíos o tener un valor de 0")
    public Integer tipTitCta;
}
