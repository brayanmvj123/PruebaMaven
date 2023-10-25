/*
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Esteban Talero <etalero@bancodebogota.com.co>.
 */
package com.bdb.oplbacthdiarios.controller.service.control;

import com.bdb.oplbacthdiarios.controller.service.interfaces.CargueDcvDerechosPatrimonialesService;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseBatch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controlador del servicio encargado de realizar el cargue del archivo DCV dcob01s
 *
 * @author: Esteban Talero
 * @version: 19/11/2020
 * @since: 19/11/2020
 */
@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@Api(value = "Cargue archivo DCOB01 Emisor - Derechos patrimoniales Emisor.")
public class ControllerCargueDerechosPatrimoniales {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    final CargueDcvDerechosPatrimonialesService cargueDcvDerechosPatrimonialesService;

    public ControllerCargueDerechosPatrimoniales(CargueDcvDerechosPatrimonialesService cargueDcvDerPatriService) {
        this.cargueDcvDerechosPatrimonialesService = cargueDcvDerPatriService;
    }

    @GetMapping("carga/emisor/depatriemi")
    @ApiOperation(value = "Derechos patrimoniales", notes = "Carga del archivo emisor de Deceval con el resultado de " +
            "derechos Patrimoniales hasta guardar la data del mismo en la base de Datos")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error")
    })
    public ResponseEntity<ResponseBatch> derechosPatrimoniales(HttpServletRequest request) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, IOException {
        logger.info("inicio job carga emision derechos patrimoniales...");
        return cargueDcvDerechosPatrimonialesService.loadDerechosPatrimoniales(request);
    }
}
