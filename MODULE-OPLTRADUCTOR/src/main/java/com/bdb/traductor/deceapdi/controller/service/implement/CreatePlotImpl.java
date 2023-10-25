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
package com.bdb.traductor.deceapdi.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.*;
import com.bdb.opaloshare.persistence.model.trad.CruceHisCdtRenautDigDto;
import com.bdb.opaloshare.persistence.model.trad.JSONMonetaryField;
import com.bdb.opaloshare.persistence.model.trad.JSONPlotFields;
import com.bdb.opaloshare.util.Plot;
import com.bdb.traductor.deceapdi.controller.service.interfaces.CreatePlotService;
import com.bdb.traductor.deceapdi.controller.service.interfaces.SalTramastcDownService;
import com.bdb.traductor.deceapdi.persistence.model.MonetaryFields;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Implementacion encargado de generar la trama del traductor CADI
 *
 * @author: Esteban Talero
 * @version: 14/12/2020
 * @since: 26/10/2020
 */
@Service
@CommonsLog
public class CreatePlotImpl implements CreatePlotService {

    /**
     * Service encargado de guardar las tramas generadas de los CDT
     */
    @Autowired
    SalTramastcDownService tramastc;

    /**
     * Metodo encargado de generar la trama del traductor CADI
     *
     * @param cdtsHisCadi  Lista con los CDT's de la tabla historica que tengan tipo de proceso 3 Pago capital
     *                     4 Pago intereses y 5 Rte Fuente
     * @param hisCdtsCadig Lista con los datos del cruce de la tabla historica del CDT{@link HisCDTSLargeEntity}
     *                     con la tabla de transacciones de renovacion {@link HisTranpgEntity}
     * @param trd          Objeto con los valores basicos que tendra el traductor {@link HisTraductoresDownEntity}
     * @return Trama resuelta del traductor CADI Dividida en tres tramas por CDT recibido en el siguiente orden
     * 1 trama tendra los datos del capital a pagar
     * 2 trama tendra los datos de intereses
     * 3 trama tendra los datos de rte Fuente
     */
    @Override
    public Map<String, List<SalTramastcDownEntity>> createPlot(List<Long> cdtsHisCadi, List<CruceHisCdtRenautDigDto> hisCdtsCadig,
                                                               HisTraductoresDownEntity trd) {

        Map<String, List<SalTramastcDownEntity>> r = new HashMap<>();

        // Foreach all CDTs
        for (Long numCdtCadi : cdtsHisCadi) {
            List<SalTramastcDownEntity> tramastcDownEntities = new ArrayList<>();
            for (CruceHisCdtRenautDigDto transac : hisCdtsCadig) {
                // Get all transactions
                List<JSONMonetaryField> monetaryFields = new ArrayList<>();

                // Start plot
                JSONPlotFields plotFields = new JSONPlotFields();
                plotFields.setCodEntidad(trd.getCodEntidad());
                plotFields.setAppFuente(trd.getAplFuente());
                plotFields.setCodTrasaccion(trd.getCodTran());
                plotFields.setFechaContable(new SimpleDateFormat("yyyyMMdd")
                        .format(Date.from(Instant.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault())))));
                plotFields.setOficinaOrigen(transac.getUnidadOrigen());
                plotFields.setOficinaDestino(transac.getUnidadDestino());
                plotFields.setProducto(trd.getProdPrin());
                plotFields.setNumProducto(transac.getNumCdt().toString());
                plotFields.setTipoNegocio(trd.getTipNegocio());
                plotFields.setNumeroNegocio(transac.getCodTrn());
                plotFields.setCxvcvxvx("" + transac.getOficina());
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

                    switch (transac.getOplTipPlazo()) {
                        // Term in (1 = Days)
                        case 1:
                            if (transac.getPlazo() < 180 && cm.getIdCampo().equals("01")) { // Term minor than 6 months
                                pf.setValor((transac.getProceso().equals("3") || transac.getProceso().equals("7")) ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (transac.getPlazo() >= 180 && transac.getPlazo() < 365 && cm.getIdCampo().equals("02")) { // Term between 6 and 12 months
                                pf.setValor((transac.getProceso().equals("3") || transac.getProceso().equals("7")) ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (transac.getPlazo() >= 365 && transac.getPlazo() < 540 && cm.getIdCampo().equals("03")) { // Term between 12 and 18 months
                                pf.setValor((transac.getProceso().equals("3") || transac.getProceso().equals("7")) ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (transac.getPlazo() >= 540 && cm.getIdCampo().equals("04")) { // Term major than 18 months
                                pf.setValor((transac.getProceso().equals("3") || transac.getProceso().equals("7")) ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (transac.getPlazo() < 180 && cm.getIdCampo().equals("05")) { // Term minor than 6 months
                                pf.setValor((transac.getProceso().equals("4") || transac.getProceso().equals("5")) ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (transac.getPlazo() >= 180 && transac.getPlazo() < 365 && cm.getIdCampo().equals("06")) { // Term between 6 and 12 months
                                pf.setValor((transac.getProceso().equals("4") || transac.getProceso().equals("5")) ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (transac.getPlazo() >= 365 && transac.getPlazo() < 540 && cm.getIdCampo().equals("07")) { // Term between 12 and 18 months
                                pf.setValor((transac.getProceso().equals("4") || transac.getProceso().equals("5")) ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (transac.getPlazo() >= 540 && cm.getIdCampo().equals("08")) { // Term major than 18 months
                                pf.setValor((transac.getProceso().equals("4") || transac.getProceso().equals("5")) ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (cm.getIdCampo().equals("09")) { // Paid with "Fondos recibidos por reinversion"
                                pf.setValor((transac.getProceso().equals("3")) && transac.getTipTransaccion() == 7 ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (cm.getIdCampo().equals("10")) { // Paid with "Fondos recibidos por reinversion"
                                pf.setValor((transac.getProceso().equals("4")) && transac.getTipTransaccion() == 7 ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (cm.getIdCampo().equals("11")) { // Paid with "Fondos recibidos por reinversion"
                                pf.setValor((transac.getProceso().equals("4") && transac.getTipTransaccion() == 7) ? transac.getGmf() : BigDecimal.ZERO);
                            } else if (cm.getIdCampo().equals("12")) { // Paid with "Fondos recibidos por reinversion"
                                pf.setValor((transac.getProceso().equals("5") && transac.getTipTransaccion() == 8) ?
                                        determinaValorMonetario(transac.getProceso(), transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);

                            } else if (cm.getIdCampo().equals("13")) { // "Abono a Cuenta Corriente"
                                pf.setValor(getValueMonetary(transac, "3", "4", 1));
                            } else if (cm.getIdCampo().equals("14")) { // GMF de los Intereses con abono a Cuenta Corriente"
                                pf.setValor((transac.getProceso().equals("4") && transac.getTipTransaccion() == 1) ?
                                        transac.getGmf() : BigDecimal.ZERO);
                            } else if (cm.getIdCampo().equals("15")) { // Abono a Cuenta de Ahorros"
                                pf.setValor(getValueMonetary(transac, "3", "4", 2));
                            } else if (cm.getIdCampo().equals("16")) { // GMF de los Intereses con abono a Cuenta de Ahorros"
                                pf.setValor((transac.getProceso().equals("4") && transac.getTipTransaccion() == 2) ?
                                        transac.getGmf() : BigDecimal.ZERO);
                            } else if (cm.getIdCampo().equals("17")) { // En cuenta contable"
//                                pf.setValor(getValueMonetary(transac, "3", "4", 6));
                                pf.setValor(determinaValorCuentaContable(transac.getProceso(), transac.getTipTransaccion(), transac.getValor(), cm.getIdCampo()));
                            } else if (cm.getIdCampo().equals("18")) { // GMF De Capital en cuenta contable"
//                                pf.setValor((transac.getProceso().equals("3") && transac.getTipTransaccion() == 6) ?
//                                        transac.getGmf() : BigDecimal.ZERO);
                                pf.setValor(determinaValorCuentaContable(transac.getProceso(), transac.getTipTransaccion(), transac.getGmf(), cm.getIdCampo()));
                            } else if (cm.getIdCampo().equals("19")) { // GMF De los intereses en cuenta contable"
                                pf.setValor((transac.getProceso().equals("4") && transac.getTipTransaccion() == 6) ?
                                        transac.getGmf() : BigDecimal.ZERO);
                            } else if (cm.getIdCampo().equals("20")) { // Disminución de capital a cuenta de ahorros
                                pf.setValor((transac.getProceso().equals("7") && transac.getTipTransaccion() == 2) ?
                                        transac.getValor() : BigDecimal.ZERO);
                            } else if (cm.getIdCampo().equals("21")) { // Disminución de capital a cuenta corriente
                                pf.setValor((transac.getProceso().equals("7") && transac.getTipTransaccion() == 1) ?
                                        transac.getValor() : BigDecimal.ZERO);
                            }
                            break;
                        // Term in (2 = Months)
                        case 2:
                            System.out.println("Plazo en meses");
                            if (transac.getPlazo() < 6 && cm.getIdCampo().equals("01")) { // Term minor than 6 months
                                pf.setValor((transac.getProceso().equals("3") || transac.getProceso().equals("7")) ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (transac.getPlazo() >= 6 && transac.getPlazo() < 12 && cm.getIdCampo().equals("02")) { // Term between 6 and 12 months
                                pf.setValor((transac.getProceso().equals("3") || transac.getProceso().equals("7")) ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (transac.getPlazo() >= 12 && transac.getPlazo() < 18 && cm.getIdCampo().equals("03")) { // Term between 12 and 18 months
                                pf.setValor((transac.getProceso().equals("3") || transac.getProceso().equals("7")) ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (transac.getPlazo() >= 18 && cm.getIdCampo().equals("04")) { // Term major than 18 months
                                pf.setValor((transac.getProceso().equals("3") || transac.getProceso().equals("7")) ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (transac.getPlazo() < 6 && cm.getIdCampo().equals("05")) { // Term minor than 6 months
                                pf.setValor((transac.getProceso().equals("4") || transac.getProceso().equals("5")) ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (transac.getPlazo() >= 6 && transac.getPlazo() < 12 && cm.getIdCampo().equals("06")) { // Term between 6 and 12 months
                                pf.setValor((transac.getProceso().equals("4") || transac.getProceso().equals("5")) ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (transac.getPlazo() >= 12 && transac.getPlazo() < 18 && cm.getIdCampo().equals("07")) { // Term between 12 and 18 months
                                pf.setValor((transac.getProceso().equals("4") || transac.getProceso().equals("5")) ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (transac.getPlazo() >= 18 && cm.getIdCampo().equals("08")) { // Term major than 18 months
                                pf.setValor((transac.getProceso().equals("4") || transac.getProceso().equals("5")) ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (cm.getIdCampo().equals("09")) { // Paid with "Fondos recibidos por reinversion"
                                pf.setValor((transac.getProceso().equals("3") && transac.getTipTransaccion() == 7) ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (cm.getIdCampo().equals("10")) { // Paid with "Fondos recibidos por reinversion"
                                pf.setValor((transac.getProceso().equals("4") && transac.getTipTransaccion() == 7) ? determinaValorMonetario(transac.getProceso(),
                                        transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);
                            } else if (cm.getIdCampo().equals("11")) { // Paid with "Fondos recibidos por reinversion"
                                pf.setValor((transac.getProceso().equals("4") && transac.getTipTransaccion() == 7) ?
                                        transac.getGmf() : BigDecimal.ZERO);  /////////////////////////////////////////////////////////
                            } else if (cm.getIdCampo().equals("12")) { // Paid with "Fondos recibidos por reinversion"
                                pf.setValor((transac.getProceso().equals("5") && transac.getTipTransaccion() == 8) ?
                                        determinaValorMonetario(transac.getProceso(), transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO);

                            } else if (cm.getIdCampo().equals("13")) { // "Abono a Cuenta Corriente"
                                pf.setValor(getValueMonetary(transac, "3", "4", 1));
                            } else if (cm.getIdCampo().equals("14")) { // "GMF de los Intereses con abono a Cuenta Corriente"
                                pf.setValor((transac.getProceso().equals("4") && transac.getTipTransaccion() == 1) ?
                                        transac.getGmf() : BigDecimal.ZERO);
                            } else if (cm.getIdCampo().equals("15")) { // Abono a Cuenta de Ahorros"
                                pf.setValor(getValueMonetary(transac, "3", "4", 2));
                            } else if (cm.getIdCampo().equals("16")) { // GMF de los Intereses con abono a Cuenta de Ahorros"
                                pf.setValor((transac.getProceso().equals("4") && transac.getTipTransaccion() == 2) ?
                                        transac.getGmf() : BigDecimal.ZERO);
                            } else if (cm.getIdCampo().equals("17")) { // En cuenta contable"
//                                pf.setValor(getValueMonetary(transac, "3", "4", 6));
                                pf.setValor(determinaValorCuentaContable(transac.getProceso(), transac.getTipTransaccion(),
                                        transac.getValor(), cm.getIdCampo()));
                            } else if (cm.getIdCampo().equals("18")) { // GMF De Capital en cuenta contable"
//                                pf.setValor((transac.getProceso().equals("3") && transac.getTipTransaccion() == 6) ?
//                                        transac.getGmf() : BigDecimal.ZERO);
                                pf.setValor(determinaValorCuentaContable(transac.getProceso(), transac.getTipTransaccion(),
                                        transac.getGmf(), cm.getIdCampo()));
                            } else if (cm.getIdCampo().equals("19")) { // GMF De los intereses en cuenta contable"
                                pf.setValor((transac.getProceso().equals("4") && transac.getTipTransaccion() == 6) ?
                                        transac.getGmf() : BigDecimal.ZERO);
                            } else if (cm.getIdCampo().equals("20")) { // Disminución de capital a cuenta de ahorros
                                pf.setValor((transac.getProceso().equals("7") && transac.getTipTransaccion() == 2) ?
                                        transac.getValor() : BigDecimal.ZERO);
                            } else if (cm.getIdCampo().equals("21")) { // Disminución de capital a cuenta corriente
                                pf.setValor((transac.getProceso().equals("7") && transac.getTipTransaccion() == 1) ?
                                        transac.getValor() : BigDecimal.ZERO);
                            }
                    }

                    monetaryFields.add(pf);
                }
                if (numCdtCadi.equals(transac.getNumCdt())) {
                    plotFields.setCamposMonetarios(monetaryFields);
                    MonetaryFields monetaryFields1 = new MonetaryFields();
                    monetaryFields1.setMonetaryFieldList(monetaryFields);
                    // Generate plot
                    String plot = Plot.deceApdi(plotFields);

                    System.out.println("PLOT->" + plot);

                    // Plot
                    SalTramastcDownEntity tre = new SalTramastcDownEntity();
                    tre.setTrama(plot);
                    tre.setFecha(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()));

                    // Add to save list
                    tramastcDownEntities.add(tre);
                }
            }
            tramastcDownEntities.forEach(System.out::println);
            tramastc.saveAll(tramastcDownEntities);
            r.put(numCdtCadi.toString(), tramastcDownEntities);
            System.out.println("RETORNA  " + r);
            log.info("Los valores de las transacciones concuerdan con el CDT, guardado en la base de datos.");
        }
        System.out.println("RETORNA II  " + r);
        return r;
    }

    /**
     * Metodo encargado de retonar el valor a mapear en los campos monetarios del traductor
     *
     * @param proceso        campo de la tabla {@link HisTranpgEntity} donde se determina si es
     *                       capital codigo 3
     *                       intereses codigo 4
     *                       rte Fuente codigo 5
     * @param tipTransaccion campo de la tabla {@link HisTranpgEntity} donde se determina si es
     *                       renovacion codigo 7
     *                       Cuenta contable rte Fuente codigo 8
     * @param valor          Valor del campo a mapear
     * @return retorna valor correspondiente al proceso que se va a mapear en el campo monetario
     */
    private BigDecimal determinaValorMonetario(String proceso, Integer tipTransaccion, BigDecimal valor) {
        //Valor para Capital pago
        if (proceso.equals("3") && (tipTransaccion == 1
                || tipTransaccion == 2
                || tipTransaccion == 6
                || tipTransaccion == 7)) {
            return valor;
        }

        if (proceso.equals("4") && (tipTransaccion == 1
                || tipTransaccion == 2
                || tipTransaccion == 6
                || tipTransaccion == 7)) {
            return valor;
        }

        // Valor para Rte Fuente
        if (proceso.equals("5") && tipTransaccion == 8) {
            return valor;
        }

        if (proceso.equals("7") && (tipTransaccion == 1
                || tipTransaccion == 2
                || tipTransaccion == 6)) {
            return valor;
        }


        /* ESTOS TIPOS DE PAGOS PARA CAPITAL ESTARAN DISPONIBLES EN LA SOLICITUD MIGRACIÓN D300

        if (proceso.equals("3") && tipTransaccion == 3) {
            return valor;
        }

        if (proceso.equals("3") && tipTransaccion == 4) {
            return valor;
        }

        if (proceso.equals("3") && tipTransaccion == 5) {
            return valor;
        }*/


        //Si no encuentra valor mapea Cero en el traductor en el campo monetario correspondiente
        return (BigDecimal.ZERO);
    }

    private BigDecimal validateGmf(Long codProd, Integer gmfCapital, BigDecimal gmf) {
        gmfCapital = Optional.ofNullable(gmfCapital).orElse(0);
        if (codProd == 3001 || codProd == 3002) return BigDecimal.ZERO;
        else {
            if (gmfCapital.equals(1)) return gmf;
            else return BigDecimal.ZERO;
        }
    }

    private BigDecimal getValueMonetary(CruceHisCdtRenautDigDto transac, String proceso, String procesoDos, int tipoCta){
        return ((transac.getProceso().equals(proceso) || transac.getProceso().equals(procesoDos)) && transac.getTipTransaccion() == tipoCta) ?
                determinaValorMonetario(transac.getProceso(), transac.getTipTransaccion(), transac.getValor()) : BigDecimal.ZERO;
    }

    private BigDecimal determinaValorCuentaContable(String proceso, Integer tipTransaccion, BigDecimal valor, String campo) {
        if (proceso.equals("3") && tipTransaccion == 6 && (campo.equals("17") || campo.equals("18"))) {
            return valor;
        }

        if (proceso.equals("4") && tipTransaccion == 6 && campo.equals("17")) {
            return valor;
        }

        // Valor para Rte Fuente
        if (proceso.equals("7") && tipTransaccion == 6 && (campo.equals("17") || campo.equals("18"))) {
            return valor;
        }

        return BigDecimal.ZERO;
    }

}
