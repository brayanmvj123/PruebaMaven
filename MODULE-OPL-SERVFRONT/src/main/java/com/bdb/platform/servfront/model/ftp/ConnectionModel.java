package com.bdb.platform.servfront.model.ftp;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionModel {
    @NotNull
    @Digits(integer = 4, fraction = 0)
    @JsonAlias(value = "connection_id")
    private Long connectionId;

    @NotNull
    @Size(max = 1)
    @JsonAlias(value = "connection_type")
    private String connectionType;

    @NotNull
    @Size(max = 100)
    @JsonAlias(value = "host_name")
    private String hostName;

    @NotNull
    @Size(max = 100)
    @JsonAlias(value = "host_ip")
    private String hostIp;

    @NotNull
    @Digits(integer = 5, fraction = 0)
    private Long port;

    @NotNull
    @Size(max = 100)
    private String description;

    @NotNull
    @Digits(integer = 3, fraction = 0)
    @JsonAlias(value = "user_id")
    private Long userId;

    @NotNull
    @Size(max = 500)
    private String path;
}
