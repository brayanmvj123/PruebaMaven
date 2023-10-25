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
package com.bdb.opalo.control.controller.service.interfaces;

import com.bdb.opalo.control.persistence.dto.ControlCdtDto;
import com.bdb.opalo.control.persistence.exception.ControlCdtsException;

/**
 * Interface encargado del Control renovacion CDT
 *
 * @author: Esteban Talero
 * @version: 01/10/2020
 * @since: 01/10/2020
 */
public interface ControlCdtService {
    boolean controlCdtMarcacion(ControlCdtDto controlCdtDto) throws ControlCdtsException;
}
