package com.bdb.opalo.reporte.exceptions;

public class NetworkFailureException extends RuntimeException {

    public NetworkFailureException(String message) {
        super(message);
    }

    public NetworkFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
