package com.bdb.opaloshare.persistence.model.trad;

import com.bdb.opaloshare.persistence.model.response.RequestResult;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("CDT/v1")
public class ControllerGetCDT {

    @PostMapping(value = "CDTT", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<RequestResult> getCDT() {
        return null;
    }
}
