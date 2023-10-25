package com.bdb.opalotasasfija.controller.service.implement;

import com.bdb.opalotasasfija.controller.service.interfaces.CalculoDiasCDTService;
import com.bdb.opalotasasfija.controller.service.interfaces.CalculoFechaVencimientoService;
import com.bdb.opalotasasfija.persistence.JSONSchema.ServiceCalculoFechaVen.JSONCalculoDiasCDT;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Service
public class CalculoDiasCDTServiceImpl implements CalculoDiasCDTService {

    @Autowired
    private CalculoFechaVencimientoService serviceCalcFecha;

    private Logger logger = LoggerFactory.getLogger(CalculoDiasCDTServiceImpl.class);

    @Override
    public String calculoFechaVencimiento(JSONCalculoDiasCDT request) {
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
        return resultadoDias.toString();
    }

    public LocalDate conversorString(String fecha){
        return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    public JSONObject fechaEnDias360(JSONCalculoDiasCDT request) {
        System.out.println("------------------------------BASE EN DIAS (360)-----------------------------------------");
        LocalDate fechaApertura = conversorString(request.getFechaApertura());
        LocalDate fechaVencimiento = conversorString(request.getFechaVencimiento());
        long difereciaDias = ChronoUnit.DAYS.between(fechaApertura,fechaVencimiento);
        System.out.println("LA DIFERENCIA DE DIAS: "+difereciaDias);
        if(difereciaDias >= 30){
            Long residuoDias = difereciaDias%30;
            long totalDias = difereciaDias - residuoDias;
            Long meses = totalDias/30;
            LocalDate fechaTest = fechaApertura.plusMonths(meses);
            System.out.println("FECHA TEST: "+fechaTest);
            long diasFaltantes = ChronoUnit.DAYS.between(fechaTest,fechaVencimiento);
            System.out.println("LOS DIAS FALTANTES SON: "+diasFaltantes);
            int contador = serviceCalcFecha.verificarMeses31Dias(fechaTest,diasFaltantes);
            System.out.println("CONTADOR: "+contador);
            totalDias = (totalDias + diasFaltantes) - contador;
            System.out.println("LA FECHA VENCIMIENTO PARA BASE 360: "+fechaVencimiento);
            System.out.println("DIAS CORRESPONDIENTES: "+totalDias);
            long plazoMeses = serviceCalcFecha.convertirDiasMeses360(totalDias);
            return respuesta(request,plazoMeses,totalDias);
        }else{
            System.out.println("El plazo debe ser mayor o igual a 30 dias.");
            return null;
        }
    }

    public JSONObject fechaEnDias365(JSONCalculoDiasCDT request){
        System.out.println("------------------------------BASE EN DIAS (365)-----------------------------------------");
        LocalDate fechaApertura = conversorString(request.getFechaApertura());
        LocalDate fechaVencimiento = conversorString(request.getFechaVencimiento());
        long difereciaDias = ChronoUnit.DAYS.between(fechaApertura,fechaVencimiento);
        System.out.println("LA DIFERENCIA DE DIAS: "+difereciaDias);
        if(difereciaDias >= 30){
            int contador = serviceCalcFecha.verificarBisiesto(fechaApertura,fechaVencimiento);
            long totalDias = difereciaDias - contador;
            System.out.println("LA FECHA VENCIMIENTO PARA BASE 360: "+fechaVencimiento);
            System.out.println("DIAS CORRESPONDIENTES: "+totalDias);
            return respuesta(request,0,totalDias);
        }else{
            System.out.println("El plazo debe ser mayor o igual a 30 dias.");
            return null;
        }
    }

    public JSONObject fechaEnDiasReal(JSONCalculoDiasCDT request){
        System.out.println("------------------------------BASE EN DIAS (365)-----------------------------------------");
        LocalDate fechaApertura = conversorString(request.getFechaApertura());
        LocalDate fechaVencimiento = conversorString(request.getFechaVencimiento());
        long difereciaDias = ChronoUnit.DAYS.between(fechaApertura,fechaVencimiento);
        System.out.println("LA DIFERENCIA DE DIAS: "+difereciaDias);
        if(difereciaDias >= 30){
            long totalDias = difereciaDias;
            System.out.println("LA FECHA VENCIMIENTO PARA BASE 360: "+fechaVencimiento);
            System.out.println("DIAS CORRESPONDIENTES: "+totalDias);
            return respuesta(request,0,totalDias);
        }else{
            System.out.println("El plazo debe ser mayor o igual a 30 dias.");
            return null;
        }
    }

    //CONSTRUCCION DEL JSON DE RESPUESTA
    public JSONObject respuesta(JSONCalculoDiasCDT request , long plazoMeses , Long plazoDias){
        JSONObject jsonGeneral = new JSONObject();
        Map<String,Object> resultado = new HashMap<>();
        if (request.getBase() == 2 || request.getBase() == 3){
            long meses = periodicidadDias365Real(request.getFechaApertura(), conversorString(request.getFechaVencimiento()));
            resultado.put("periodicidad",serviceCalcFecha.periodicidadAdecuadasParaMeses(meses));
        }else {
            resultado.put("periodicidad",serviceCalcFecha.periodicidadAdecuadasParaMeses(plazoMeses));
        }
        resultado.put("plazoenDias",plazoDias.toString());
        jsonGeneral.put("resultado",resultado);
        return jsonGeneral;
    }

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
                System.out.println("PERIODICIDAD AL PLAZO");
                cantidadMeses =  1;
            }
        }
        return cantidadMeses;
    }

}
