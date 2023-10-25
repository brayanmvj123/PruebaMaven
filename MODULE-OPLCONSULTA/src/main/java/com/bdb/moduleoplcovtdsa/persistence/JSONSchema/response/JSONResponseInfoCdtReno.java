package com.bdb.moduleoplcovtdsa.persistence.JSONSchema.response;

import com.bdb.moduleoplcovtdsa.persistence.JSONSchema.request.JSONRequestInfoCdtReno;
import com.bdb.moduleoplcovtdsa.persistence.model.infocdtreno.InfoCdtReno;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class JSONResponseInfoCdtReno implements Serializable {

    @ApiModelProperty(value = "/CDTSDesmaterializado/v1/infoCdtReno", name = "url", dataType = "String", required = true,
            example = "/CDTSDesmaterializado/v1/infoCdtReno")
    private String url;

    @ApiModelProperty(value = "OK", name = "status", dataType = "HttpStatus", required = true, example = "OK")
    private HttpStatus status;

    private JSONRequestInfoCdtReno request;

    @ApiModelProperty(value = "Se ha encontrado información sobre el CDT Digital Desmaterializado consultado.",
            name = "message", dataType = "String", required = true, example = "Se ha encontrado información sobre el " +
            "CDT Digital Desmaterializado consultado.")
    private String message;

    private InfoCdtReno result;

}
