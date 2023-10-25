package com.bdb.opalo.oficina.controller.service.control;

import com.bdb.opalo.oficina.controller.service.interfaces.ClosingOfficesService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@CommonsLog
@Api(tags = "Cierre de oficinas y actualización de IDs de oficina en los CDTS y Transaccion.")
public class ControllerClosingOffices {

    final ClosingOfficesService closingOfficesService;

    public ControllerClosingOffices(ClosingOfficesService closingOfficesService) {
        this.closingOfficesService = closingOfficesService;
    }

    @ApiOperation(value = "Este servicio se encarga de cargar y actualizar los IDs de las oficinas que cierran y estan " +
            "presentes en los CDTs Digitales y en las Transacciones de Pago.",
            produces = "application/json",
            response = ResponseBatch.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Carga, actualización exitosa."),
            @ApiResponse(code = 404, message = "Servicio caido. Validar: <br> " +
                    "<ul><li>El endpoint que se esta consumiendo.</li>" +
                    "<li>Si el error persiste, por favor reportar.</li></ul>"),
            @ApiResponse(code = 500, message = "Error al generar la carga o actualización.")
    })
    @GetMapping("oficina/cierreOficinas")
    public ResponseEntity<ResponseBatch> cierreOficinas(HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, IOException, ErrorFtps {
        log.info("SE INICIA LA EJECUCIÓN DE LA CARGA, DESPUES LA ACTUALZIACIÓN DE LOS IDS DE LA OFICINAS QUE CIERRAN");
        return closingOfficesService.officesClosed(urlRequest);
    }


}
