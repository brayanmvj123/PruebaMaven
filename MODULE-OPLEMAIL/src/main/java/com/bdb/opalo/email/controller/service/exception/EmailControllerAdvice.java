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
package com.bdb.opalo.email.controller.service.exception;

import com.bdb.opalo.email.controller.service.dto.Response;
import com.bdb.opalo.email.controller.service.exception.ExceptionHandling;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * RestControllerAdvice
 *
 * @author: Andres Marles
 * @version: 18/01/20222
 * @since: 18/01/20222
 */
@RestControllerAdvice
@Slf4j
public class EmailControllerAdvice {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity handleGenericNotFoundException(NotFoundException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {IOException.class})
    @ResponseBody
    public ResponseEntity<RequestResult<Map<String, Object>>> handlerException(IOException e, HttpServletRequest http) {
        Map<String, Object> response = new HashMap<>();
        e.printStackTrace();
        System.out.println(e.getLocalizedMessage());
        System.out.println(e.getCause());
        response.put("error", e.getLocalizedMessage());
        response.put("message", "Archivo no encontrado en la carpeta del dia de Opalo en el sitio FTPS, revisar");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http, HttpStatus.BAD_REQUEST, response));
    }

//    @ExceptionHandler(value = ExceptionHandling.class)
//    public ResponseEntity<Response> handleSiteFtps(ExceptionHandling e) {
//        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//        if (e.getHttpStatus() == 409) {
//            status = HttpStatus.CONFLICT;
//        }
//        if (e.getHttpStatus() == 503) {
//            status = HttpStatus.SERVICE_UNAVAILABLE;
//        }
//        if (e.getHttpStatus() == 400) {
//            status = HttpStatus.BAD_REQUEST;
//        }
//        Response resultEntity = new Response(LocalDateTime.now(), " ", status, e.getMessage());
//        return new ResponseEntity<>(resultEntity, status);
//    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseBody
    public ResponseEntity<RequestResult<Map<String, Object>>> handlerException(MethodArgumentNotValidException e, HttpServletRequest http) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> listado = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getField().concat(" ").concat(fieldError.getDefaultMessage())));
        response.put("errors", listado);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http, HttpStatus.BAD_REQUEST, response));
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseBody
    public ResponseEntity<RequestResult<Map<String, Object>>> handlerException(HttpMessageNotReadableException e, HttpServletRequest http) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", e.getMostSpecificCause().getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http, HttpStatus.BAD_REQUEST, response));
    }

    @ExceptionHandler(value = {HttpClientErrorException.class})
    @ResponseBody
    public ResponseEntity<RequestResult<Map<String, Object>>> handlerException(HttpClientErrorException e, HttpServletRequest http) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", e.getMostSpecificCause().getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http, HttpStatus.BAD_REQUEST, response));
    }


    @ExceptionHandler(value = {MaxUploadSizeExceededException.class})
    @ResponseBody
    public ResponseEntity<RequestResult<Map<String, Object>>> handlerExceptionFile(MaxUploadSizeExceededException e, HttpServletRequest http) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", e.getMostSpecificCause().getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http, HttpStatus.BAD_REQUEST, response));
    }


    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ResponseEntity<RequestResult<Map<String, Object>>> handlerException(Exception e, HttpServletRequest http) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", e.getLocalizedMessage());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResult<>(http, HttpStatus.INTERNAL_SERVER_ERROR, response));
    }
}
