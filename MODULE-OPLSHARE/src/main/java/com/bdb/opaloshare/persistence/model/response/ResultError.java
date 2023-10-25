package com.bdb.opaloshare.persistence.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Copyright (c) 2019 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file war write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResultError extends RequestLogic {
    private String timestamp;
    private String message;
    private List<String> errors;

    /**
     * Show result with request URL and http status
     *
     * @param request Http Servlet request
     * @param status  Http status
     */
    public ResultError(HttpServletRequest request, HttpStatus status) {
        super(request, status);
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * Show result with request URL and http status
     *
     * @param request Http Servlet request
     * @param status  Http status
     * @param message Service result
     */
    public ResultError(HttpServletRequest request, HttpStatus status, String message) {
        super(request, status);
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.message = message;
    }

    /**
     * Show result with request URL and http status
     *
     * @param request Http Servlet request
     * @param status  Http status
     * @param message Service result
     * @param errors  List of errors.
     */
    public ResultError(HttpServletRequest request, HttpStatus status, String message, List<String> errors) {
        super(request, status);
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.errors = errors;
        this.message = message;
    }

    /**
     * Show result with request URL and http status
     *
     * @param request Http Servlet request
     * @param status  Http status
     * @param error  Service result
     */
    public ResultError(HttpServletRequest request, HttpStatus status, String message, String error) {
        super(request, status);
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.errors = Collections.singletonList(error);
        this.message = message;
    }
}
