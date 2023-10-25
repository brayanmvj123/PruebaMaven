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
package com.bdb.opalogdoracle.controller.service.interfaces;

/**
 * Interface encargado de ejecutar traductor CADI
 *
 * @author: Esteban Talero
 * @version: 10/11/2020
 * @since: 09/11/2020
 */
public interface TramaTraductorCadiService {

    String tramasTraductorCadi(String url, String urlBatch) throws Exception;
}
