package com.bdb.opaloshare.persistence.model.jsonschema.XLSX;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XLSXSheetModel implements Serializable {

    @NotNull
    private String title;

    @NotNull
    private String author;

    @NotNull
    private String password;

    @NotNull
    private List<String> headers;

    @NotNull
    private List<List<String>> cells;

    private static final long serialVersionUID = 1L;
}