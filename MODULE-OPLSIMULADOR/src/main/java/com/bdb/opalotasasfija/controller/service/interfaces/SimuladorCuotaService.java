package com.bdb.opalotasasfija.controller.service.interfaces;

import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opalotasasfija.persistence.JSONSchema.JSONTasaEfectivaNominal;
import com.bdb.opalotasasfija.persistence.JSONSchema.ResultJSONCuotas;
import com.bdb.opalotasasfija.persistence.JSONSchema.request.simuladorcuota.JSONRequestSimuladorCuota;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResponseSimuladorCuota;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public interface SimuladorCuotaService {

    JSONResponseSimuladorCuota simulador(JSONRequestSimuladorCuota request, HttpServletRequest urlRequest) throws ParseException;

}
