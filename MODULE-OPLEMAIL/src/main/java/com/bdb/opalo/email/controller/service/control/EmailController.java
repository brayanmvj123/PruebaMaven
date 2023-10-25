/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Esteban Talero <etalero@bancodebogota.com.co>.
 */
package com.bdb.opalo.email.controller.service.control;

import com.bdb.opalo.email.controller.service.dto.PerfilEmailDto;
import com.bdb.opalo.email.controller.service.dto.Response;
import com.bdb.opalo.email.controller.service.exception.ExceptionHandling;
import com.bdb.opalo.email.controller.service.interfaces.EmailService;
import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Controlador del servicio de Enviar Correo Electronico
 *
 * @author: Esteban Talero
 * @version: 26/07/2020
 * @since: 26/07/2020
 */
@RestController
@RequestMapping("Email/v1")
public class EmailController {
    @Autowired
    EmailService emailService;

    /**
     * @param emailBody idPerfil de correo a Enviar
     * @return ResponseEntity
     */
    @PostMapping(path = "/send", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> enviarCorreo(@Valid @RequestBody PerfilEmailDto emailBody) throws IOException, ErrorFtps, ExceptionHandling {
        System.out.println("SERVICIO ENVIO DE CORREO");
        System.out.println("IDPERFIL a consultar" + emailBody.getIdPerfil());
        boolean result = emailService.sendEmail(emailBody.getIdPerfil(), emailBody.getEmailSubjectType(), emailBody.getAttached(),
                emailBody.getEmailContentType());
        HttpStatus httpCode;
        String message;
        if (result) {
            httpCode = HttpStatus.CREATED;
            message = "Mail successfully sent";
        } else {
            httpCode = HttpStatus.NO_CONTENT;
            message = "Succes";
        }
        Response resultEntity = new Response(LocalDateTime.now(), " ", httpCode, message);
        return new ResponseEntity<>(resultEntity, httpCode);
    }
}
