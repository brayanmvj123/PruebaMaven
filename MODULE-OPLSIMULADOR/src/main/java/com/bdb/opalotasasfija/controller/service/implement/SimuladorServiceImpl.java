package com.bdb.opalotasasfija.controller.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opaloshare.util.Constants;
import com.bdb.opaloshare.util.Utils;
import com.bdb.opalotasasfija.controller.service.interfaces.Calculadora;
import com.bdb.opalotasasfija.controller.service.interfaces.SimuladorService;
import com.bdb.opalotasasfija.persistence.JSONSchema.JSONTasaEfectivaNominal;
import com.bdb.opalotasasfija.persistence.JSONSchema.ResultJSONCuotas;

@Service
public class SimuladorServiceImpl implements SimuladorService {

    @Autowired
    private Calculadora calculadora;
    
    @Autowired
	private Utils util;

    @Override
    public ResponseEntity<RequestResult<ResultJSONCuotas>> simuladorCuota(JSONTasaEfectivaNominal rq, HttpServletRequest request) {

        List<Double> interesList = new ArrayList<>();
        Map<String, String> parametros =  util.ObjectToMap(rq.getParametros());
        ResultJSONCuotas resultJSONCuotas = new ResultJSONCuotas();
        RequestResult<ResultJSONCuotas> simulacionCuotas ;
        
       try {
    	   
   		double tasaFija = Double.valueOf((rq.getParametros().getTasaFija() == null ? null : rq.getParametros().getTasaFija()));
   		String periodicidad = rq.getParametros().getPeriodicidad() == null ? null : rq.getParametros().getPeriodicidad();
		String base = rq.getParametros().getBase() == null ? null : rq.getParametros().getBase();
		double diasPlazo = Double.valueOf((rq.getParametros().getDiasPlazo() == null ? null : rq.getParametros().getDiasPlazo()));
		double capital = Double.valueOf((rq.getParametros().getCapital() == null ? null : rq.getParametros().getCapital()));
		double retencion = Double.valueOf((rq.getParametros().getRetencion() == null ? null : rq.getParametros().getRetencion()));
		
        double baseDias = baseDias(base);
		
        double periodicidadDias = periodicidadDias(periodicidad, diasPlazo);
                
		double periodo = baseDias/periodicidadDias; 
		
		double tasaNominal = calculadora.calcularTasaNominal(tasaFija, periodo);
	    
		double NumPeriodos = diasPlazo / periodicidadDias;
		System.out.println("NumPeriodos: " + NumPeriodos);
	    interesList = calculadora.calcularInteres(tasaNominal, diasPlazo, baseDias, capital, retencion, NumPeriodos);
	    

            if (interesList.size() == 0) {

                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hubo un error al calcular cuotas");

            } else {

                resultJSONCuotas.setInteresBruto(String.format(Constants.DECIMAL_FORMAT_AVOID_NOTATION, interesList.get(0)));
                resultJSONCuotas.setRetencionIntereses(String.format(Constants.DECIMAL_FORMAT_AVOID_NOTATION, interesList.get(1)));
                resultJSONCuotas.setInteresNeto(String.format(Constants.DECIMAL_FORMAT_AVOID_NOTATION, interesList.get(2)));

                resultJSONCuotas.setInteresBrutoPeriodo(String.format(Constants.DECIMAL_FORMAT_AVOID_NOTATION, interesList.get(3)));
                resultJSONCuotas.setRetencionInteresesPeriodo(String.format(Constants.DECIMAL_FORMAT_AVOID_NOTATION, interesList.get(4)));
                resultJSONCuotas.setInteresNetoPeriodo(String.format(Constants.DECIMAL_FORMAT_AVOID_NOTATION, interesList.get(5)));

                resultJSONCuotas.setTasaNominal(tasaNominal);
                
                simulacionCuotas = new RequestResult<>(request, HttpStatus.OK);
                simulacionCuotas.setResult(resultJSONCuotas);

                simulacionCuotas.setParameters(parametros);

            }

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Hubo una excepcion");

	}
       return ResponseEntity.ok(simulacionCuotas);
	
}
	
   public double baseDias(String base) {
		
		double baseDias = 0F; 
		
		switch(base) {
		  case "1":
			  baseDias = Constants.BASE_365;
		    break;
		  case "2":
			  baseDias = Constants.BASE_360;
		    break;
		  case "3":
			  baseDias = Constants.BASE_366;
			  break;
		}
		return baseDias;
	}
	
	public double periodicidadDias(String periodicidad, double diasPlazo) {
		
		double periodicidadDias = 0F;
		
		switch(periodicidad) {
		  case "1":
			  periodicidadDias = diasPlazo;
		    break;
		  case "2":
			  periodicidadDias = Constants.PER_1;
		    break;
		  case "3":
			  periodicidadDias = Constants.PER_2;
		    break;
		  case "4":
			  periodicidadDias = Constants.PER_3;
		    break;
		  case "5": 
			  periodicidadDias = Constants.PER_4;
		    break;
		  case "6": 
			  periodicidadDias = Constants.PER_5;
		    break;
		  case "7": 
			  periodicidadDias = Constants.PER_6;
		    break;
		  case "8": 
			  periodicidadDias = Constants.PER_7;
		    break;
		  case "9": 
			  periodicidadDias = Constants.PER_8;
		    break;
		  case "10": 
			  periodicidadDias = diasPlazo;
		    break;
		  case "11": 
			  periodicidadDias = Constants.PER_10;
		    break;
		  case "12": 
			  periodicidadDias = Constants.PER_12;
		    break;
		  case "13": 
			  periodicidadDias = Constants.PER_18;
		    break;
		  case "14": 
			  periodicidadDias = Constants.PER_24;
		    break;
		  case "15": 
			  periodicidadDias = Constants.PER_30;
		    break;
		  case "16": 
			  periodicidadDias = Constants.PER_36;
		    break;
		  case "17": 
			  periodicidadDias = Constants.PER_42;
		    break;
		  case "18": 
			  periodicidadDias = Constants.PER_48;
		    break;
		  case "19": 
			  periodicidadDias = Constants.PER_60;
		    break;
		  case "20": 
			  periodicidadDias = diasPlazo;
		    break;
		}
		return periodicidadDias;
	}

}
