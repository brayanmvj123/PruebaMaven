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
package com.bdb.opalogdoracle.controller.service.implement;

import com.bdb.opalogdoracle.controller.service.interfaces.ApersobreRenautService;
import com.bdb.opalogdoracle.controller.service.interfaces.TramaTraductorCadiService;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service encargado de ejecutar traductor CADI
 *
 * @author: Esteban Talero
 * @version: 10/11/2020
 * @since: 09/11/2020
 */
@Service
public class TramaTraductorCadiServiceImpl implements TramaTraductorCadiService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SharedService sharedService;

    @Autowired
    ApersobreRenautService apersobreRenautService;

    @Override
    public String tramasTraductorCadi(String url, String urlBatch) throws Exception {
        String responseBody;
        try {
            logger.info("start TRADUCTOR CADI...");
            String host = sharedService.generarUrl(urlBatch, "OPLBATCH");
            final String urlTraductor = host + "TRADUCTOR/DO/v1/process/cancelacion";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(urlTraductor,
                    HttpMethod.POST,
                    null,
                    new ParameterizedTypeReference<String>() {
                    });
            responseBody = response.getBody();
        } catch (Exception e) {
            apersobreRenautService.almacenarEstadoRenautSQLServer(url, "RENAUT_FAIL");
            logger.error("Error en CrossTraductorCadiTasklet: {0}", e);
            throw new UnexpectedJobExecutionException("Error en CrossTraductorCadiTasklet");
        }
        return responseBody;
    }
}
