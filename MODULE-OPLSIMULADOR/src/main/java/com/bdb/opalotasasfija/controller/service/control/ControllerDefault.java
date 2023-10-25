package com.bdb.opalotasasfija.controller.service.control;

import com.bdb.opaloshare.persistence.model.system.AppData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (c) 2019 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file war write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@RestController
public class ControllerDefault {
    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.application.version}")
    private String version;

    @Value("${spring.application.description}")
    private String description;

    @RequestMapping(value = "/", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<AppData> info() {
        AppData data = new AppData();
        data.setNombre(applicationName);
        data.setVersion(version);
        data.setDescripcion(description);

        return ResponseEntity.ok(data);
    }
}
