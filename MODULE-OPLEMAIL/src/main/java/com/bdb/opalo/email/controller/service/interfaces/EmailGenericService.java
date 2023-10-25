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
import com.bdb.opaloshare.persistence.model.email.RequestEmail;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.util.List;

/**
 * Interfaz encargada de enviar correo Electronico
 *
 * @author: Andres Marles
 * @version: 12/01/2022
 * @since: 12/01/2022
 */
public interface EmailGenericService {
    boolean sendEmailGeneric(RequestEmail requestEmail) throws IOException, ErrorFtps, MessagingException, ExceptionHandling;
    boolean sendEmailGeneric(RequestEmail requestEmailGeneric, List<MultipartFile> files) throws IOException, MessagingException;
}
