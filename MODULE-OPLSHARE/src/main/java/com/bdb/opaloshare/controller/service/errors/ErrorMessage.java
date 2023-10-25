package com.bdb.opaloshare.controller.service.errors;

public class ErrorMessage {

	/*
	 * ESTA CLASE PERMITE NOTIFICAR LOS ERRORES EN CONSOLA DEL SITIO FTPS, ESTA CLASE ES LA IMPLEMENTADA EN FTPSServiceImplement
	 * EN DONDE SI SE QUIERE NOTIFICAR ALGUN ERROR SE DA UN CODIGO Y UN MENSAJE , ESTO PARA PERSONALIZAR LOS DIFERENTES ERRORES
	 * DEL SITIO FTPS.
	 */
	
	private int errorcode;
    private String errormessage;

    public ErrorMessage(int errorcode, String errormessage) {
		super();
		this.errorcode = errorcode;
		this.errormessage = errormessage;
	}

	public int getErrorcode() {
        return errorcode;
    }

    public String getErrormessage() {
        return errormessage;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "errorcode=" + errorcode +
                ", errormessage='" + errormessage + '\'' +
                '}';
    }
    
}
