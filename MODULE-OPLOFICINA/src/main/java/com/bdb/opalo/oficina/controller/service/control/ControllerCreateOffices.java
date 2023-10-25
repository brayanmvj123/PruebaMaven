package com.bdb.opalo.oficina.controller.service.control;

import com.bdb.opalo.oficina.controller.service.interfaces.CreateOfficesService;
import com.bdb.opalo.oficina.persistence.response.ResponseBatch;
import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@CommonsLog
@Api(tags = "Creación de oficinas.")
public class ControllerCreateOffices {

    @Autowired
    CreateOfficesService createOfficesService;

    @ApiOperation(value = "Este servicio se encarga de crear los nuevos centros de costos.",
            produces = "application/json",
            response = ResponseBatch.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Creación exitosa."),
            @ApiResponse(code = 404, message = "Servicio caido. Validar: <br> " +
                    "<ul><li>El endpoint que se esta consumiendo.</li>" +
                    "<li>Si el error persiste, por favor reportar.</li></ul>"),
            @ApiResponse(code = 500, message = "Error al generar la carga o creación.")
    })
    @GetMapping("oficina/creacion")
    public ResponseEntity<ResponseBatch> creacionOficina(HttpServletRequest urlRequest) throws JobInstanceAlreadyCompleteException,
            JobExecutionAlreadyRunningException, JobParametersInvalidException, ErrorFtps, JobRestartException, IOException {
        log.info("SE INICIA EL PROCESO PARA LA CREACIÓN DE LAS OFICINAS NUEVAS, INICIO: "+ LocalDateTime.now());
        return createOfficesService.createOffice(urlRequest);
    }
}
