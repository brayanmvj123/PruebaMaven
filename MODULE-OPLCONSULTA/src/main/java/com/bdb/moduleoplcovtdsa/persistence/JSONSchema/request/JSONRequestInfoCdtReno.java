package com.bdb.moduleoplcovtdsa.persistence.JSONSchema.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class JSONRequestInfoCdtReno implements Serializable {

    @NotNull
    @ApiModelProperty(value = "80000777000086", name = "numCdtAct", dataType = "Long", required = true,
            example = "80000777000086", notes = "Número actual del CDT Digital.")
    private Long numCdtAct;

}
