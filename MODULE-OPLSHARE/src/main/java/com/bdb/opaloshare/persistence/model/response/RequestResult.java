package com.bdb.opaloshare.persistence.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class RequestResult<T> extends RequestLogic {

    private T result;

    /**
     * Show result with request URL and http status
     *
     * @param request Http Servlet request
     * @param status  Http status
     */
    public RequestResult(HttpServletRequest request, HttpStatus status) {
        super(request, status);
    }

    /**
     * Show result with request URL and http status
     *
     * @param request Http Servlet request
     * @param status  Http status
     * @param result  Service result
     */
    public RequestResult(HttpServletRequest request, HttpStatus status, T result) {
        super(request, status);
        this.result = result;
    }
}
