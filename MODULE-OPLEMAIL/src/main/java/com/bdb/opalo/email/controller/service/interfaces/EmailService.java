/**
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
package com.bdb.opalo.email.controller.service.interfaces;

import com.bdb.opalo.email.controller.service.exception.ExceptionHandling;
import com.bdb.opaloshare.controller.service.errors.ErrorFtps;

import java.io.IOException;

/**
 * Interfaz encargada de enviar correo Electronico
 *
 * @author: Esteban Talero
 * @version: 26/07/2020
 * @since: 26/07/2020
 */
public interface EmailService {
    boolean sendEmail(Integer idPerfil, String emailSubjectType, Boolean attached, String emailContentType) throws IOException, ErrorFtps, ExceptionHandling;
}
