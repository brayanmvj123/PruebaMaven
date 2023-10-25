package com.bdb.opalotasasfija.controller.service.control;

import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opalotasasfija.controller.service.interfaces.SimuladorService;
import com.bdb.opalotasasfija.persistence.JSONSchema.JSONTasaEfectivaNominal;
import com.bdb.opalotasasfija.persistence.JSONSchema.ResultJSONCuotas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("TasaEfectivaNominal/v1")
public class ControllerSimuladorService {
	
	@Autowired
	private SimuladorService simulador;
	
	@PostMapping( value="simulacionCuota", produces = { "application/json" } )
	 public ResponseEntity<RequestResult<ResultJSONCuotas>> calculoTasas(HttpServletRequest request, @Valid @RequestBody JSONTasaEfectivaNominal rq) {
		
		return simulador.simuladorCuota(rq, request);
         
	  }
	
	
	

}
