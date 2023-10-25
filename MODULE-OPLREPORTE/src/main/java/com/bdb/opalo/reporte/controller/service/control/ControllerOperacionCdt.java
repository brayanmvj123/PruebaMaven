package com.bdb.opalo.reporte.controller.service.control;

import com.bdb.opalo.reporte.controller.service.interfaces.OperacionCdtService;
import com.bdb.opalo.reporte.exceptions.NoDataException;
import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

@Tag(name = "Vistas")
@RequestMapping("archivos")
@RestController
@Slf4j
@AllArgsConstructor
public class ControllerOperacionCdt {
    private final OperacionCdtService operacionCdtService;

    @GetMapping("/consultaDiariaAperturasyRenovacionesCdt")
    @Operation(
            summary = "Generar archivo de consulta diaria de aperturas y renovaciones de CDT",
            description = "Esta operación permite generar un archivo de consulta diaria de aperturas y renovaciones que se encuentran en la Vista 'OPERACIONES_CDTS_DIARIOS_VIEW'.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Archivo generado exitosamente"),
                    @ApiResponse(responseCode = "204", description = "No hay datos para generar el archivo"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            },
            deprecated = false
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Archivo generado exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay datos para generar el archivo"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<String> generarArchivoOperacionCdt() {
        try {
            return operacionCdtService.generarArchivoOperacionCdt();
        } catch (IOException | ErrorFtps e) {
            // En caso de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el ftps del servidor: " + e.getMessage());
        } catch (NoDataException e){
            // Captura la excepción y devuelve una respuesta con código 204
            log.info("No hay datos para generar el archivo");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Error: " + e.getMessage());
        } catch (HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode()).body("Error del cliente: " + e.getMessage());
        }
    }

}
