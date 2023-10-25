package com.bdb.opalotasasfija.controller.service.control;


import com.bdb.opaloshare.persistence.entity.OplHisTasaVariableEntity;
import com.bdb.opaloshare.persistence.model.jsonschema.tasaVariable.JSONRequestTasaVariable;
import com.bdb.opaloshare.persistence.model.response.tasaVariable.JSONResponseTasaVariable;
import com.bdb.opaloshare.persistence.model.response.tasaVariable.JSONResponseTasaVariableLista;
import com.bdb.opalotasasfija.controller.service.interfaces.TasaVariableService;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.JSONResponse;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResponseStatus;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("CDTSDesmaterializado/v2")
@Api(tags = "Tasa Variable")
public class ControllerTasaVariable {

    @Autowired
    TasaVariableService tasaVariableService;

    @ApiOperation(value = "Este servicio se encarga de consultar la tasa variable deacuerdo al tipo de tasa y fecha (ttf).",
            produces = "application/json",
            response = JSONResponseTasaVariable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta exitosa"),
            @ApiResponse(code = 400, message = "Request erroneo"),
            @ApiResponse(code = 404, message = "Servicio caido"),
            @ApiResponse(code = 500, message = "Error al consultar, por favor reportar")
    }
    )
    @ApiParam(name = "JSONRequestTasaVariable", value = "Parametros utilizados para la colsulta.", required = true)
    @PostMapping(value = "tasaVariable/ttf/obtenertasa" , consumes = {"application/json"} , produces = {"application/json"})
    public ResponseEntity<JSONResponse<JSONResponseStatus, JSONRequestTasaVariable,
            OplHisTasaVariableEntity>> tasaVariableTipoTasaFecha(@Valid @RequestBody JSONRequestTasaVariable request, HttpServletRequest url){
        JSONResponse<JSONResponseStatus, JSONRequestTasaVariable,
                OplHisTasaVariableEntity> result = tasaVariableService.consultaTasaVariableTipoFecha(request, url);
        return ResponseEntity
                .status(result.getStatus().getCode()).body(result);
    }


    @ApiOperation(value = "Este servicio se encarga de consultar las tasa variables en una fecha especifica (f).",
            produces = "application/json",
            response = JSONResponseTasaVariableLista.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta exitosa"),
            @ApiResponse(code = 400, message = "Request erroneo"),
            @ApiResponse(code = 404, message = "Servicio caido"),
            @ApiResponse(code = 500, message = "Error al consultar, por favor reportar")
    }
    )
    @ApiParam(name = "JSONRequestTasaVariable", value = "Parametros utilizados para la colsulta.", required = true)
    @PostMapping(value = "tasaVariable/f/obtenertasa" , consumes = {"application/json"} , produces = {"application/json"})
    public ResponseEntity<JSONResponse<JSONResponseStatus, JSONRequestTasaVariable,
            List<OplHisTasaVariableEntity>>> tasaVariableFecha(@Valid @RequestBody JSONRequestTasaVariable request, HttpServletRequest url){
        JSONResponse<JSONResponseStatus, JSONRequestTasaVariable,
                List<OplHisTasaVariableEntity>> result = tasaVariableService.consultaTasaVariableFecha(request, url);
        return ResponseEntity
                .status(result.getStatus().getCode()).body(result);
    }


    @ApiOperation(value = "Este servicio se encarga de consultar los registros de una tasa variable especifica (tt).",
            produces = "application/json",
            response = JSONResponseTasaVariableLista.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta exitosa"),
            @ApiResponse(code = 400, message = "Request erroneo"),
            @ApiResponse(code = 404, message = "Servicio caido"),
            @ApiResponse(code = 500, message = "Error al consultar, por favor reportar")
    }
    )
    @ApiParam(name = "JSONRequestTasaVariable", value = "Parametros utilizados para la colsulta.", required = true)
    @PostMapping(value = "tasaVariable/tt/obtenertasa" , consumes = {"application/json"} , produces = {"application/json"})
    public ResponseEntity<JSONResponse<JSONResponseStatus, JSONRequestTasaVariable,
            List<OplHisTasaVariableEntity>>> tasaVariableTipoTasa(@RequestBody JSONRequestTasaVariable request, HttpServletRequest url){
        JSONResponse<JSONResponseStatus, JSONRequestTasaVariable,
                List<OplHisTasaVariableEntity>> result = tasaVariableService.consultaTasaVariableTipo(request, url);
        return ResponseEntity
                .status(result.getStatus().getCode()).body(result);
    }


    @ApiOperation(value = "Este servicio se encarga de consultar los registros de tasas variables.",
            produces = "application/json",
            response = JSONResponseTasaVariableLista.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta exitosa"),
            @ApiResponse(code = 400, message = "Request erroneo"),
            @ApiResponse(code = 404, message = "Servicio caido"),
            @ApiResponse(code = 500, message = "Error al consultar, por favor reportar")
    }
    )
    @GetMapping(value = "tasaVariable/obtenertasa" , produces = {"application/json"})
    public ResponseEntity<JSONResponse<JSONResponseStatus, JSONRequestTasaVariable,
            List<OplHisTasaVariableEntity>>> tasaVariable(HttpServletRequest url){
        JSONResponse<JSONResponseStatus, JSONRequestTasaVariable,
                List<OplHisTasaVariableEntity>> result = tasaVariableService.consultaTasaVariable(url);
        return ResponseEntity
                .status(result.getStatus().getCode()).body(result);
    }
}
