package com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestConfirmacionCancelacion implements Serializable {

    @NotBlank
    @ApiModelProperty(value = "Descripción: Nombre del canal de ejecución que realiza la petición", name = "canal", dataType = "String", required = true,
            example = "OPALO", notes = "Nombre del canal de ejecución que realiza la petición")
    private String canal;

    @NotBlank
    @ApiModelProperty(value = "Descripción: Fecha de ejecución de la petición.", name = "fecha", dataType = "String", required = true,
            example = "2021-11-21 10:31:00", notes = "Fecha de ejecución de la petición")
    private String fecha;

    @Valid
    @JsonProperty("infoCliente")
    private InfoClientes infoClientes;

    @Valid
    private CDT cdt;

    private static final long serialVersionUID = 1L;

}
