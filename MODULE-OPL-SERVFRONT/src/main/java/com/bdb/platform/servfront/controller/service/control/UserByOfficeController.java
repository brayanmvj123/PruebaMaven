package com.bdb.platform.servfront.controller.service.control;

import com.bdb.opaloshare.persistence.entity.HisUsuarioxOficinaEntity;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opaloshare.persistence.model.userbyoffice.RequestUserByOffice;
import com.bdb.opaloshare.persistence.model.userbyoffice.ResponseUserByOffice;
import com.bdb.platform.servfront.controller.service.interfaces.UserByOfficeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("Information")
@CrossOrigin(origins = "*", maxAge = 0, allowedHeaders = "*", methods = {RequestMethod.POST})
@Tag(name = "Información de usuarios por Oficinas", description = "Servicio que permite realizar varias operaciones respecto " +
        "a los usuarios por oficinas")
public class UserByOfficeController {

    @Autowired
    @Qualifier(value = "UserByOfficeServiceImpl")
    private UserByOfficeService userByOfficeService;


    @GetMapping(value = "UserByOffice")
    @Operation(summary = "Retorna información de todos los usuarios vinculados a las oficinas del banco, permite filtrar(opcional) por oficina y/o estado",
            description = "Retorna una lista con todos los usuarios asociados a las oficinas del banco, permite filtrar(opcional) " +
                    "por oficina y/o estado de usuario. El tipo de estado de usuario puede ser: 6=Usuario Activo, y 7=Usuario Inactivo")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Retorna la información de usuarios asociados según el fitro ingresado"),
            @ApiResponse(responseCode = "404", description = "No existen registros asociados al id de oficina ingresado",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "400", description = "1- El registro ingresado en el request no existe en la tabla OPL_PAR_OFICINA_DOWN_TBL, " +
                    "2-Los datos ingresados no son correctos, revisar respuesta.",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<List<ResponseUserByOffice>>> findByOffice(
            @Parameter( description = "Id de la oficina") @RequestParam(value = "office", required = false) Long office,
            @Parameter( description = "Id del tipo de estado") @RequestParam(value = "state", required = false) Integer state,
            HttpServletRequest http) {
        List<ResponseUserByOffice> result = userByOfficeService.findAllParameters(office, state);
        return ResponseEntity.status((result == null || result.size() < 1)? HttpStatus.NOT_FOUND : HttpStatus.OK).body(
                new RequestResult<>(http, (result == null || result.size() < 1) ? HttpStatus.NOT_FOUND : HttpStatus.OK, result));
    }


    @GetMapping(value = "UserByOffice/page/{page}/size/{size}")
    @Operation(summary = "Retorna información del personal de oficinas (respuesta paginada)",
            description = "Consulta todos los registros de la tabla OPL_HIS_USUARIOXOFICINA_LARGE_TBL (respuesta paginada)")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Retorna la información del personal de oficinas."),
            @ApiResponse(responseCode = "404", description = "La tabla OPL_HIS_USUARIOXOFICINA_LARGE_TBL no tiene registros",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<Page<ResponseUserByOffice>>> findAllPagination(@Parameter( description = "Número de pagina.", required = true) @PathVariable Integer page,
                                                                                       @Parameter( description = "Tamaño de la página, número de registros por página.", required = true) @PathVariable Integer size,
                                                                                       HttpServletRequest http) {
        Page<ResponseUserByOffice> result = userByOfficeService.findAll(PageRequest.of(page, size));
        return ResponseEntity.status((result == null || result.getSize() < 1) ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(
                new RequestResult<>(http, (result == null || result.getSize() < 1) ? HttpStatus.NOT_FOUND : HttpStatus.OK, result));
    }


    @GetMapping(value = "UserByOffice/{id}")
    @Operation(summary = "Retorna información de un usuario de oficina por su id", description = "Retorna información de un usuario de oficina por su id")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Retorna la información del usuario con el id ingresado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado en la base de datos",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<ResponseUserByOffice>> findById(@Parameter( description = "Id del usuario", required = true) @PathVariable Long id,
                                                                        HttpServletRequest http) {
        ResponseUserByOffice result = userByOfficeService.findById(id);
        return ResponseEntity.status(result == null? HttpStatus.NOT_FOUND : HttpStatus.OK).body(
                new RequestResult<>(http, result == null ? HttpStatus.NOT_FOUND : HttpStatus.OK, result));
    }


    @PostMapping(value = "UserByOffice" )
    @Operation(summary = "Permite crear un usuario de oficina nuevo", description = "Permite crear un registro nuevo en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "201", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<?>> save(@Valid @RequestBody RequestUserByOffice requestUserByOffice, HttpServletRequest http) {
        Boolean checkOffice = userByOfficeService.checkOffice(requestUserByOffice.getNroOficina());
        if(!checkOffice)  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http,HttpStatus.BAD_REQUEST,
                "La oficina con id '"+requestUserByOffice.getNroOficina()+"' no está registrada en base de datos, revisar"));
        HisUsuarioxOficinaEntity result = userByOfficeService.save(requestUserByOffice);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RequestResult<>(http,HttpStatus.CREATED, result));
    }


    @PutMapping(value = "UserByOffice/update/{id}")
    @Operation(summary = "Permite actualizar un usuario de oficina existente", description = "Permite actualizar un registro existente por medio de su id")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Usuario actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado en la base de datos",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<?>> update(@Valid @RequestBody RequestUserByOffice requestUserByOffice,
                                @Parameter( description = "Id del usuario", required = true) @PathVariable Long id,
                                HttpServletRequest http) {
        Boolean checkOffice = userByOfficeService.checkOffice(requestUserByOffice.getNroOficina());
        if(!checkOffice)  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http,HttpStatus.BAD_REQUEST,
                "La oficina con id '"+requestUserByOffice.getNroOficina()+"' no está registrada en base de datos, revisar"));
        HisUsuarioxOficinaEntity result = userByOfficeService.update(requestUserByOffice, id);

        return ResponseEntity.status(result == null? HttpStatus.NOT_FOUND : HttpStatus.OK).body(
                new RequestResult<>(http, result == null ? HttpStatus.NOT_FOUND : HttpStatus.OK,
                        result == null ? "Usuario con id '"+id+"' no encontrado." : result));
    }


    @DeleteMapping(value = "UserByOffice/delete/{id}")
    @Operation(summary = "Permite eliminar un usuario de oficina", description = "Permite eliminar un usuario de oficina por medio de su id")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Usuario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado en la base de datos",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<RequestResult<Boolean>> delete(@Parameter( description = "Id del usuario", required = true) @PathVariable Long id,
                                                                      HttpServletRequest http) {
        Boolean result = userByOfficeService.delete(id);
        return ResponseEntity.status(!result? HttpStatus.NOT_FOUND : HttpStatus.OK).body(
                new RequestResult<>(http, !result ? HttpStatus.NOT_FOUND : HttpStatus.OK, result));
    }
}
