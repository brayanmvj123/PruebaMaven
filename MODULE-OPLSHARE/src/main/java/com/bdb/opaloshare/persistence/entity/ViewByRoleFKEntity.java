package com.bdb.opaloshare.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

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
public class ViewByRoleFKEntity implements Serializable {
    private Long rol;
    private Long vista;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewByRoleFKEntity that = (ViewByRoleFKEntity) o;
        return rol.equals(that.rol) && vista.equals(that.vista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rol, vista);
    }
}
