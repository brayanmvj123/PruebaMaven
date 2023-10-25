package com.bdb.opalotasasfija.controller.service.interfaces;

import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opalotasasfija.persistence.JSONSchema.JSONTasaEfectivaNominal;
import com.bdb.opalotasasfija.persistence.JSONSchema.ResultJSONCuotas;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface SimuladorService {

	public ResponseEntity<RequestResult<ResultJSONCuotas>> simuladorCuota(JSONTasaEfectivaNominal rq, HttpServletRequest request);
			
}
