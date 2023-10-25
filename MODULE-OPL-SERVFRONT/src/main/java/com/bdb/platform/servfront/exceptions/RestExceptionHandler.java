package com.bdb.platform.servfront.exceptions;

import com.bdb.opaloshare.persistence.model.response.RequestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseBody
    public ResponseEntity<RequestResult<Map<String, Object>>> handlerException(MethodArgumentNotValidException e, HttpServletRequest http) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> listado = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getField().concat(" ").concat(fieldError.getDefaultMessage())));
        response.put("errors", listado);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResult<>(http, HttpStatus.BAD_REQUEST, response));
    }


    @ExceptionHandler(value = {BindException.class})
    @ResponseBody
    public ResponseEntity<RequestResult<Map<String, Object>>> handlerException(BindException e, HttpServletRequest http) {
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

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ResponseEntity<RequestResult<Map<String, Object>>> handlerException(Exception e, HttpServletRequest http) {
        Map<String, Object> response = new HashMap<>();
        e.printStackTrace();
        response.put("error", e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResult<>(http, HttpStatus.INTERNAL_SERVER_ERROR, response));
    }
}
