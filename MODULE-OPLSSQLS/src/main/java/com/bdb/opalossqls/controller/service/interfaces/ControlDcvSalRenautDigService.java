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
package com.bdb.opalossqls.controller.service.interfaces;

import com.bdb.opaloshare.persistence.model.component.ModelCrucePatrimonioRenaut;

import java.util.List;

/**
 * Service encargada de limpiar y guardar en la tabla salRenautDig los Cdts
 *
 * @author: Esteban Talero
 * @version: 19/10/2020
 * @since: 19/10/2020
 */
public interface ControlDcvSalRenautDigService {
    boolean controlDcvRenautDig(List<ModelCrucePatrimonioRenaut> listPatrimonio);
}
