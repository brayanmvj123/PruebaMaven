package com.bdb.opalossqls.controller.service.control;

import com.bdb.opalossqls.controller.service.interfaces.EstadosProcesosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("CDTSDesmaterializado/v1/renovacion/")
public class ControllerStatusRenaut {

    @Autowired
    private EstadosProcesosService estadosProcesosService;

    @PostMapping("estadoRenaut")
    public void estadoRenaut(@RequestParam String status){
        estadosProcesosService.llenarEstado(status);
    }
}
