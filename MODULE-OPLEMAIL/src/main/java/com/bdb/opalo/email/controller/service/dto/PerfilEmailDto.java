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
package com.bdb.opalo.email.controller.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Dto que expone el servicio de envio de correo, el cual tiene el idPerfil para buscar en la
 * base de datos los parametros del correo a enviar
 *
 * @author: Esteban Talero
 * @version: 26/07/2020
 * @since: 26/07/2020
 */
@Data
public class PerfilEmailDto {

    /**
     * Id perfil del correo a enviar
     */
    @NotNull(message = "Debe especificar el idPerfil")
    private Integer idPerfil;

    /**
     * Email Subject Type del correo a enviar
     */
    @NotNull(message = "Debe especificar el tipo de asunto que debe llevar el correo")
    private String emailSubjectType;

    /**
     * Email Content Type del correo a enviar
     */
    @NotNull(message = "Debe especificar el tipo de contenido o cuerpo de mensaje que debe llevar el correo")
    private String emailContentType;

    /**
     * Attached
     */
    @NotNull(message = "Debe especificar si tiene adjuntos o no")
    private Boolean attached;

}