package com.bdb.opaloshare.controller.service.interfaces;

import java.util.Optional;
import java.text.ParseException;

/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBF was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public interface MetodosGeneralesService {
    String convertirRespuesta(String dato);

    String convertirRespuestaTipoII(String dato);

    String eliminarCerosIzquierda(String dato);

    <T> String entityToJSON(Optional<T> entity);  
	
	String formatoFecha(String fecha) throws ParseException;

	String nombreArchivo(String clave);
}
