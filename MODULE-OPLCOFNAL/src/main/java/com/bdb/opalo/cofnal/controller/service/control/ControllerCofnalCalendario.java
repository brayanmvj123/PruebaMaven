package com.bdb.opalo.cofnal.controller.service.control;

import com.bdb.opalo.cofnal.controller.service.interfaces.CofnalCalendarioService;
import com.bdb.opalo.cofnal.persistance.JSONSchema.response.calendario.JSONResponseCalendarioCofnal;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("OPALOcofnal/v1")
@CommonsLog
public class ControllerCofnalCalendario {

    final CofnalCalendarioService cofnalCalendarioService;

    public ControllerCofnalCalendario(CofnalCalendarioService cofnalCalendarioService){
        this.cofnalCalendarioService = cofnalCalendarioService;
    }

    @ApiOperation(value = "Este servicio se encarga de consultar el calendario en cofgnal, la tabla contiene la info " +
            "actualizada de los 3 meses siguientes al mes actual,\n se actualiza los últimos 3 dias de cada mes, para " +
            "los 3 meses siguientes.",
            produces = "application/json",
            response = JSONResponseCalendarioCofnal.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta exitosa."),
            @ApiResponse(code = 404, message = "Servicio caido. Validar: <br> " +
                    "<ul><li>El endpoint que se esta consumiendo.</li>" +
                    "<li>Si el error persiste, por favor reportar.</li></ul>"),
            @ApiResponse(code = 500, message = "Error al consultar, por favor reportar.")
    })
    @GetMapping("calendario")
    public ResponseEntity<JSONResponseCalendarioCofnal> consultaCalendario(HttpServletRequest urlRequest) {
        log.info("Se inicia el consumo de la vwCalendar.");
        return cofnalCalendarioService.consultaAllCalendario(urlRequest);
    }
}
