package com.bdb.platform.servfront.model.commons;

import java.math.BigDecimal;

/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBF was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
public class Constants {
    public static final BigDecimal CIEN = new BigDecimal(100);

    public static final String ACC_PAR_TXT_MEDIUM_TBL_selectById = "select * from ACC_PAR_TXT_MEDIUM_TBL where ID_TXT = ? ";

    public static final String ACC_PAR_TXT_MEDIUM_TBL_insert = "insert into ACC_PAR_TXT_MEDIUM_TBL (ID_TXT, TXT_DESC) values (?, ?)";

    public static final String LABEL_OK = "label Creado con Exito";
}
