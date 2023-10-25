package com.bdb.opalotasasfija.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.TipbaseParDownEntity;
import com.bdb.opaloshare.persistence.model.jsonschema.fechaInicioPeriodo.JSONRequestFechaInicioPeriodo;
import com.bdb.opaloshare.persistence.repository.RepositoryTipBase;
import com.bdb.opalotasasfija.controller.service.interfaces.CalculoFechaInicioPeriodoService;
import com.bdb.opalotasasfija.controller.service.interfaces.CalculoFechaVencimientoService;
import com.bdb.opalotasasfija.persistence.JSONSchema.model.ValidacionFechaInicial;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.calculadoradias.JSONResponseCalculadoraDias;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.calculoFechaInicioPeriodo.JSONResultCalculoFechaInicioPeriodo;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResponseStatus;
import lombok.extern.apachecommons.CommonsLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@CommonsLog
public class CalculoFechaInicioPeriodoImpl implements CalculoFechaInicioPeriodoService {

    @Autowired
    private CalculoFechaVencimientoService serviceCalcFecha;
    @Autowired
    private RepositoryTipBase repositoryTipBase;

    private Logger logger = LoggerFactory.getLogger(CalculoFechaVencimientoServiceImpl.class);


    @Override
    public JSONResponseCalculadoraDias<JSONResponseStatus, JSONRequestFechaInicioPeriodo, JSONResultCalculoFechaInicioPeriodo> calculoFechaInicioPeriodo(JSONRequestFechaInicioPeriodo request, HttpServletRequest url) {

        JSONResponseCalculadoraDias<JSONResponseStatus, JSONRequestFechaInicioPeriodo, JSONResultCalculoFechaInicioPeriodo> resultado = new JSONResponseCalculadoraDias<>();

        switch (request.getParametros().getTipoPlazo()) {
            case 1:
                resultado = calculoDias(request, url);
                break;
            case 2:
                resultado = calculoMeses(request, url);
                break;
            default:
                logger.error("El tipo de Plazo recibido es desconocido");
        }
        return resultado;
    }

    public JSONResponseCalculadoraDias<JSONResponseStatus, JSONRequestFechaInicioPeriodo, JSONResultCalculoFechaInicioPeriodo> calculoDias(JSONRequestFechaInicioPeriodo request, HttpServletRequest url) {

        JSONResponseCalculadoraDias<JSONResponseStatus, JSONRequestFechaInicioPeriodo, JSONResultCalculoFechaInicioPeriodo> resultadoDias = new JSONResponseCalculadoraDias<>();

        switch (baseDias(request.getParametros().getBase())) {
            case "360":
                resultadoDias = fechaEnDias360(request, url);
                break;
            case "365":
                resultadoDias = fechaEnDias365(request, url);
                break;
            case "Real":
                resultadoDias = fechaEnDiasReal(request, url);
                break;
            default:
                logger.error("El tipo de BASE recibido es desconocido");
        }
        return  resultadoDias;
    }


    public JSONResponseCalculadoraDias<JSONResponseStatus, JSONRequestFechaInicioPeriodo, JSONResultCalculoFechaInicioPeriodo> calculoMeses(JSONRequestFechaInicioPeriodo request, HttpServletRequest url) {

        JSONResponseCalculadoraDias<JSONResponseStatus, JSONRequestFechaInicioPeriodo, JSONResultCalculoFechaInicioPeriodo> resultadoMeses = new JSONResponseCalculadoraDias<>();

        switch (baseDias(request.getParametros().getBase())) {
            case "360":
                resultadoMeses = fechaEnMeses360(request, url);
                break;
            case "365":
                resultadoMeses = fechaEnMeses365(request, url);
                break;
            case "Real":
                resultadoMeses = fechaEnMesesReal(request, url);
                break;
            default:
                logger.error("El tipo de BASE recibido es desconocido");
        }
        return resultadoMeses;
    }


    public JSONResponseCalculadoraDias<JSONResponseStatus, JSONRequestFechaInicioPeriodo,
            JSONResultCalculoFechaInicioPeriodo> fechaEnMeses360(JSONRequestFechaInicioPeriodo request, HttpServletRequest url) {

        System.out.println("------------------------------BASE 360---------------------------------------------------");

        ValidacionFechaInicial validacionFechaInicial = validarFechaInicial(request);
        Integer diasDiferencia = validacionFechaInicial.getDiasDiferencia();
        LocalDate fechaVencimiento = validacionFechaInicial.getFecha();
        Long mesesPeriodicidad = request.getParametros().getPeriodicidad().equals(0) ? request.getParametros().getPlazo(): request.getParametros().getPeriodicidad();
        System.out.println("EL MES ES: " + fechaVencimiento.getMonthValue());
        LocalDate nuevaFecha = fechaVencimiento.minusMonths(mesesPeriodicidad).plusDays(diasDiferencia);
        System.out.println("NUEVA FECHA: " + nuevaFecha);
        Period diferencia = Period.between(nuevaFecha,fechaVencimiento);
        System.out.println("DIFERENCIA ES: " + diferencia.toTotalMonths());
        return respuesta(request,nuevaFecha,url);
    }

