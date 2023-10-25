package com.bdb.oplbacthsemanal.schudeler.serviceimpl;

import com.bdb.opaloshare.persistence.entity.SalPgSemanalDownEntity;
import com.bdb.opaloshare.persistence.model.jsonschema.calculadoradias.JSONRequestCalculadoraDias;
import com.bdb.opaloshare.persistence.model.jsonschema.fechaInicioPeriodo.JSONRequestFechaInicioPeriodo;
import com.bdb.opaloshare.persistence.model.jsonschema.fechaInicioPeriodo.ParametersFechaInicioPeriodo;
import com.bdb.opaloshare.persistence.model.jsonschema.simuladorcuota.JSONRequestSimuladorCuota;
import com.bdb.opaloshare.persistence.model.jsonschema.simuladorcuota.ParametersSimuladorCuota;
import com.bdb.opaloshare.persistence.model.jsonschema.tasaVariable.JSONRequestTasaVariable;
import com.bdb.opaloshare.persistence.model.jsonschema.tasaVariable.ParametersTasaVariable;
import com.bdb.opaloshare.persistence.model.response.calculadoradias.JSONResponseCalculadoraDias;
import com.bdb.opaloshare.persistence.model.response.calculadoradias.JSONResponseStatus;
import com.bdb.opaloshare.persistence.model.response.calculadoradias.JSONResultCalculoraDias;
import com.bdb.opaloshare.persistence.model.response.fechaInicioPeriodo.JSONResponseFechaInicioPeriodo;
import com.bdb.opaloshare.persistence.model.response.simuladorcuota.JSONResponseSimuladorCuota;
import com.bdb.opaloshare.persistence.model.response.tasaVariable.JSONResponseTasaVariable;
import com.bdb.opaloshare.persistence.repository.RepositorySalPgSemanalDown;
import com.bdb.oplbacthsemanal.mapper.MapperReportPg;
import com.bdb.opaloshare.controller.service.interfaces.HttpClient;
import com.bdb.oplbacthsemanal.schudeler.services.OperationBatchSimQuota;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
@CommonsLog
public class OperationBatchSimQuotaImpl implements OperationBatchSimQuota {

    private static final String CODIGO_APP = "OPL766";

    @Autowired
    RepositorySalPgSemanalDown repositorySalPgSemanal;

    @Autowired
    private MapperReportPg mapperReportPg;
    
    @Autowired
    private HttpClient httpClient;


    @Override
    public void calculateFactor(String host) {
        repositorySalPgSemanal.findAll()
            .stream()
            .filter(Objects::nonNull)
            .filter(factor -> factor.getFactorOpl().equals(new BigDecimal(0)))
            .forEach(item -> {
                LocalDate fechaInicial = consumeServiceFechaInicioPeriodo(item, host);
                String tasa = item.getTipTasa().equals("1") ? item.getTasaEfe().toString() : consumeServiceTasaVariable(item, fechaInicial, host);
                repositorySalPgSemanal.updateFactorValue(consumeServiceSimuCuota(item, tasa, fechaInicial, host), item.getItem());
            });
    }

