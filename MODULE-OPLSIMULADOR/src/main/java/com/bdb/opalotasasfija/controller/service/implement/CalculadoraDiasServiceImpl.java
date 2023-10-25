package com.bdb.opalotasasfija.controller.service.implement;

import com.bdb.opalotasasfija.controller.service.interfaces.CalculadoraDiasService;
import com.bdb.opalotasasfija.controller.service.interfaces.CalculoFechaVencimientoService;
import com.bdb.opalotasasfija.persistence.JSONSchema.request.calculadoradias.JSONRequestCalculadoraDias;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.calculadoradias.JSONResponseCalculadoraDias;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.calculadoradias.JSONResultCalculoraDias;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResponseStatus;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
@CommonsLog
public class CalculadoraDiasServiceImpl implements CalculadoraDiasService {

    @Autowired
    private CalculoFechaVencimientoService serviceCalcFecha;

    @Override
    public JSONResponseCalculadoraDias<JSONResponseStatus,JSONRequestCalculadoraDias,JSONResultCalculoraDias> calculoDias(JSONRequestCalculadoraDias request,
                                                                                                                          HttpServletRequest url) {
        JSONResponseCalculadoraDias<JSONResponseStatus,
                JSONRequestCalculadoraDias,
                JSONResultCalculoraDias> resultadoDias = new JSONResponseCalculadoraDias<>();
        switch (request.getBase()) {
            case 1:
                resultadoDias = base365(request, url);
                break;
            case 2:
                resultadoDias = base360(request, url);
                break;
            case 3:
                resultadoDias = baseReal(request, url);
                break;
            default:
                log.error("El tipo de BASE recibido es desconocido");
        }
        return resultadoDias;
    }