    public JSONResponseCalculadoraDias<JSONResponseStatus, JSONRequestFechaInicioPeriodo,
            JSONResultCalculoFechaInicioPeriodo> fechaEnMeses365(JSONRequestFechaInicioPeriodo request, HttpServletRequest url) {

        System.out.println("---------------------------------BASE 365------------------------------------------------");

        ValidacionFechaInicial validacionFechaInicial = validarFechaInicial(request);
        LocalDate fechaVencimiento = validacionFechaInicial.getFecha();
        Long mesesPeriodicidad = request.getParametros().getPeriodicidad().equals(0) ? request.getParametros().getPlazo(): request.getParametros().getPeriodicidad();
        System.out.println("EL MES ES: " + fechaVencimiento.getMonthValue() + " dia: "+fechaVencimiento.getDayOfMonth() +" ULTIMO DIA: "+fechaVencimiento.lengthOfMonth());

        LocalDate nuevaFecha = fechaVencimiento.minusMonths(mesesPeriodicidad);
        System.out.println("FECHA INGRESADA: " + fechaVencimiento);
        System.out.println("NUEVA FECHA: " + nuevaFecha);

        Period diferencia = Period.between(nuevaFecha,fechaVencimiento);
        System.out.println("DIFERENCIA MESES: "+diferencia.toTotalMonths());

        System.out.println("EL VALOR DE FEBRERO ES: "+serviceCalcFecha.corregirFebrero(nuevaFecha,fechaVencimiento));
        int contador = serviceCalcFecha.corregirFebrero(nuevaFecha,fechaVencimiento) * 2 ;
        nuevaFecha = nuevaFecha.minusDays(contador);
        System.out.println("FECHA DE VENCIMIENTO: " + nuevaFecha);
        return respuesta(request,nuevaFecha,url);
    }

    public JSONResponseCalculadoraDias<JSONResponseStatus, JSONRequestFechaInicioPeriodo,
            JSONResultCalculoFechaInicioPeriodo> fechaEnMesesReal(JSONRequestFechaInicioPeriodo request, HttpServletRequest url) {

        System.out.println("---------------------------------BASE REAL------------------------------------------------");

        ValidacionFechaInicial validacionFechaInicial = validarFechaInicial(request);
        LocalDate fechaVencimiento = validacionFechaInicial.getFecha();
        Long mesesPeriodicidad = request.getParametros().getPeriodicidad().equals(0) ? request.getParametros().getPlazo(): request.getParametros().getPeriodicidad();
        System.out.println("EL MES ES: " + fechaVencimiento.getMonthValue() + " dia: "+fechaVencimiento.getDayOfMonth() +" ULTIMO DIA: "+fechaVencimiento.lengthOfMonth());

        LocalDate nuevaFecha = fechaVencimiento.minusMonths(mesesPeriodicidad);
        System.out.println("FECHA INGRESADA: " + fechaVencimiento);
        System.out.println("NUEVA FECHA: " + nuevaFecha);

        System.out.println("EL VALOR DE FEBRERO ES: "+serviceCalcFecha.corregirFebrero(fechaVencimiento, nuevaFecha));
        int contador = serviceCalcFecha.corregirFebrero(nuevaFecha,fechaVencimiento) * 2 ;
        nuevaFecha = nuevaFecha.minusDays(contador);

        int adicionYearBisiesto = serviceCalcFecha.verificarBisiesto(nuevaFecha,fechaVencimiento);
        System.out.println(adicionYearBisiesto);
        nuevaFecha = nuevaFecha.plusDays(adicionYearBisiesto);
        System.out.println("FECHA DE VENCIMIENTO: " + nuevaFecha);
        return respuesta(request,nuevaFecha,url);
    }

