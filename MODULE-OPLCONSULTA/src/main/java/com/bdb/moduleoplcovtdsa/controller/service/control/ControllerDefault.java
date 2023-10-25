package com.bdb.moduleoplcovtdsa.controller.service.control;

import com.bdb.opaloshare.persistence.model.system.AppData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerDefault {
    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.application.version}")
    private String version;

    @Value("${spring.application.description}")
    private String description;

    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<AppData> info() {
        AppData data = new AppData();
        data.setNombre(applicationName);
        data.setVersion(version);
        data.setDescripcion(description);

        return ResponseEntity.ok(data);
    }
}
