package com.bdb.opalotasasfija.controller.service.control;

import com.bdb.opalotasasfija.controller.service.interfaces.CalculoDiasCDTService;
import com.bdb.opalotasasfija.controller.service.interfaces.CalculoFechaVencimientoService;
import com.bdb.opalotasasfija.persistence.JSONSchema.ServiceCalculoFechaVen.JSONCalculoDiasCDT;
import com.bdb.opalotasasfija.persistence.JSONSchema.ServiceCalculoFechaVen.JSONCalculoFechaVencimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
public class ControllerCalculoFechaVencimiento {

    @Autowired
    CalculoFechaVencimientoService serviceCalcFecVen;

    @Autowired
    CalculoDiasCDTService serviceCalcDias;

    @PostMapping(value = "simulador/calculoFechaVencimiento" , consumes = {"application/json"} , produces = {"application/json"})
    public String calculoFechaVencimiento(@Valid @RequestBody JSONCalculoFechaVencimiento request){
        return serviceCalcFecVen.calculoFechaVencimiento(request);
    }

    @PostMapping(value = "simulador/calculoDiasCDT" , consumes = {"application/json"} , produces = {"application/json"})
    public String calculoDiasCDT(@Valid @RequestBody JSONCalculoDiasCDT request){
        return serviceCalcDias.calculoFechaVencimiento(request);
    }
}
