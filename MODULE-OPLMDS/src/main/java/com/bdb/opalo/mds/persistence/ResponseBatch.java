package com.bdb.opalo.mds.persistence;

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

    @ApiModelProperty(name = "date", dataType = "String", required = true, notes = "Fecha del proceso")
    private String date;
    @ApiModelProperty(name = "status", dataType = "String", required = true, notes = "Resultado del proceso")
    private String status;
    @ApiModelProperty(name = "requestUrl", dataType = "String", required = true, notes = "Request solicitado")
    private String requestUrl;
    @ApiModelProperty(name = "jobId", dataType = "String", required = true, notes = "Identificador del Job")
    private String jobId;
    @ApiModelProperty(name = "resultJob", dataType = "String", required = true, notes = "Descripción el resultado")
    private String resultJob;
    @ApiModelProperty(name = "possibleMistake", dataType = "String", required = true, notes = "Posible errores presentado en el proceso")
    private String possibleMistake;

}
