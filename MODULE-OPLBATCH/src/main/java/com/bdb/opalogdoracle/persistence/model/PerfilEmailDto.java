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
package com.bdb.opalogdoracle.persistence.model;

import lombok.Data;

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

    private Integer idPerfil;

    private String emailSubjectType;

    private String emailContentType;

    private Boolean attached;
}