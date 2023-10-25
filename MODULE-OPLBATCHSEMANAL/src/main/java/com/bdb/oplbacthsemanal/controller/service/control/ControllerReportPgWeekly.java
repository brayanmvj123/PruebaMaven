package com.bdb.oplbacthsemanal.controller.service.control;

import com.bdb.oplbacthsemanal.controller.service.interfaces.ReportPgWeeklyService;
import com.bdb.oplbacthsemanal.persistence.model.response.ResponseBatch;
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

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@CommonsLog
@Api(tags = "Reporte fecha vencimiento oficinas")
public class ControllerReportPgWeekly {

    @Autowired
    ReportPgWeeklyService reportPgWeeklyService;

    @ApiOperation(value = "Este servicio se encarga de cruzar data para mostrar en el reporte de fecha vencimientos por oficinas.",
            produces = "application/json",
            response = ResponseBatch.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cruce exitoso."),
            @ApiResponse(code = 404, message = "Servicio caido. Validar: <br> " +
                    "<ul><li>El endpoint que se esta consumiendo.</li>" +
                    "<li>Si el error persiste, por favor reportar.</li></ul>"),
            @ApiResponse(code = 500, message = "Error al generar la data para el reporte.")
    }
    )
    @GetMapping("cruceSemanal/reportFechaVenOfic")
    public ResponseEntity<ResponseBatch> fechaVenOficina(HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        log.info("SE INICIA EL CRUCE DE INFORMACIÓN PARA LLENAR LA TABLA PGSEMANAL");
        return reportPgWeeklyService.consumeJobsPgWeekly(urlRequest);

    }


    @ApiOperation(value = "Este servicio se encarga de la creación de archivos de excel con CDTS semanales para enviar a oficinas.",
            notes = "Crea un excel por cada oficina a partir de los todos los datos de la liquidación semanal actual. " +
                    "Crea archivos únicamente con la información de CDTs Conciliados No digitales (tipPosicion !=4) y " +
                    "pertenecientes a oficinas distintas a tesorería (tipPosicion !=2).",
            produces = "application/json",
            response = ResponseBatch.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Simulación exitosa."),
            @ApiResponse(code = 404, message = "Servicio caido. Validar: <br> " +
                    "<ul><li>El endpoint que se esta consumiendo.</li>" +
                    "<li>Si el error persiste, por favor reportar.</li></ul>"),
            @ApiResponse(code = 500, message = "Error al generar la data para el reporte.")
    })
    @GetMapping("simuladorPg/createFiles")
    public ResponseEntity<ResponseBatch> createFilesOffice(HttpServletRequest urlRequest) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("SE INICIA EL PROCESO DE SIMULACION DE LOS CDTS PROVENIENTES DEL CRUCE Y ALMACENADOS EN LA TABLA SAL_PG.");
        return reportPgWeeklyService.consumeJobCreateFiles(urlRequest);
    }
}
