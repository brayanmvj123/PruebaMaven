package com.bdb.opaloshare.controller.service.errors;

public class ErrorFtps extends Exception {

	/**
	 * ESTA CLASE PERMITE NOTIFICAR LOS ERRORES EN CONSOLA DEL SITIO FTPS.
	 */
	private static final long serialVersionUID = 1L;
	
	private ErrorMessage errorMessage;

    public ErrorFtps(ErrorMessage errorMessage) {
        super(errorMessage.getErrormessage());
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
    
}
