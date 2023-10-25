package com.bdb.opalotasasfija.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.TipPeriodParDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryTipPeriodicidad;
import com.bdb.opalotasasfija.controller.service.interfaces.CalculoFechaVencimientoService;
import com.bdb.opalotasasfija.persistence.JSONSchema.ServiceCalculoFechaVen.JSONCalculoFechaVencimiento;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class CalculoFechaVencimientoServiceImpl implements CalculoFechaVencimientoService {

    @Autowired
    private RepositoryTipPeriodicidad repositoryTipPeriodicidad;

    private Logger logger = LoggerFactory.getLogger(CalculoFechaVencimientoServiceImpl.class);

    @Override
    public String calculoFechaVencimiento(JSONCalculoFechaVencimiento request) {
        JSONObject jsonGeneral = new JSONObject();
        switch (request.getTipoPlazo()) {
            case 1:
                jsonGeneral = calculoDias(request);
                break;
            case 2:
                jsonGeneral = calculoMeses(request);
                System.out.println(jsonGeneral.toString());
                break;
            default:
                logger.error("El tipo de Plazo recibido es desconocido");
        }
        return jsonGeneral.toString();
    }

    public JSONObject calculoMeses(JSONCalculoFechaVencimiento request) {
        JSONObject resultadoMeses = new JSONObject();
        switch (request.getBase()) {
            case 1:
                resultadoMeses = fechaEnMeses360(request);
                break;
            case 2:
                resultadoMeses = fechaEnMeses365(request);
                break;
            case 3:
                resultadoMeses = fechaEnMesesReal(request);
                break;
            default:
                logger.error("El tipo de BASE recibido es desconocido");
        }
        return resultadoMeses;
    }

    public JSONObject fechaEnMeses360(JSONCalculoFechaVencimiento request) {
        System.out.println("------------------------------BASE 360---------------------------------------------------");
        LocalDate fecha = LocalDate.parse(request.getFechaApertura(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println("EL MES ES: " + fecha.getMonthValue());
        LocalDate nuevaFecha = fecha.plusMonths(request.getPlazo());
        System.out.println("NUEVA FECHA: " + nuevaFecha);
        Period diferencia = Period.between(fecha, nuevaFecha);
        System.out.println("DIFERENCIA ES: " + diferencia.toTotalMonths());
        return respuesta(request,nuevaFecha);
    }

    public JSONObject fechaEnMeses365(JSONCalculoFechaVencimiento request) {
        System.out.println("---------------------------------BASE 365------------------------------------------------");

        LocalDate fecha = LocalDate.parse(request.getFechaApertura(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println("EL MES ES: " + fecha.getMonthValue() + " dia: "+fecha.getDayOfMonth() +" ULTIMO DIA: "+fecha.lengthOfMonth());

        LocalDate nuevaFecha = fecha.plusMonths(request.getPlazo());
        System.out.println("FECHA INGRESADA: " + fecha);
        System.out.println("NUEVA FECHA: " + nuevaFecha);

        Period diferencia = Period.between(fecha, nuevaFecha);
        System.out.println("DIFERENCIA MESES: "+diferencia.toTotalMonths());

        System.out.println("EL VALOR DE FEBRERO ES: "+corregirFebrero(fecha, nuevaFecha));
        int contador = corregirFebrero(fecha, nuevaFecha) * 2 ;
        nuevaFecha = nuevaFecha.plusDays(contador);
        System.out.println("FECHA DE VENCIMIENTO: " + nuevaFecha);
        return respuesta(request,nuevaFecha);
    }

    public JSONObject fechaEnMesesReal(JSONCalculoFechaVencimiento request) {
        System.out.println("---------------------------------BASE REAL------------------------------------------------");

        LocalDate fecha = LocalDate.parse(request.getFechaApertura(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println("EL MES ES: " + fecha.getMonthValue() + " dia: "+fecha.getDayOfMonth() +" ULTIMO DIA: "+fecha.lengthOfMonth());

        LocalDate nuevaFecha = fecha.plusMonths(request.getPlazo());
        System.out.println("FECHA INGRESADA: " + fecha);
        System.out.println("NUEVA FECHA: " + nuevaFecha);

        System.out.println("EL VALOR DE FEBRERO ES: "+corregirFebrero(fecha, nuevaFecha));
        int contador = corregirFebrero(fecha, nuevaFecha) * 2 ;
        nuevaFecha = nuevaFecha.plusDays(contador);

        int adicionYearBisiesto = verificarBisiesto(fecha , nuevaFecha);
        System.out.println(adicionYearBisiesto);
        nuevaFecha = nuevaFecha.minusDays(adicionYearBisiesto);
        System.out.println("FECHA DE VENCIMIENTO: " + nuevaFecha);
        return respuesta(request,nuevaFecha);
    }

    public JSONObject calculoDias(JSONCalculoFechaVencimiento request) {
        JSONObject resultadoDias = new JSONObject();
        switch (request.getBase()) {
            case 1:
                resultadoDias = fechaEnDias360(request);
                break;
            case 2:
                resultadoDias = fechaEnDias365(request);
                break;
            case 3:
                resultadoDias = fechaEnDiasReal(request);
                break;
            default:
                logger.error("El tipo de BASE recibido es desconocido");
        }
        return  resultadoDias;
    }

    public JSONObject fechaEnDias360(JSONCalculoFechaVencimiento request) {
        System.out.println("------------------------------BASE EN DIAS (360)-----------------------------------------");
        if(request.getPlazo() >= 30){
            LocalDate fecha = LocalDate.parse(request.getFechaApertura(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            Long mesesSolicitados = request.getPlazo()/30;
            Long diasSobrantes = request.getPlazo()%30;
            System.out.println("DIAS SOBRANTES: "+diasSobrantes);
            LocalDate fechaPreVencimiento = fecha.plusMonths(mesesSolicitados);
            int contador = verificarMeses31Dias(fechaPreVencimiento,diasSobrantes);
            LocalDate fechaVencimiento = fechaPreVencimiento.plusDays(diasSobrantes+contador);
            System.out.println("LA FECHA VENCIMIENTO PARA BASE 360: "+fechaVencimiento);
            request.setPlazo(convertirDiasMeses360(request.getPlazo()));
            return respuesta(request,fechaVencimiento);
        }else{
            System.out.println("El plazo debe ser mayor o igual a 30 dias.");
            return null;
        }
    }

    public JSONObject fechaEnDias365(JSONCalculoFechaVencimiento request){
        System.out.println("------------------------------BASE EN DIAS (365)-----------------------------------------");
        if(request.getPlazo() >= 30){
            LocalDate fecha = LocalDate.parse(request.getFechaApertura(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            LocalDate nuevaFecha = fecha.plusDays(request.getPlazo());
            int adicionYearBisiesto = verificarBisiesto(fecha , nuevaFecha);
            nuevaFecha = nuevaFecha.plusDays(adicionYearBisiesto);
            System.out.println("FECHA DE VENCIMIENTO: " + nuevaFecha);
            return respuesta(request,nuevaFecha);
        }else{
            System.out.println("El plazo debe ser mayor o igual a 30 dias.");
            return null;
        }
    }

    public JSONObject fechaEnDiasReal(JSONCalculoFechaVencimiento request){
        System.out.println("------------------------------BASE REAL--------------------------------------------------");
        if(request.getPlazo() >= 30){
            LocalDate fecha = LocalDate.parse(request.getFechaApertura(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            System.out.println("EL MES ES: " + fecha.getMonthValue());
            LocalDate nuevaFecha = fecha.plusDays(request.getPlazo());
            System.out.println("FECHA DE VENCIMIENTO con el dia Bisiesto: " + nuevaFecha);
            long p2 = ChronoUnit.DAYS.between(fecha, nuevaFecha);
            System.out.println("DIFERENCIA DIAS: " + p2);
            return respuesta(request,nuevaFecha);
        }else{
            System.out.println("El plazo debe ser mayor o igual a 30 dias.");
            return null;
        }
    }

    //CALCULO DE PERIODICIDADES
    public String nombrePeriodicidad(Integer clave){
        Map<Integer,String> periodicidad = new HashMap<>();
        List<TipPeriodParDownEntity> periodicidades = repositoryTipPeriodicidad.findAll();
        periodicidades.forEach(item -> periodicidad.put(item.getTipPeriodicidad(),item.getDescPeriodicidad()));
        /*periodicidad.put(0,"Al vencimiento");
        periodicidad.put(1,"Mensual");
        periodicidad.put(2,"Bimensual");
        periodicidad.put(3,"Trimestral");
        periodicidad.put(4,"Cuatrimestral");
        periodicidad.put(5,"5 Meses");
        periodicidad.put(6,"6 Meses");
        periodicidad.put(7,"7 Meses");
        periodicidad.put(8,"8 Meses");
        periodicidad.put(9,"9 Meses");
        periodicidad.put(10,"10 Meses");
        periodicidad.put(11,"11 Meses");*/
        return periodicidad.get(clave);
    }

    public List<TipPeriodParDownEntity> periodicidadAdecuadasParaMeses(Long plazo) {
        List<TipPeriodParDownEntity> periodicidad = new ArrayList<>();
        List<TipPeriodParDownEntity> tipPeriodicidad = repositoryTipPeriodicidad.findAll();
        for (int i = 1; i < tipPeriodicidad.size(); i++) {
            if (plazo % tipPeriodicidad.get(i).getTipPeriodicidad() == 0) {
                if (plazo >= (tipPeriodicidad.get(i).getTipPeriodicidad()*2)) {
                    TipPeriodParDownEntity periodo = new TipPeriodParDownEntity();
                    System.out.println("VALOR DE I: "+tipPeriodicidad.get(i).getTipPeriodicidad());
                    periodo.setTipPeriodicidad(tipPeriodicidad.get(i).getTipPeriodicidad());
                    periodo.setDescPeriodicidad(nombrePeriodicidad(tipPeriodicidad.get(i).getTipPeriodicidad()));
                    periodicidad.add(periodo);
                }
            }
        }

        TipPeriodParDownEntity periodo = new TipPeriodParDownEntity();
        periodo.setTipPeriodicidad(0);
        periodo.setDescPeriodicidad(nombrePeriodicidad(0));
        periodicidad.add(periodicidad.size(),periodo);

        /*periodicidad.add(String.valueOf(0));
        periodicidad.add(nombrePeriodicidad(0));*/
        periodicidad.stream().forEach(data -> System.out.println(data));
        return periodicidad;
    }

    public Long periodicidadDias365Real(String fechaA , LocalDate fechaVencimiento){
        LocalDate fechaApertura = LocalDate.parse(fechaA, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        long cantidadDias = ChronoUnit.DAYS.between(fechaApertura, fechaVencimiento);
        long cantidadMeses = 0;
        if (cantidadDias >= 30){
            if (fechaApertura.getDayOfMonth() == fechaApertura.lengthOfMonth() &&
                    fechaVencimiento.getDayOfMonth() == fechaVencimiento.lengthOfMonth()){
                cantidadMeses = convertirDiasaMeses365(fechaApertura, fechaVencimiento);
            }else if (fechaApertura.getDayOfMonth() == fechaVencimiento.getDayOfMonth()){
                cantidadMeses = convertirDiasaMeses365(fechaApertura, fechaVencimiento);
            }else{
                System.out.println("PERIODICIDAD AL PLAZO");
                cantidadMeses =  1;
            }
        }
        return cantidadMeses;
    }

    public Long convertirDiasaMeses365(LocalDate fechaApertura , LocalDate fechaVencimiento){
        Period periodo = Period.between(fechaApertura,fechaVencimiento);
        System.out.println("MESES: "+periodo.toTotalMonths());
        return periodo.toTotalMonths();
    }

    public long convertirDiasMeses360(Long plazo){
        double valor = plazo.floatValue()/30;
        if(valor%1 > 0 ){
            return 1;
        }else{
            return plazo/30;
        }
    }

    //CONSTRUCCION DEL JSON DE RESPUESTA
    public JSONObject respuesta(JSONCalculoFechaVencimiento request , LocalDate fechaVencimiento){
        JSONObject jsonGeneral = new JSONObject();
        Map<String,Object> resultado = new HashMap<>();
        if (request.getTipoPlazo() == 1 && (request.getBase() == 2 || request.getBase() == 3)){
            long meses = periodicidadDias365Real(request.getFechaApertura(), fechaVencimiento);
            resultado.put("periodicidad",periodicidadAdecuadasParaMeses(meses));
        }else {
            resultado.put("periodicidad",periodicidadAdecuadasParaMeses(request.getPlazo()));
        }
        resultado.put("fechaVencimiento",fechaVencimiento.toString());
        jsonGeneral.put("resultado",resultado);

        return jsonGeneral;
    }

    public int verificarBisiesto(LocalDate fechaApertura, LocalDate fechaVencimiento) {
        Integer yearApertura = fechaApertura.getYear();
        Integer yearVencimiento = fechaVencimiento.getYear();
        System.out.println(yearApertura + " - " + yearVencimiento);
        int contador = 0;
        for (int i = yearApertura; i <= yearVencimiento; i++) {
            ChronoLocalDate febrero = LocalDate.parse(i + "/02/29", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            //System.out.println("FECHA DE FEBRERO: "+febrero);
            if ( (febrero.isAfter(fechaApertura) || febrero.equals(fechaApertura)) &&
                    (febrero.isBefore(fechaVencimiento) || febrero.equals(fechaVencimiento))) {
                //System.out.println("ENTRO A LA CONDICION");
                if (febrero.isLeapYear()){
                    System.out.println("entroooo es verdadero");
                    contador++;
                }
            }
        }
        System.out.println("CONTADOR: "+contador);
        return contador;
    }

    public int corregirFebrero(LocalDate fechaApertura, LocalDate fechaVencimiento){
        Integer yearApertura = fechaApertura.getYear();
        Integer yearVencimiento = fechaVencimiento.getYear();
        System.out.println(yearApertura + " - " + yearVencimiento);
        int contador = 0;
        for (int i = yearApertura; i <= yearVencimiento; i++) {
            ChronoLocalDate febreroInicial = LocalDate.parse(i + "/02/01", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            ChronoLocalDate febreroFinal = LocalDate.parse(i + "/02/28", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            if ( (febreroInicial.isAfter(fechaApertura) || febreroInicial.equals(fechaApertura))
                    && (febreroFinal.isBefore(fechaVencimiento) || febreroFinal.equals(fechaVencimiento))){
                contador++;
            }
        }
        return contador;
    }

    public Integer verificarMeses31Dias(LocalDate fecha,Long diasAdicionales){
        LocalDate fechaAdicional = fecha.plusDays(diasAdicionales);
        int saber = fecha.lengthOfMonth() == 31 ? 1 : 0;
        if(saber == 1){
            String mes = fecha.getMonthValue() <= 9 ? "0"+fecha.getMonthValue() : String.valueOf(fecha.getMonthValue());
            ChronoLocalDate fechaFinal = LocalDate.parse(fecha.getYear() + "/"+mes+ "/"+31, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            System.out.println("FECHA NUEVA: " + fechaFinal);
            if (fechaAdicional.isAfter(fechaFinal)){
                return 1;
            }else{
                return 0;
            }
        }else{
            return 0;
        }
    }

}
