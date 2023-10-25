package com.bdb.opaloshare.persistence.model.response;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class RequestLogic {

    protected ResponseStatus status;

    @ApiModelProperty(value = "Descripción: Url Request.", name = "requestUrl", dataType = "String",
            example = "localhost:8080/CDTSDesmaterializado/v1/crucediario/pagodiaconciliacion", notes = "Url Request.")
    @Schema( description = "Descripción: Url Request", name = "requestUrl", type = "String",
            example = "localhost:8080/CDTSDesmaterializado/v1/crucediario/pagodiaconciliacion"  )
    protected String requestUrl;

    @ApiModelProperty(value = "Descripción: Parámetros ingresados en el Request.", name = "parameters", dataType = "Map<String, String>",
            example = " {\n" +
                    "    \"page\": \"1\",\n" +
                    "    \"size\": \"10\",\n" +
                    "  }", notes = "Parámetros ingresados en el Request.")
    @Schema( description = "Descripción: Parámetros ingresados en el Request.", name = "parameters", type = "String",
            example = "{ \'page\':\'1\'}")
    protected Map<String, String> parameters;

    /**
     * Show result with request URL and http status.
     *
     * @param request Http Servlet request.
     * @param status  Http status.
     */
    RequestLogic(HttpServletRequest request, HttpStatus status) {
        this.status = new ResponseStatus(status.value(), status.name());

        setParams(request);
        setRequestPath(request);
    }

    /**
     * Set request parameters for response.
     */
    private void setParams(HttpServletRequest request) {
        this.parameters = new HashMap<>();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet())
            this.parameters.put(entry.getKey(), entry.getValue()[0]);
    }

    /**
     * Set request full path.
     */
    private void setRequestPath(HttpServletRequest request) {
        this.requestUrl = String.format("%1$s%2$s",
                request.getRequestURL(),
                request.getQueryString() != null ? "?" + request.getQueryString() : "");
    }
}
