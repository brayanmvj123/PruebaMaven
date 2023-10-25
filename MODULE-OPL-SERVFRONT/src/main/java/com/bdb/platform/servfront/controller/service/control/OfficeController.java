package com.bdb.platform.servfront.controller.service.control;

import com.bdb.opaloshare.persistence.entity.OficinaParWithRelationsDownEntity;
import com.bdb.opaloshare.persistence.model.office.ResponseOffice;
import com.bdb.opaloshare.persistence.model.office.ResponseOfficeDetail;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.platform.servfront.controller.service.interfaces.OfficeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("Information")
@CrossOrigin(origins = "*", maxAge = 0, allowedHeaders = "*", methods = {RequestMethod.POST})
@Tag(name = "Información de Oficinas", description = "Servicio que permite realizar todas las consultas referentes a oficinas")
public class OfficeController {

    @Autowired
    @Qualifier(value = "OfficeServiceImpl")
    private OfficeService officeService;


    @GetMapping(value = "office")
    @Operation(summary = "Retorna información de oficinas por su estado", description = "Retorna información de oficinas " +
            "por su estado. Estados 1= procesado, 2=enviado, 3=finalizado, 4=oficina activa, 5=oficina cerrada, 6=Usuario Activo, 7= Usuario Inactivo")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Retorna la información de la oficina con el estado ingresado"),
            @ApiResponse(responseCode = "404", description = "Oficina no encontrada en base de datos con el estado ingresado",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<List<ResponseOffice>>> findOfficeByState(
            @Parameter( description = "Id del tipo de estado") @RequestParam(value = "status", required = false) Integer status,
            HttpServletRequest http) {
        List<ResponseOffice> result = officeService.findAllOfficeByState(status);
        return ResponseEntity.status((result == null || result.size() < 1)? HttpStatus.NOT_FOUND : HttpStatus.OK).body(
                new RequestResult<>(http, (result == null || result.size() < 1) ? HttpStatus.NOT_FOUND : HttpStatus.OK, result));
    }

    @GetMapping(value = "office/{id}")
    @Operation(summary = "Retorna información detallada de oficinas por ID", description = "Retorna información detallada de de oficinas por ID")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Retorna la información detallada de de la oficina con el id ingresado"),
            @ApiResponse(responseCode = "404", description = "Oficina no encontrada en base de datos",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<ResponseOfficeDetail>> findOfficeById(@Parameter( description = "Id del usuario", required = true) @PathVariable Integer id,
                                                                              HttpServletRequest http) {
        ResponseOfficeDetail result = officeService.findOfficeById(id);
        return ResponseEntity.status(result == null? HttpStatus.NOT_FOUND : HttpStatus.OK).body(
                new RequestResult<>(http, result == null ? HttpStatus.NOT_FOUND : HttpStatus.OK, result));
    }


    @GetMapping(value = "office/daily/findOfficeWithoutEmail")
    @Operation(summary = "Retorna las oficinas (registradas en los CDTs pertenecientes a liquidación diaria actual) que no tienen correos asociados",
            description = "Retorna las oficinas (registradas en los CDTs pertenecientes a liquidación diaria actual) que no tienen correos asociados")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Responde una lista vacía, la cual indica que todas las oficinas " +
                    "(registradas en los CDTs pertenecientes a liquidación diaria actual) tienen correos asignados "),
            @ApiResponse(responseCode = "202", description = "Responde una lista con las oficinas (registradas en los CDTs " +
                    "pertenecientes a liquidación diaria actual) que no tiene correos asignados.",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<List<OficinaParWithRelationsDownEntity>>> findOfficeDaily(HttpServletRequest http) {
        List<OficinaParWithRelationsDownEntity> result = officeService.findOfficeWithoutEmailDaily();
        return ResponseEntity.status((result == null || result.size() < 1) ? HttpStatus.OK : HttpStatus.ACCEPTED).body(
                new RequestResult<>(http, (result == null || result.size() < 1) ? HttpStatus.OK : HttpStatus.ACCEPTED, result));
    }


    @GetMapping(value = "office/weekly/findOfficeWithoutEmail")
    @Operation(summary = "Retorna las oficinas (registradas en los CDTs pertenecientes a liquidación semanal actual) que no tienen correos asociados",
            description = "Retorna las oficinas (registradas en los CDTs pertenecientes a liquidación semanal actual) que no tienen correos asociados")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Responde una lista vacía, la cual indica que todas las oficinas " +
                    "(registradas en los CDTs pertenecientes a liquidación semanal actual) tienen correos asignados "),
            @ApiResponse(responseCode = "202", description = "Responde una lista con las oficinas (registradas en los CDTs " +
                    "pertenecientes a liquidación semanal actual) que no tiene correos asignados.",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<List<OficinaParWithRelationsDownEntity>>> findOfficeWeekly(HttpServletRequest http) {
        List<OficinaParWithRelationsDownEntity> result = officeService.findOfficeWithoutEmailWeekly();
        return ResponseEntity.status((result == null || result.size() < 1) ? HttpStatus.OK : HttpStatus.ACCEPTED).body(
                new RequestResult<>(http, (result == null || result.size() < 1) ? HttpStatus.OK : HttpStatus.ACCEPTED, result));
    }
}
