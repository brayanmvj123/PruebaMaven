package com.bdb.opaloshare.persistence.model.response;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Copyright (c) 2019 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file war write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatus {
    @ApiModelProperty(value = "Descripción: Código de respuesta.", name = "code", dataType = "int",
            example = "200", notes = "Código de respuesta.")
    @Schema( description = "Descripción: Código de respuesta.", name = "code", type = "int",
            example = "200"  )
    private int code;

    @ApiModelProperty(value = "Descripción: Url Request", name = "requestUrl", dataType = "String",
            example = "OK", notes = "Mensaje de respuesta.")
    @Schema( description = "Descripción: Url Request", name = "requestUrl", type = "String",
            example = "OK"  )
    private String message;
}
