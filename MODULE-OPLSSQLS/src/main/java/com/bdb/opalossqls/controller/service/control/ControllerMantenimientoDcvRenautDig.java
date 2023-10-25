/*
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Esteban Talero <etalero@bancodebogota.com.co>.
 */
package com.bdb.opalossqls.controller.service.control;

import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.model.component.ModelCrucePatrimonioRenaut;
import com.bdb.opalossqls.controller.service.implement.ControlDcvSalRenautDigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Controller encargada de limpiar y guardar en la tabla salRenautDig los Cdts
 *
 * @author: Esteban Talero
 * @version: 19/10/2020
 * @since: 19/10/2020
 */
@RestController
@RequestMapping("CDTSDesmaterializado/v1")
public class ControllerMantenimientoDcvRenautDig {

    @Autowired
    ControlDcvSalRenautDigImpl controlDcvSalRenautDig;

    @Autowired
    private SharedService serviceShared;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("controlDcvRenautDig")
    public String controlDcvRenautDig(HttpServletRequest http) throws Exception {

        logger.info("start ControllerMantenimientoDcvRenautDig controlDcvRenautDig()...");
        if (controlDcvSalRenautDig.controlDcvRenautDig(obtenerConsultaCrucePatrimonio(serviceShared.generarUrlSql(http.
                getRequestURL().toString())))) {
            return "{\"status\":\"200-OK\"}";
        } else {
            return "{\"status\":\"500-FAIL\"}";
        }
    }

    /**
     * Obtiene el resultado de la consulta del cruce de los CDTs digitales que esten con la marcacion en la
     * tabla de controles de CDTs
     *
     * @param host ambiente
     * @return list con data
     */
    public List<ModelCrucePatrimonioRenaut> obtenerConsultaCrucePatrimonio(String host) {
        String url = host + "OPLBATCH/CDTSDesmaterializado/v1/cruceInformacionRenautDig";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<ModelCrucePatrimonioRenaut>> response = restTemplate.exchange(url, HttpMethod.POST,
                null, new ParameterizedTypeReference<List<ModelCrucePatrimonioRenaut>>() {
                });
        return response.getBody();
    }

    @PostMapping("envioCDTsDcvRenautDig")
    public ResponseEntity<String> envioCDTsDcvRenautDig(@RequestBody List<ModelCrucePatrimonioRenaut> request){
        if (controlDcvSalRenautDig.controlDcvRenautDig(request)) {
            logger.info("CDTs Digitales entregados correctamente.");
            return ResponseEntity.ok("CDTs Digitales entregados correctamente.");
        }else {
            logger.error("Error ocurrido al entregar los CDTs Digitales en la tabla DCV_SAL_RENAUT.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error ocurrido al entregar los " +
                    "CDTs Digitales en la tabla DCV_SAL_RENAUT.");
        }
    }

}
