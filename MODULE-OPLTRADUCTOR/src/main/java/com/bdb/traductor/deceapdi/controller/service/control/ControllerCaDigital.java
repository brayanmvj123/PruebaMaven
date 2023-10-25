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
package com.bdb.traductor.deceapdi.controller.service.control;

import com.bdb.opaloshare.persistence.entity.HisTraductoresDownEntity;
import com.bdb.opaloshare.persistence.entity.SalTramastcDownEntity;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opaloshare.persistence.model.trad.CruceHisCdtRenautDigDto;
import com.bdb.opaloshare.persistence.model.trad.RequestTradCDT;
import com.bdb.traductor.deceapdi.controller.service.interfaces.CreatePlotService;
import com.bdb.traductor.deceapdi.controller.service.interfaces.HisTraductoresDownService;
import com.bdb.traductor.deceapdi.controller.service.interfaces.TradContService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controlador encargado de generar la trama del traductor CADI, realizando los cruces en la base de
 * datos internamente
 *
 * @author: Esteban Talero
 * @version: 26/10/2020
 * @since: 26/10/2020
 */
@RestController
@RequestMapping("DO/v1")
@CrossOrigin(origins = "*", maxAge = 0)
@CommonsLog
public class ControllerCaDigital {

    /**
     * Service encargado de realizar el cruce de las tablas para obtener la Data para generar la trama
     */
    @Autowired
    TradContService service;

    /**
     * Service encargado de consultar los datos basicos del traductor o las cabeceras del mismo
     */
    @Autowired
    HisTraductoresDownService traductoresDownService;

    /**
     * Service encargado de generar la trama del traductor CADI
     */
    @Autowired
    CreatePlotService createPlotService;

    /**
     * Alias de traductor a generar para buscar en la base de datos
     */
    @Value("${translator.transaction_code_cadi}")
    String codTran;

    /**
     * Alias del aplicativo al que pertenece el traductor para buscar en la base de datos
     */
    @Value("${translator.source_application}")
    String aplFuente;

    /**
     * Hace el proceso de consulta de las aperturas de CDTs abiertas en un fecha especifica, obtiene el valor total del
     * CDT y sus transacciones correspondientes, para así posteriormente generar un hash y almacenarlo en base de datos.
     *
     * @param request Http Servlet Request
     * @return Response entity with the result.
     */
    @PostMapping(value = "process/cancelacion", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RequestResult<Map<String, List<SalTramastcDownEntity>>>> startConsultToTranslationCadi(
            HttpServletRequest request, @RequestBody(required = false) RequestTradCDT requestTradCDT) {

        // Get all CDTs Renaut Dig of the day.
//        List<CruceHisCdtRenautDigDto> hisCdtsCadig = service.consultaCDTSCadiCancel();

        List<CruceHisCdtRenautDigDto> hisCdtsCadig;
        if(requestTradCDT == null) {
            hisCdtsCadig = service.consultaCDTSCadiCancel();
        } else {
            hisCdtsCadig = service.consultaCDTSCaOficina(requestTradCDT.getNumCdt(), requestTradCDT.getCodIsin(), requestTradCDT.getCtaInv());
        }

        // Get translator
        HisTraductoresDownEntity trd = traductoresDownService.getByTrad(aplFuente, codTran);

        // Check if translator is null
        if (!Optional.ofNullable(trd).isPresent()) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "No se encuentra traductor " + codTran +
                    " en base de datos.");
        }

        System.out.println("TRADUCTOR CADI -> " + trd.getAplFuente() + trd.getCodTran());

        // Check if is empty
        if (!hisCdtsCadig.isEmpty()) {

            List<Long> cdtsHisCadi = new ArrayList<>();
            for (CruceHisCdtRenautDigDto transac : hisCdtsCadig) {
                if (!cdtsHisCadi.contains(transac.getNumCdt())) {
                    cdtsHisCadi.add(transac.getNumCdt());
                }
            }
            System.out.println("CDTs a procesar "+cdtsHisCadi.size());
            //Create the CADI translator plot by sending the data
            Map<String, List<SalTramastcDownEntity>> r = createPlotService.createPlot(cdtsHisCadi, hisCdtsCadig, trd);

            // If is empty in final process
            if (r.isEmpty()) {
                System.out.println("No se generaron CDTs,  CDTs con errores matemáticos.");
                throw new ResponseStatusException(HttpStatus.NO_CONTENT,
                        "No se generaron CDTs, CDTs con errores matemáticos.");
            }

            return ResponseEntity.ok(new RequestResult<>(request, HttpStatus.OK, r));
        } else {
            System.out.println("No hay CDTs para procesar.");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay CDTs para procesar.");
        }
    }
}