    public JSONResponseCalculadoraDias<JSONResponseStatus, JSONRequestFechaInicioPeriodo,
            JSONResultCalculoFechaInicioPeriodo> fechaEnDias360(JSONRequestFechaInicioPeriodo request, HttpServletRequest url) {

        log.info("------------------------------BASE EN DIAS (360)-----------------------------------------");
        if(request.getParametros().getPeriodicidad() >= 0){

            ValidacionFechaInicial validacionFechaInicial = validarFechaInicial(request);
            Integer diferencia = validacionFechaInicial.getDiasDiferencia();
            LocalDate fechaVencimiento = validacionFechaInicial.getFecha();
            Long mesesPeriodicidad = request.getParametros().getPeriodicidad().equals(0) ? request.getParametros().getPlazo()/30: request.getParametros().getPeriodicidad();

            Long mesesSolicitados = mesesPeriodicidad;
            Long diasSobrantes = request.getParametros().getPeriodicidad().equals(0) ? request.getParametros().getPlazo()%30L : 30*request.getParametros().getPeriodicidad()%30L;
            System.out.println("DIAS SOBRANTES: "+diasSobrantes);
            LocalDate fechaPreApertura = fechaVencimiento.minusMonths(mesesSolicitados);
            int contador = serviceCalcFecha.verificarMeses31Dias(fechaVencimiento,new Long(0));
            System.out.println("verificarMeses31Dias: "+contador);
            LocalDate fechaApertura = fechaPreApertura.minusDays(diasSobrantes+contador).plusDays(diferencia);
            System.out.println("LA FECHA APERTURA PARA BASE 360: "+fechaApertura);
            request.getParametros().setPlazo(serviceCalcFecha.convertirDiasMeses360(request.getParametros().getPlazo()));
            return respuesta(request,fechaApertura,url);
        }else{
            System.out.println("El plazo debe ser mayor o igual a 30 dias.");
            return null;
        }
    }

    public JSONResponseCalculadoraDias<JSONResponseStatus, JSONRequestFechaInicioPeriodo,
            JSONResultCalculoFechaInicioPeriodo> fechaEnDias365(JSONRequestFechaInicioPeriodo request, HttpServletRequest url){

        System.out.println("------------------------------BASE EN DIAS (365)-----------------------------------------");
        if(request.getParametros().getPeriodicidad() >= 0){

            ValidacionFechaInicial validacionFechaInicial = validarFechaInicial(request);
            LocalDate fechaVencimiento = validacionFechaInicial.getFecha();
            Long diasPeriodicidad = request.getParametros().getPeriodicidad().equals(0) ? request.getParametros().getPlazo(): request.getParametros().getPeriodicidad()*30;

            LocalDate nuevaFecha = fechaVencimiento.minusDays(diasPeriodicidad);
            int adicionYearBisiesto = serviceCalcFecha.verificarBisiesto(nuevaFecha,fechaVencimiento);
            nuevaFecha = nuevaFecha.minusDays(adicionYearBisiesto);
            System.out.println("FECHA DE VENCIMIENTO: " + nuevaFecha);
            return respuesta(request,nuevaFecha,url);
        }else{
            System.out.println("El plazo debe ser mayor o igual a 30 dias.");
            return null;
        }
    }

