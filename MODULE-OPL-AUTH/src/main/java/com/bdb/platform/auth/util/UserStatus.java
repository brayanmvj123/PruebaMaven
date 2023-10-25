package com.bdb.platform.auth.util;

import lombok.Getter;

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
@Getter
public enum UserStatus {
    /**
     * If user has this status then user can enter to the platform.
     *
     * @since 02/01/2020
     */
    ACTIVE("1"),

    /**
     * If user has this status then user can't enter to the platform.
     *
     * @since 02/01/2020
     */
    INACTIVE("2"),

    /**
     * If user has this status then user can´t be disabled.
     *
     * @since 02/01/2020
     */
    PERMANENT("0");

    // Status value
    private final String value;

    /**
     * Status constructor.
     *
     * @param s status value
     */
    UserStatus(String s) {
        this.value = s;
    }
}