    /**
     * Este metodo consume el servicio expuesto en el módulo OPLSIMULADOR, el cual se encarga de calcular la Fecha de
     * inicio de periodo.
     *
     * @param salPgSemanal  Contiene todos los parámetros para enviar la consulta al servicio de SIMULADOR
     *                      Se esperan los siguientes valores:
     * @return El valor de la <i>TASA</i> de acuerdo a los parametros enviados en la consulta.
     */
    public LocalDate consumeServiceFechaInicioPeriodo(SalPgSemanalDownEntity salPgSemanal, String host) {
        ParametersFechaInicioPeriodo requestParam = mapperReportPg.salPgSemanalToParamsFechaInicioPeriodo(salPgSemanal);
        JSONRequestFechaInicioPeriodo request = new JSONRequestFechaInicioPeriodo(CODIGO_APP, requestParam);

        final String url = host + "OPLSIMULADOR/CDTSDesmaterializado/v2/calculoFechaInicioPeriodo";
//        final String url = "http://localhost:8080/CDTSDesmaterializado/v2/calculoFechaInicioPeriodo";
        ResponseEntity<JSONResponseFechaInicioPeriodo> response = httpClient.post(request, url, "FECHA INICIO DE PERIODO", JSONResponseFechaInicioPeriodo.class);
         return response.getBody() != null ? LocalDate.parse(response.getBody().getResult().getFechaInicio(), DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
    }


    /**
     * Este metodo consume el servicio expuesto en el modulo OPLSIMULADOR, el cual se encarga consultar la tasa variable
     *
     * @param salPgSemanal  Contiene todos los parámetros para enviar la consulta al servicio de SIMULADOR
     * @param fechaEmi     Se toma del consumo del servicio <i>calculoFechaInicial</i>
     *                     del modulo <i>MODULE-OPLSIMULADOR</i>.
     * @param host     Esta campo permite completar y saber la dirección del HOST al cual apuntar.
     * @return El valor de la <i>TASA</i>.
     */
    public String consumeServiceTasaVariable(SalPgSemanalDownEntity salPgSemanal, LocalDate fechaEmi, String host) {
        ParametersTasaVariable requestParam = mapperReportPg.salPgSemanalToParamsTasaVariable(salPgSemanal.getTipTasa(), fechaEmi);
        JSONRequestTasaVariable request = new JSONRequestTasaVariable(CODIGO_APP, requestParam);

        final String url = host + "OPLSIMULADOR/CDTSDesmaterializado/v2/tasaVariable/ttf/obtenertasa";
//        final String url = "http://localhost:8080/CDTSDesmaterializado/v2/tasaVariable/ttf/obtenertasa";
        ResponseEntity<JSONResponseTasaVariable> response = httpClient.post(request, url, "TASA VARIABLE", JSONResponseTasaVariable.class);
        return response.getBody() != null ? response.getBody().getResult().getValor().toString() : null;
    }


    /**
     * Este metodo consume el servicio expuesto en el <i>MODULE-OPLSIMULADOR</i>, el cual se encarga simular los datos
     * enviados al request y calcular la Tasa Nominal y Efectiva.
     *
     * @param salPgSemanal  Contiene todos los parámetros para enviar la consulta al servicio de SIMULADOR CUOTA
     *                      Se esperan los siguientes valores:
     * @param tasa         Si es tasa fija se obtiene de la consulta a la tabla <i>TmpTasasCdtMdsEntity</i>, de lo
     *                     contrario se trae del servicio tasaVariable.
     * @param fechaEmi     Se toma del consumo del servicio <i>calculoFechaInicial</i>
     *                     del modulo <i>MODULE-OPLSIMULADOR</i>.
     * @param host         Permite completar y saber la dirección del HOST al cual apuntar.
     * @return El valor del <i>FACTOR</i> de cada simulación.
     */
    public BigDecimal consumeServiceSimuCuota(SalPgSemanalDownEntity salPgSemanal, String tasa, LocalDate fechaEmi, String host) {
        String diasPlazo = calculoServiceDias(salPgSemanal, fechaEmi, host);
        ParametersSimuladorCuota requestParam = mapperReportPg.salPgSemanalToParametersSimuladorCuota(salPgSemanal, tasa, diasPlazo, calculateRetencion(salPgSemanal));
        JSONRequestSimuladorCuota request = new JSONRequestSimuladorCuota(CODIGO_APP, requestParam);

        final String url = host + "OPLSIMULADOR/TasaEfectivaNominal/v2/simulacionCuota";
//        final String url = "http://localhost:8080/CDTSDesmaterializado/v2/simulacionCuota";
        ResponseEntity<JSONResponseSimuladorCuota> response = httpClient.post(request, url, "SIMULADOR CUOTA V2", JSONResponseSimuladorCuota.class);
        return response.getBody() != null ? BigDecimal.valueOf(response.getBody().getResult().getFactor()) : null;
    }


    /**
     * Este metodo realiza el consumo del servicio expuesto en el modulo OPLSIMULADOR, el cual calcula los dias entre la
     * fecha de apertura y la fecha de vencimiento.
     *
     * @param salPgSemanal  Contiene todos los parámetros para enviar la consulta al servicio de SIMULADOR
     * @param fechaEmi     Se toma del consumo del servicio <i>calculoFechaInicial</i>
     *                     del modulo <i>MODULE-OPLSIMULADOR</i>.
     * @param host     Host, esta campo permite completar y saber la dirección del HOST al cual apuntar.
     * @return El resultado del <i>número de dias</i> entre la fecha de apertura y la fecha vencimiento.
     */
    public String calculoServiceDias(SalPgSemanalDownEntity salPgSemanal, LocalDate fechaEmi, String host) {
        JSONRequestCalculadoraDias request = mapperReportPg.salPgSemanalToJSONRequestCalculadoraDias(Integer.parseInt(salPgSemanal.getTipBase()),
                fechaEmi, LocalDate.parse(salPgSemanal.getFechaProxPg(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        final String url = host + "OPLSIMULADOR/CDTSDesmaterializado/v2/simulador/calculoDiasCDT";
//        final String url = "http://localhost:8080/CDTSDesmaterializado/v2/simulador/calculoDiasCDT";

        ResponseEntity<JSONResponseCalculadoraDias<JSONResponseStatus, JSONRequestCalculadoraDias, JSONResultCalculoraDias>> response =
                httpClient.post(request, url, "CALCULO DE DIAS",
                new ParameterizedTypeReference<JSONResponseCalculadoraDias<JSONResponseStatus, JSONRequestCalculadoraDias, JSONResultCalculoraDias>>(){});

        JSONResponseCalculadoraDias<JSONResponseStatus, JSONRequestCalculadoraDias, JSONResultCalculoraDias> resultado2 = response.getBody();
        return resultado2.getResult().getPlazoenDias().toString();
    }


    private String calculateRetencion(SalPgSemanalDownEntity salPgDownEntity){
        return salPgDownEntity.getRteFte().divide(salPgDownEntity.getIntBruto(), 5, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100L)).setScale(0, RoundingMode.HALF_UP).toString();
    }

}
