package com.bdb.opalo.reporte.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException ex) {
        // Manejar excepciones relacionadas con respuestas 4xx (errores del cliente)
        return ResponseEntity.status(ex.getStatusCode()).body("Error del cliente: " + ex.getMessage());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> handleHttpServerErrorException(HttpServerErrorException ex) {
        // Manejar excepciones relacionadas con respuestas 5xx (errores del servidor)
        return ResponseEntity.status(ex.getStatusCode()).body("Error del servidor: " + ex.getMessage());
    }

    @ExceptionHandler(NetworkFailureException.class)
    public ResponseEntity<String> handleNetworkFailureException(NetworkFailureException ex) {
        // Manejar excepciones de falla de red personalizadas
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falla de red: " + ex.getMessage());
    }
}
