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
package com.bdb.opalo.email.controller.service.implement;

import com.bdb.opalo.email.controller.service.interfaces.EmailDataService;
import com.bdb.opaloshare.persistence.entity.OplParCorreoEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryDatosCorreo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio que encargado de retornar las caracteristicas del correo a enviar dependiendo
 * el ID de perfil que se envie
 *
 * @author: Esteban Talero
 * @version: 26/07/2020
 * @since: 26/07/2020
 */
@Service
public class EmailDataImpl implements EmailDataService {

    /**
     * Interfaz de la libreria OPLSHARE encargado de ejecutar el query JPA para retornar
     * los valores de la base de datos
     */
    @Autowired
    private RepositoryDatosCorreo repository;

    /**
     * Método que retorna el resultado de la consulta los datos del correo a enviar
     *
     * @param idPerfil el valor para consultar las caracteristicas del correo a enviar
     * @return el resultado del query
     */
    @Override
    public OplParCorreoEntity getByIdPerfil(Integer idPerfil) {
        return repository.findByIdPerfil(idPerfil);
    }
}
