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
package com.bdb.moduleoplcancelaciones.controller.service.implement;

import com.bdb.moduleoplcancelaciones.controller.service.interfaces.TramaTraductorCadiService;
import com.bdb.opaloshare.controller.service.interfaces.HttpClient;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.HisCdtxCtainvEntity;
import com.bdb.opaloshare.persistence.entity.HisInfoCDTEntity;
import com.bdb.opaloshare.persistence.entity.HisRenovacion;
import com.bdb.opaloshare.persistence.entity.HisTransaccionesEntity;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestTraductor.RequestTraductor;
import com.bdb.opaloshare.persistence.repository.RepositoryHisCdtxCtainv;
import com.bdb.opaloshare.persistence.repository.RepositoryHisInfoCDT;
import com.bdb.opaloshare.persistence.repository.RepositoryHisRenovacion;
import com.bdb.opaloshare.persistence.repository.RepositoryTransacciones;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;

/**
 * Service encargado de ejecutar traductor CADI
 *
 * @author: Andres Marles
 * @version: 10/11/2020
 * @since: 09/11/2020
 */
@Service
@CommonsLog
public class TramaTraductorCadiServiceImpl implements TramaTraductorCadiService {

    @Autowired
    private HttpClient httpClient;

    @Autowired
    SharedService sharedService;

    @Autowired
    private RepositoryHisInfoCDT repositoryHisInfoCDT;

    @Autowired
    private RepositoryTransacciones repositoryTransacciones;

    @Autowired
    private RepositoryHisRenovacion repositoryHisRenovacion;

    @Autowired
    private RepositoryHisCdtxCtainv repositoryHisCdtxCtainv;

    @Override
    public boolean tramasTraductorCadi(String urlBatch, RequestTraductor requestTraductor, List<HisCdtxCtainvEntity> hisCdtxCtainvEntities,
                                       List<HisTransaccionesEntity> transacciones, HisInfoCDTEntity hisInfoCDTEntity, HisRenovacion renovacion) {

        try {
            log.info("start TRADUCTOR in Office ...");
            String host = sharedService.generarUrl(urlBatch, "OPLCANCELACIONES");
//        final String urlTraductor = host + "TRADUCTOR/DO/v1/process/cancelacion";
            final String urlTraductor = "http://localhost:8081/DO/v1/process/cancelacion";

            ResponseEntity<String> response = httpClient.post(requestTraductor, urlTraductor, "TRADUCTOR", new ParameterizedTypeReference<String>() {
            });
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            repositoryHisInfoCDT.delete(hisInfoCDTEntity);
            repositoryTransacciones.deleteAll(transacciones);
            if (renovacion != null) repositoryHisRenovacion.delete(renovacion);
            repositoryHisCdtxCtainv.deleteAll(hisCdtxCtainvEntities);
            log.error("Error en CrossTraductorCadiTasklet: " + e);
            throw new UnexpectedJobExecutionException("Error en el proceso del servicio traductor, no esta disponible o no se pudo consumir, revisar.");
        }
    }
}
