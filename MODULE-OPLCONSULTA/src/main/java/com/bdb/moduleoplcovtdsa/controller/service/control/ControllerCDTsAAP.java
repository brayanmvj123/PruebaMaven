package com.bdb.moduleoplcovtdsa.controller.service.control;

import com.bdb.moduleoplcovtdsa.controller.service.interfaces.CDTsAAPService;
import com.bdb.moduleoplcovtdsa.persistence.JSONSchema.JSONConsultaPPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@CrossOrigin(origins = "*", maxAge = 0, allowedHeaders = "*", methods = {RequestMethod.POST})
public class ControllerCDTsAAP {

    @Autowired
    private CDTsAAPService serviceAAP;

    /**
     * Servicio de consulta de CDT Desmaterializado.
     *
     * @param request Json de consulta.
     * @return Respuesta de consulta del servicio.
     */

    @PostMapping(value = "consCDTSDesAAP", produces = {"application/json"})
    public String consCDTSDesAAP(@Valid @RequestBody JSONConsultaPPE request) {
        return serviceAAP.consCDTSDesAAP(request);
    }
}