    /**
     *
     * @param fecha
     * @return
     */
    public LocalDate conversorString(String fecha){
        return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    /**
     *
     * @param request
     * @param url
     * @return
     */
    public JSONResponseCalculadoraDias<JSONResponseStatus,JSONRequestCalculadoraDias,JSONResultCalculoraDias> base360(JSONRequestCalculadoraDias request,
                                                                                                                      HttpServletRequest url) {
        log.info("------------------------------BASE EN DIAS (360)-----------------------------------------");
        LocalDate fechaApertura = conversorString(request.getFechaApertura());
        LocalDate fechaVencimiento = conversorString(request.getFechaVencimiento());
        long difereciaDias = ChronoUnit.DAYS.between(fechaApertura,fechaVencimiento);
        log.info("LA DIFERENCIA DE DIAS: "+difereciaDias);
        if(difereciaDias >= 30){
            long residuoDias = difereciaDias%30;
            long totalDias = difereciaDias - residuoDias;
            long meses = totalDias/30;
            LocalDate fechaTest = fechaApertura.plusMonths(meses);
            log.info("FECHA TEST: "+fechaTest);
            long diasFaltantes = ChronoUnit.DAYS.between(fechaTest,fechaVencimiento);
            log.info("LOS DIAS FALTANTES SON: "+diasFaltantes);
            int contador = serviceCalcFecha.verificarMeses31Dias(fechaTest,diasFaltantes);
            log.info("CONTADOR: "+contador);
            totalDias = (totalDias + diasFaltantes) - contador;
            log.info("LA FECHA VENCIMIENTO PARA BASE 360: "+fechaVencimiento);
            log.info("DIAS CORRESPONDIENTES: "+totalDias);
            long plazoMeses = serviceCalcFecha.convertirDiasMeses360(totalDias);
            return respuesta(request,plazoMeses, totalDias, url);
        }else{
            log.error("El plazo debe ser mayor o igual a 30 dias.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El plazo debe ser mayor o igual a 30 dias.");
        }
    }

    /**
     *
     * @param request
     * @param url
     * @return
     */
    public JSONResponseCalculadoraDias<JSONResponseStatus,JSONRequestCalculadoraDias,JSONResultCalculoraDias> base365(JSONRequestCalculadoraDias request,
                                                                                                                      HttpServletRequest url){
        log.info("------------------------------BASE EN DIAS (365)-----------------------------------------");
        LocalDate fechaApertura = conversorString(request.getFechaApertura());
        LocalDate fechaVencimiento = conversorString(request.getFechaVencimiento());
        long difereciaDias = ChronoUnit.DAYS.between(fechaApertura,fechaVencimiento);
        log.info("LA DIFERENCIA DE DIAS: "+difereciaDias);
        if(difereciaDias >= 30){
            int contador = serviceCalcFecha.verificarBisiesto(fechaApertura,fechaVencimiento);
            long totalDias = difereciaDias - contador;
            log.info("LA FECHA VENCIMIENTO PARA BASE 365: "+fechaVencimiento);
            log.info("DIAS CORRESPONDIENTES: "+totalDias);
            return respuesta(request,0, totalDias, url);
        }else{
            log.error("El plazo debe ser mayor o igual a 30 dias.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El plazo debe ser mayor o igual a 30 dias.");
        }
    }

    /**
     *
     * @param request
     * @param url
     * @return
     */
    public JSONResponseCalculadoraDias<JSONResponseStatus,JSONRequestCalculadoraDias,JSONResultCalculoraDias> baseReal(JSONRequestCalculadoraDias request,
                                                                                                                       HttpServletRequest url){
        log.info("------------------------------BASE EN DIAS (REAL)-----------------------------------------");
        LocalDate fechaApertura = conversorString(request.getFechaApertura());
        LocalDate fechaVencimiento = conversorString(request.getFechaVencimiento());
        long difereciaDias = ChronoUnit.DAYS.between(fechaApertura,fechaVencimiento);
        log.info("LA DIFERENCIA DE DIAS: "+difereciaDias);
        if(difereciaDias >= 30){
            log.info("LA FECHA VENCIMIENTO PARA BASE REAL: "+fechaVencimiento);
            log.info("DIAS CORRESPONDIENTES: "+ difereciaDias);
            return respuesta(request,0, difereciaDias, url);
        }else{
            log.error("El plazo debe ser mayor o igual a 30 dias.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El plazo debe ser mayor o igual a 30 dias.");
        }
    }

    /**
     *
     * @param request
     * @param plazoMeses
     * @param plazoDias
     * @param url
     * @return
     */
    public JSONResponseCalculadoraDias<JSONResponseStatus,JSONRequestCalculadoraDias,JSONResultCalculoraDias> respuesta(JSONRequestCalculadoraDias request,
                                                                                                                        long plazoMeses,
                                                                                                                        Long plazoDias,
                                                                                                                        HttpServletRequest url){
        JSONResponseStatus jsonResponseStatus = new JSONResponseStatus();
        jsonResponseStatus.setCode(HttpStatus.OK.value());
        jsonResponseStatus.setMessage(HttpStatus.OK.getReasonPhrase());

        JSONResultCalculoraDias jsonResultCalculoraDias = new JSONResultCalculoraDias();

        if (request.getBase() == 1 || request.getBase() == 3){
            long meses = periodicidadDias365Real(request.getFechaApertura(), conversorString(request.getFechaVencimiento()));
            jsonResultCalculoraDias.setPeriodicidad(serviceCalcFecha.periodicidadAdecuadasParaMeses(meses));
        }else {
            jsonResultCalculoraDias.setPeriodicidad(serviceCalcFecha.periodicidadAdecuadasParaMeses(plazoMeses));
        }
        jsonResultCalculoraDias.setPlazoenDias(plazoDias);

        return new JSONResponseCalculadoraDias<>(jsonResponseStatus,
                url.getRequestURL().toString(),
                request,
                jsonResultCalculoraDias);
    }

    /**
     *
     * @param fechaA
     * @param fechaVencimiento
     * @return
     */
    public Long periodicidadDias365Real(String fechaA , LocalDate fechaVencimiento){
        LocalDate fechaApertura = LocalDate.parse(fechaA, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        long cantidadDias = ChronoUnit.DAYS.between(fechaApertura, fechaVencimiento);
        long cantidadMeses = 0;
        if (cantidadDias >= 30){
            if (fechaApertura.getDayOfMonth() == fechaApertura.lengthOfMonth() &&
                    fechaVencimiento.getDayOfMonth() == fechaVencimiento.lengthOfMonth()){
                cantidadMeses = serviceCalcFecha.convertirDiasaMeses365(fechaApertura, fechaVencimiento);
            }else if (fechaApertura.getDayOfMonth() == fechaVencimiento.getDayOfMonth()){
                cantidadMeses = serviceCalcFecha.convertirDiasaMeses365(fechaApertura, fechaVencimiento);
            }else{
                log.info("PERIODICIDAD AL PLAZO");
                cantidadMeses =  1;
            }
        }
        return cantidadMeses;
    }
}
