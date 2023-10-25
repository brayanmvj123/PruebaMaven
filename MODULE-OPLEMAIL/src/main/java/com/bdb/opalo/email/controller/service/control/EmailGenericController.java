package com.bdb.opalo.email.controller.service.control;

import com.bdb.opalo.email.controller.service.dto.Response;
import com.bdb.opalo.email.controller.service.exception.ExceptionHandling;
import com.bdb.opalo.email.controller.service.interfaces.EmailGenericService;
import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.persistence.model.email.RequestEmail;
import com.bdb.opaloshare.persistence.model.response.RequestResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controlador del servicio de Enviar Correo Electronico
 *
 * @author: Andres Marles
 * @version: 12/01/2022
 * @since: 12/01/2022
 */
@RestController
@RequestMapping("Email/v2")
@Tag( name = "Envío de correos", description = "Servicio que permite enviar correos ")
public class EmailGenericController {

    @Autowired
    private EmailGenericService emailGeneric;


    /**
     * @param requestEmail idPerfil de correo a Enviar
     * @return ResponseEntity
     */
    @PostMapping(path = "/sendEmailWithFilesFTPS")
    @Operation(summary = "Método genérico, permite enviar un correo electrónico y adjuntar archivos almacenados en el FTPS.",
            description = "Permite enviar un correo electrónico con o sin archivos adjuntos (los archivos " +
                    "adjuntos deben estar almacenados en el FTPS en la carpeta \"/OPALO/'yyyyMMdd'(Fecha actual, " +
                    "expresada en este formato)/OUTPUT/folderinOutputFTPS(campo ingresado en el request )\"")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "201", description = "Archivo enviado exitosamente",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "400", description = "Error de validación, revisar los datos ingresados",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<Response> sendEmailWithParameters(@Valid @RequestBody RequestEmail requestEmail) throws IOException, ErrorFtps, ExceptionHandling, MessagingException {

        boolean result = emailGeneric.sendEmailGeneric(requestEmail);
        HttpStatus httpCode;
        String message;
        if (result) {
            httpCode = HttpStatus.CREATED;
            message = "Mail successfully sent";
        } else {
            httpCode = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "Fsiled";
        }
        Response resultEntity = new Response(LocalDateTime.now(), " ", httpCode, message);
        return new ResponseEntity<>(resultEntity, httpCode);
    }


    /**
//     * @param requestEmail idPerfil de correo a Enviar
     * @return ResponseEntity
     */
    @PostMapping(path = "/sendEmail", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation( summary = "Método genérico, permite enviar un correo electrónico", description  = "Método genérico, permite enviar " +
            "un correo electrónico con o sin archivos adjuntos. El campo 'message' puede ir vacío o recibir texto plano o HTML")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "201", description = "Correo enviado exitosamente",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "400", description = "Error de validación, revisar los datos ingresados",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el servidor",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestResult.class))})})
    public ResponseEntity<Response> sendEmailGeneric(@Valid
        @Schema( required = true, example = "opalo@bancodebogota.com.co") @RequestParam("From") String from ,
        @Schema( required = true, example = "[\"amarles@bancodebogota.com.co\", \"amarles@bancodebogota.com.co\"]")   @RequestParam("To") List<String> mailTo,
        @Schema( required = false, example = "[\"amarles@bancodebogota.com.co\", \"amarles@bancodebogota.com.co\"]")    @RequestParam("CC") List<String> mailCopyTo,
        @Schema( required = false, example = "[\"amarles@bancodebogota.com.co\", \"amarles@bancodebogota.com.co\"]")     @RequestParam("CO") List<String> carbonCopy,
        @Schema( required = true, example = "Correo de prueba, no responder")    @RequestParam("Subject")String emailSubjectType,
        @Schema( required = true, example = "<h4>Buenos dias</h4><br><div><p>Este es un mensaje de prueba, por favor no responder.</p>" )  @RequestParam("Message") String message,
        @RequestParam("file") List<MultipartFile> files) throws IOException, ErrorFtps, ExceptionHandling, MessagingException {


        RequestEmail requestEmail = new RequestEmail();
        requestEmail.setFrom(from);
        requestEmail.setTo(mailTo);
        requestEmail.setCc(mailCopyTo);
        requestEmail.setCo(carbonCopy);
        requestEmail.setSubject(emailSubjectType);
        requestEmail.setMessage(message);
        boolean result = emailGeneric.sendEmailGeneric(requestEmail, files);
        HttpStatus httpCode;
        String messages;
        if (result) {
            httpCode = HttpStatus.CREATED;
            messages = "Mail successfully sent";
        } else {
            httpCode = HttpStatus.INTERNAL_SERVER_ERROR;
            messages = "Fsiled";
        }
        Response resultEntity = new Response(LocalDateTime.now(), " ", httpCode, messages);
        return new ResponseEntity<>(resultEntity, httpCode);
    }
}
