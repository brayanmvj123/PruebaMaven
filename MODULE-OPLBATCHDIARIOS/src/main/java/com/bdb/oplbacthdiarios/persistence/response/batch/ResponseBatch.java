package com.bdb.oplbacthdiarios.persistence.response.batch;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBatch implements Serializable {

    @ApiModelProperty(notes = "Fecha de la ejecución del servicio.", example = "2020/12/22", required = true)
    private String date;
    @ApiModelProperty(notes = "Estado del resultado de la operación.", example = "200", required = true)
    private String status;
    @ApiModelProperty(notes = "Url del consumo del servicio.", example = "https://opalo_des.banbta.net:8002/OPLBATCHDIARIO/", required = true)
    private String requestUrl;
    @ApiModelProperty(notes = "Identificador del job.", example = "15000", required = true)
    private String jobId;
    @ApiModelProperty(notes = "Resultado del Job.", example = "COMPLETED", allowableValues = "COMPLETED, FAIL", required = true)
    private String resultJob;
    @ApiModelProperty(notes = "Posibles errores encontrados en la ejecución.", example = "N/A", required = true)
    private String possibleMistake;

}
