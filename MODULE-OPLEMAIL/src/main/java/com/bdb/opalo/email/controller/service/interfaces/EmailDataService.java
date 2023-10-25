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

import com.bdb.opaloshare.persistence.entity.OplParCorreoEntity;

/**
 * Interfaz encargada de obtener los valores de la consulta de la base de datos
 *
 * @author: Esteban Talero
 * @version: 26/07/2020
 * @since: 26/07/2020
 */
public interface EmailDataService {

    /**
     * OplParCorreoEntity con el resultado de la consulta en la base de datos
     *
     * @param idPerfil
     * @return
     */
    OplParCorreoEntity getByIdPerfil(Integer idPerfil);

}
