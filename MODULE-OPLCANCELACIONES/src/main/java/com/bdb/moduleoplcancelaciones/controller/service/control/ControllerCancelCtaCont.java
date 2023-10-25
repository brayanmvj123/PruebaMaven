package com.bdb.moduleoplcancelaciones.controller.service.control;

import io.swagger.annotations.Api;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("CDTSDesmaterializado/v1/")
@Api(value = "Servicio para obtener la información de renovación de CDTs Digitales Desmaterializado.",
        tags = "Información sobre CDTs desmaterializados de oficina")
@CommonsLog
public class ControllerCancelCtaCont {

    @GetMapping(value = "cdt/cancelados/cuentacontable")
    public void pagosCuentasContables(){

        /**
         * CREAR JOB
         * BUSCAR EN LA TABLA SAL_PG LOS CDTS DE OFICINA (DIFERENTES A COD 3001) Y QUE ESTEN EN ESTADO **4**
         * REGISTRAR EN LA TABLA TRANSACCIONES
         * ESOS CDTS SERAN ENVIADOS A CANCELAR POR CUENTA CONTABLE
         * AGREGAR COD CUT
         */
    }

}
