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
public enum UserAction {

    // Do disable user
    DISABLE("DISABLE", UserStatus.INACTIVE.getValue()),

    // Do enable user
    ENABLE("ENABLE", UserStatus.ACTIVE.getValue());

    private final String action;

    private final String value;

    /**
     * user Action.
     *
     * @param action to do
     * @param value  to get
     */
    UserAction(String action, String value) {
        this.action = action;
        this.value = value;
    }

    /**
     * Action to required value
     *
     * @param action action
     * @return value
     */
    public static String actionToValue(String action) {
        return UserAction.valueOf(action).value;
    }
}
