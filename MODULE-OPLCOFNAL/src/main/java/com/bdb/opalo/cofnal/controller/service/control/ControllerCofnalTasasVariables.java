package com.bdb.opalo.cofnal.controller.service.control;

import com.bdb.opalo.cofnal.controller.service.interfaces.CofnalTasaVariableService;
import com.bdb.opalo.cofnal.persistance.JSONSchema.response.tasaVariable.JSONResponseTasaVariableCofnal;
import com.bdb.opalo.cofnal.persistance.JSONSchema.response.tasaVariable.JSONResponseTasaVariableCofnalMensual;
import com.bdb.opaloshare.persistence.model.jsonschema.obtenerTasa.JSONRequestTasaVariableCofnal;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("OPALOcofnal/v1")
@Api(value = "Servicio Modulo opalo cofnal")
public class ControllerCofnalTasasVariables {

    final CofnalTasaVariableService cofnalTasaVariable;

    public ControllerCofnalTasasVariables(CofnalTasaVariableService cofnalTasaVariable) {
        this.cofnalTasaVariable = cofnalTasaVariable;
    }

    @ApiOperation(value = "Este servicio se encarga de consultar las tasas variables en cofgnal.",
            produces = "application/json",
            response = JSONResponseTasaVariableCofnal.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta exitosa."),
            @ApiResponse(code = 404, message = "Servicio caido. Validar: <br> " +
                    "<ul><li>El endpoint que se esta consumiendo.</li>" +
                    "<li>Si el error persiste, por favor reportar.</li></ul>"),
            @ApiResponse(code = 500, message = "Error al consultar, por favor reportar.")
    })
    @GetMapping("tasasVariables")
    public ResponseEntity<JSONResponseTasaVariableCofnal> consultaTasasVariables(HttpServletRequest urlRequest) {
        return cofnalTasaVariable.consultaAllTasaVariable(urlRequest);
    }

    @ApiOperation(value = "Este servicio se encarga de consultar las tasas variables en cofnal del último mes.",
            produces = "application/json",
            response = JSONResponseTasaVariableCofnal.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta exitosa."),
            @ApiResponse(code = 404, message = "Servicio caido. Validar: <br> " +
                    "<ul><li>El endpoint que se esta consumiendo.</li>" +
                    "<li>Si el error persiste, por favor reportar.</li></ul>"),
            @ApiResponse(code = 500, message = "Error al consultar, por favor reportar.")
    })
    @GetMapping("tasasVariables/mes")
    public ResponseEntity<JSONResponseTasaVariableCofnalMensual> consultaTasasVariablesMes(HttpServletRequest urlRequest) {
        return cofnalTasaVariable.consultaTasaVariableMes(urlRequest);
    }

    @ApiOperation(value = "Este servicio se encarga de consultar las tasas variables en cofnal filtrados por fecha.",
            produces = "application/json",
            response = JSONResponseTasaVariableCofnal.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta exitosa."),
            @ApiResponse(code = 400, message = "Request erroneo."),
            @ApiResponse(code = 404, message = "Servicio caido."),
            @ApiResponse(code = 500, message = "Error al consultar, por favor reportar.")
    }
    )
    @ApiParam(name = "JSONRequestTasaVariableCofnal", value = "Parametros utilizados para la colsulta.", required = true)
    @PostMapping(value = "tasasVariables/f/obtenertasa", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<JSONResponseTasaVariableCofnal> tasaVariableTipoTasaFecha(@RequestBody JSONRequestTasaVariableCofnal request, HttpServletRequest url) {
        JSONResponseTasaVariableCofnal result = cofnalTasaVariable.consultaFechaTasaVariable(request, url);
        return ResponseEntity
                .status(result.getStatus().getCode())
                .body(result);
    }
}
