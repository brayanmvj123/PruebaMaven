/*
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
package com.bdb.oplbacthsemanal.controller.service.interfaces;

import java.io.ByteArrayOutputStream;

/**
 * Interface encargada de leer y realizar el cargue del archivo DCV con el resultado de
 * Derechos Patrimoniales
 *
 * @author: Esteban Talero
 * @version: 24/11/2020
 * @since: 24/11/2020
 */
public interface EmisorDcvDerechosPatrimonialesService {

    //Leer archivo Semanal del FTP
    boolean leerArchivoDcvFtp(String claveArchivo);

    //Carga los datos del archivo Semanal para manipularlo
    ByteArrayOutputStream cargarDatosArchivoDcv();
}
