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

import com.bdb.opaloshare.persistence.entity.*;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opaloshare.persistence.model.trad.JSONMonetaryField;
import com.bdb.opaloshare.persistence.model.trad.JSONPlotFields;
import com.bdb.opaloshare.util.Plot;
import com.bdb.traductor.deceapdi.controller.service.interfaces.HisTraductoresDownService;
import com.bdb.traductor.deceapdi.controller.service.interfaces.SalTramastcDownService;
import com.bdb.traductor.deceapdi.controller.service.interfaces.TradContService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Controlador del servicio traductor APDI
 *
 * @author: Esteban Talero
 * @version: 23/10/2020
 * @since: 23/10/2020
 */
@RestController
@RequestMapping("DO/v1")
@CrossOrigin(origins = "*", maxAge = 0)
@CommonsLog
@Api(value = "Traductor APDI", description = "Servicio que permite generar la trama del traductor contable de Apertura de CDTs")
public class ControllerApDigital {

    @Autowired
    private TradContService service;

    @Autowired
    private SalTramastcDownService tramastc;

    @Autowired
    private HisTraductoresDownService traductoresDownService;

    @Value("${translator.transaction_code}")
    private String codTran;

    @Value("${translator.source_application}")
    private String aplFuente;

    /**
     * Hace el proceso de consulta de las aperturas de CDTs abiertas en un fecha especifica, obtiene el valor total del
     * CDT y sus transacciones correspondientes, para así posteriormente generar un hash y almacenarlo en base de datos.
     *
     * @param request Http Servlet Request.
     * @param date    Get date in format (yyyy-mm-dd).
     * @param canal   Get channel
     * @return Response entity with the result.
     */
    @SuppressWarnings("DuplicatedCode")
    @PostMapping(value = "process/apertura", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Traductor APDI", notes = "Retorna la trama del traductor de Apertura de CDTs")
    @ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Retorna cuando existen errores matematicos " +
                    "o no existen CDTs a procesar"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Bad Request"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
            @ApiResponse(code = HttpServletResponse.SC_SERVICE_UNAVAILABLE, message = "Retorna cuando el traductor no" +
                    "se encuentra en la base de datos")})
    public ResponseEntity<RequestResult<Map<String, List<SalTramastcDownEntity>>>> startConsultToTranslation(
            HttpServletRequest request,
            @RequestParam(value = "fecha", defaultValue = "") String date,
            @RequestParam(value = "canal") String canal) {

        // Set today if param date is empty.
        if (date.isEmpty()) date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        log.info("Iniciando traduccion contable de aperturas CDT del dia " + date);

        // Get all CDTs of the day.
        List<HisCDTSLargeEntity> cdtsLargeEntities = service.consultaCDTSLargeDia();

        // Check if translator is null
        if (traductoresDownService.getByTrad(aplFuente, codTran) == null) {
            log.fatal("No se encuentra traductor en base de datos.");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "No se encuentra traductor " + aplFuente + " en base de datos.");
        }

        // Get translator
        HisTraductoresDownEntity trd = traductoresDownService.getByTrad(aplFuente, codTran);
        log.info("TRADUCTOR -> " + trd.getAplFuente() + trd.getCodTran());

        // Check if is empty
        if (!cdtsLargeEntities.isEmpty()) {
            Map<String, List<SalTramastcDownEntity>> r = new HashMap<>();
            int noProcesses = 0;

            // Foreach all CDTs
            for (HisCDTSLargeEntity cdt : cdtsLargeEntities) {

                // Get all transactions
                BigDecimal sumatory = BigDecimal.ZERO;
                List<SalTramastcDownEntity> tramastcDownEntities = new ArrayList<>();

                for (HisTranpgEntity transac : cdt.getTransacciones()) {
                    sumatory = sumatory.add(transac.getProceso().equals("6") ? BigDecimal.ZERO : transac.getValor());
                    List<JSONMonetaryField> monetaryFields = new ArrayList<>();

                    // Start plot
                    JSONPlotFields plotFields = new JSONPlotFields();
                    plotFields.setCodEntidad(trd.getCodEntidad());
                    plotFields.setAppFuente(trd.getAplFuente());
                    plotFields.setCodTrasaccion(trd.getCodTran());
                    plotFields.setFechaContable(new SimpleDateFormat("yyyyMMdd")
                            .format(Date.from(Instant.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault())))));
                    plotFields.setOficinaOrigen(transac.getUnidOrigen());
                    plotFields.setOficinaDestino("" + transac.getUnidDestino());
                    plotFields.setProducto(trd.getProdPrin());
                    plotFields.setNumProducto(cdt.getNumCdt());
                    plotFields.setTipoNegocio(trd.getTipNegocio());
                    plotFields.setNumeroNegocio(cdt.getCodTrn());
                    plotFields.setCxvcvxvx("" + transac.getUnidDestino());
                    plotFields.setCodCajero(trd.getCodCajero());
                    plotFields.setFiller(trd.getFiller());
                    plotFields.setNumCamposMonetarios("0"); // This field is filled by generator
                    plotFields.setTamParteVariable("0"); // This field is filled by generator

                    // Order result
                    Collections.sort(trd.getCamposMonetarios());

                    for (HisCamposMonetDownEntity cm : trd.getCamposMonetarios()) {
                        JSONMonetaryField pf = new JSONMonetaryField("0", BigDecimal.ZERO);

                        // Set field ID
                        pf.setIdentificador(cm.getIdCampo());

                        switch (cdt.getOplTipplazoTblTipPlazo()) {
                            // Term in (1 = Days)
                            case 1:
                                if (cdt.getPlazo() < 180 && cm.getIdCampo().equals("01")) { // Term minor than 6 months
                                    pf.setValor(service.validarRenovacion(transac));
                                } else if (cdt.getPlazo() >= 180 && cdt.getPlazo() < 365 && cm.getIdCampo().equals("02")) { // Term between 6 and 12 months
                                    pf.setValor(service.validarRenovacion(transac));
//                                    pf.setValor(transac.getValor());
                                } else if (cdt.getPlazo() >= 365 && cdt.getPlazo() < 540 && cm.getIdCampo().equals("03")) { // Term between 12 and 18 months
                                    pf.setValor(service.validarRenovacion(transac));
//                                    pf.setValor(transac.getValor());
                                } else if (cdt.getPlazo() >= 540 && cm.getIdCampo().equals("04")) { // Term major than 18 months
                                    pf.setValor(service.validarRenovacion(transac));
//                                    pf.setValor(transac.getValor());
                                } else if (transac.getProceso().equals("1") && transac.getOplTiptransTblTipTrasaccion() == 1L
                                        && cm.getIdCampo().equals("05")) { // Paid with "Cuenta Corriente"
                                    pf.setValor(transac.getValor());
                                } else if (transac.getProceso().equals("1") && transac.getOplTiptransTblTipTrasaccion() == 2L
                                        && cm.getIdCampo().equals("06")) { // Paid with "Cuenta Ahorros"
                                    pf.setValor(transac.getValor());
                                } else if (transac.getOplTiptransTblTipTrasaccion() == 7L && cm.getIdCampo().equals("07")) { // Paid with "Fondos recibidos por reinversion"
                                    pf.setValor(service.validarRenovacion(transac));
//                                    pf.setValor(transac.getValor());
                                } else if (transac.getProceso().equals("6") && transac.getOplTiptransTblTipTrasaccion() == 2L
                                        && cm.getIdCampo().equals("08")) { // Paid with "CA - Aumento de capital por renovacion"
                                    pf.setValor(transac.getValor());
                                } else if (transac.getProceso().equals("6") && transac.getOplTiptransTblTipTrasaccion() == 1L
                                        && cm.getIdCampo().equals("09")) { // Paid with "CC- Aumento de capital por renovacion"
                                    pf.setValor(transac.getValor());
                                }
                                break;
                            // Term in (2 = Months)
                            case 2:
                                log.info("Plazo en meses");
                                if (cdt.getPlazo() < 6 && cm.getIdCampo().equals("01")) { // Term minor than 6 months
                                    pf.setValor(service.validarRenovacion(transac));
//                                    pf.setValor(transac.getValor());
                                } else if (cdt.getPlazo() >= 6 && cdt.getPlazo() < 12 && cm.getIdCampo().equals("02")) { // Term between 6 and 12 months
                                    pf.setValor(service.validarRenovacion(transac));
//                                    pf.setValor(transac.getValor());
                                } else if (cdt.getPlazo() >= 12 && cdt.getPlazo() < 18 && cm.getIdCampo().equals("03")) { // Term between 12 and 18 months
                                    pf.setValor(service.validarRenovacion(transac));
//                                    pf.setValor(transac.getValor());
                                } else if (cdt.getPlazo() >= 18 && cm.getIdCampo().equals("04")) { // Term major than 18 months
                                    pf.setValor(service.validarRenovacion(transac));
//                                    pf.setValor(transac.getValor());
                                } else if (transac.getProceso().equals("1") && transac.getOplTiptransTblTipTrasaccion() == 1L
                                        && cm.getIdCampo().equals("05")) { // Paid with "Cuenta Corriente"
                                    pf.setValor(transac.getValor());
                                } else if (transac.getProceso().equals("1") && transac.getOplTiptransTblTipTrasaccion() == 2L
                                        && cm.getIdCampo().equals("06")) { // Paid with "Cuenta Ahorros"
                                    pf.setValor(transac.getValor());
                                } else if (transac.getOplTiptransTblTipTrasaccion() == 7L && cm.getIdCampo().equals("07")) { // Paid with "Fondos recibidos por reinversion"
                                    pf.setValor(service.validarRenovacion(transac));
//                                    pf.setValor(transac.getValor());
                                } else if (transac.getProceso().equals("6") && transac.getOplTiptransTblTipTrasaccion() == 2L
                                        && cm.getIdCampo().equals("08")) { // Paid with "Aumento de capital por renovacion"
                                    pf.setValor(transac.getValor());
                                } else if (transac.getProceso().equals("6") && transac.getOplTiptransTblTipTrasaccion() == 1L
                                        && cm.getIdCampo().equals("09")) { // Paid with "CC- Aumento de capital por renovacion"
                                    pf.setValor(transac.getValor());
                                }
                                break;
                        }

                        monetaryFields.add(pf);
                    }

                    plotFields.setCamposMonetarios(monetaryFields);

                    // Generate plot
                    String plot = Plot.deceApdi(plotFields);

                    log.info("PLOT->" + plot);

                    // Plot
                    SalTramastcDownEntity tre = new SalTramastcDownEntity();
                    tre.setTrama(plot);
                    tre.setFecha(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()));

                    // Add to save list
                    tramastcDownEntities.add(tre);
                }

                // Check if CDT transactions are valid
                if (cdt.getValor().compareTo(sumatory) == 0) {
                    tramastc.saveAll(tramastcDownEntities);
                    r.put(cdt.getNumCdt(), tramastcDownEntities);

                    log.info("Los valores de las transacciones concuerdan con el CDT, guardado en la base de datos.");
                } else {
                    noProcesses++;
                    log.fatal("La sumatoria de transacciones del CDT " + cdt.getNumCdt()
                            + " no concuerdan con el valor del CDT. Las tramas no seran generadas."
                            + " (" + cdt.getValor() + " != " + sumatory + ")");
                }
            }

            // If is empty in final process
            if (r.isEmpty()) {
                log.info("No se generaron CDTs, " + noProcesses + " CDTs con errores matemáticos.");
                throw new ResponseStatusException(HttpStatus.NO_CONTENT,
                        "No se generaron CDTs, " + noProcesses + " CDTs con errores matemáticos.");
            }

            return ResponseEntity.ok(new RequestResult<>(request, HttpStatus.OK, r));
        } else {
            log.info("No hay CDTs para procesar.");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay CDTs para procesar.");
        }
    }
}