    public JSONResponseCalculadoraDias<JSONResponseStatus, JSONRequestFechaInicioPeriodo,
            JSONResultCalculoFechaInicioPeriodo> fechaEnDiasReal(JSONRequestFechaInicioPeriodo request, HttpServletRequest url){

        System.out.println("------------------------------BASE REAL--------------------------------------------------");
        if(request.getParametros().getPeriodicidad() >= 0){

            ValidacionFechaInicial validacionFechaInicial = validarFechaInicial(request);
            LocalDate fechaVencimiento = validacionFechaInicial.getFecha();
            Long mesesPeriodicidad = request.getParametros().getPeriodicidad().equals(0) ? request.getParametros().getPlazo(): request.getParametros().getPeriodicidad()*30;

            System.out.println("EL MES ES: " + fechaVencimiento.getMonthValue());
            LocalDate nuevaFecha = fechaVencimiento.minusDays(mesesPeriodicidad);
            System.out.println("FECHA DE VENCIMIENTO con el dia Bisiesto: " + nuevaFecha);
            long p2 = DAYS.between(nuevaFecha,fechaVencimiento);
            System.out.println("DIFERENCIA DIAS: " + p2);
            return respuesta(request,nuevaFecha,url);
        }else{
            System.out.println("El plazo debe ser mayor o igual a 30 dias.");
            return null;
        }
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
     * @param fecha
     * @param url
     * @return
     */
    public JSONResponseCalculadoraDias<JSONResponseStatus, JSONRequestFechaInicioPeriodo, JSONResultCalculoFechaInicioPeriodo> respuesta(JSONRequestFechaInicioPeriodo request,
                                                                                                                                                LocalDate fecha,
                                                                                                                                                HttpServletRequest url){
        JSONResponseStatus jsonResponseStatus = new JSONResponseStatus();
        jsonResponseStatus.setCode(HttpStatus.OK.value());
        jsonResponseStatus.setMessage(HttpStatus.OK.getReasonPhrase());

        JSONResultCalculoFechaInicioPeriodo jsonResultCalculadoraFechaTasas = new JSONResultCalculoFechaInicioPeriodo();

        jsonResultCalculadoraFechaTasas.setFechaInicio(fecha);
        return new JSONResponseCalculadoraDias<>(jsonResponseStatus,
                url.getRequestURL().toString(),
                request,
                jsonResultCalculadoraFechaTasas);
    }

    /**
     * Valida que la base sea correcta y devuelve la descripción de la base.
     *
     * @param base El codigo de la base, enviado por el request.
     * @return La descripcion de la base.
     */

    public String baseDias(int base) {
        Optional<TipbaseParDownEntity> tipbaseParDownEntity = repositoryTipBase.findById(base);
        return tipbaseParDownEntity
                .map(parDownEntity -> parDownEntity.getDescBase())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                        "El tipo de BASE no ha sido encontrado."));
    }

    private LocalDate validarMeses360(LocalDate fechaProxPago, Long periodicidadMeses, Long factor, Integer diaVencimiento) {

        LocalDate fechaApertura;
        if (fechaProxPago.minusMonths(periodicidadMeses+factor).lengthOfMonth() < diaVencimiento ) {
            if (fechaProxPago.minusMonths(periodicidadMeses+factor).lengthOfMonth() == 28 || fechaProxPago.minusMonths(periodicidadMeses+factor).lengthOfMonth() == 29) {
                fechaApertura = fechaProxPago.minusMonths(periodicidadMeses + factor).withDayOfMonth(28);
            } else {
                fechaApertura = fechaProxPago.minusMonths(periodicidadMeses + factor).withDayOfMonth(diaVencimiento-1);
            }
        }else {
            fechaApertura = fechaProxPago.minusMonths(periodicidadMeses + factor).withDayOfMonth(diaVencimiento);
        }
        return fechaApertura;
    }

    private ValidacionFechaInicial validarFechaInicial(JSONRequestFechaInicioPeriodo request) {

        ValidacionFechaInicial validacionFechaInicial = new ValidacionFechaInicial();
        LocalDate fechaVencimiento = LocalDate.parse(request.getParametros().getFechaVencimiento(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate fechaProxPago = LocalDate.parse(request.getParametros().getFechaProxPago(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        Integer diaVencimiento = fechaVencimiento.getDayOfMonth();
        Integer diaProxPago = fechaProxPago.getDayOfMonth();
        Long mesesPeriodicidad = request.getParametros().getPeriodicidad().equals(0) ? request.getParametros().getPlazo(): request.getParametros().getPeriodicidad();
        Integer diferencia = 0;

        if(diaProxPago < diaVencimiento){
            Integer diasMes = LocalDate.parse(request.getParametros().getFechaProxPago(), DateTimeFormatter.ofPattern("yyyy/MM/dd")).minusMonths(1).lengthOfMonth();
            if(diasMes < diaVencimiento){
                System.out.println("erroress");
                Integer diasMes2 = LocalDate.parse(request.getParametros().getFechaProxPago(), DateTimeFormatter.ofPattern("yyyy/MM/dd")).minusMonths(1+mesesPeriodicidad).lengthOfMonth();
                if (diasMes2.equals(28) || diasMes2.equals(29) ) {
                    diferencia = 0;
                } else {
                    diferencia = diasMes2 - diasMes;
                }
                fechaVencimiento = LocalDate.parse(request.getParametros().getFechaProxPago(), DateTimeFormatter.ofPattern("yyyy/MM/dd")).minusMonths(1).withDayOfMonth(diasMes);
            } else {
                fechaVencimiento = LocalDate.parse(request.getParametros().getFechaProxPago(), DateTimeFormatter.ofPattern("yyyy/MM/dd")).minusMonths(1).withDayOfMonth(diaVencimiento);
            }
        } else  if (diaProxPago > diaVencimiento){
            fechaVencimiento = LocalDate.parse(request.getParametros().getFechaProxPago(), DateTimeFormatter.ofPattern("yyyy/MM/dd")).withDayOfMonth(diaVencimiento);
        } else {
            fechaVencimiento = LocalDate.parse(request.getParametros().getFechaProxPago(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        }

        validacionFechaInicial.setFecha(fechaVencimiento);
        validacionFechaInicial.setDiasDiferencia(diferencia);

        return validacionFechaInicial;
    }

}
