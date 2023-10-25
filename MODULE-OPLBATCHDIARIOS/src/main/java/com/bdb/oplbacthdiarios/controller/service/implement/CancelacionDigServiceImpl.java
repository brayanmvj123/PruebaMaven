package com.bdb.oplbacthdiarios.controller.service.implement;

import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.repository.RepositorySalPg;
import com.bdb.oplbacthdiarios.controller.service.interfaces.CancelacionDigService;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseBatch;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import lombok.extern.apachecommons.CommonsLog;
import org.json.JSONObject;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@CommonsLog
public class CancelacionDigServiceImpl implements CancelacionDigService {

    private final JobLauncher jobLauncher;

    private final Job jobPgsCdtCancel;

    private final Job jobReportesPgCuentasContables;

    private final RepositorySalPg repositorySalPg;

    private final ResponseService responseService;

    private final SharedService sharedService;

    public CancelacionDigServiceImpl(JobLauncher jobLauncher,
                                     @Qualifier(value = "JobPgsCdtCancel") Job jobPgsCdtCancel,
                                     @Qualifier(value = "jobReportesPgCuentasContables") Job jobReportesPgCuentasContables,
                                     RepositorySalPg repositorySalPg,
                                     ResponseService responseService,
                                     SharedService sharedService) {
        this.jobLauncher = jobLauncher;
        this.jobPgsCdtCancel = jobPgsCdtCancel;
        this.jobReportesPgCuentasContables = jobReportesPgCuentasContables;
        this.repositorySalPg = repositorySalPg;
        this.responseService = responseService;
        this.sharedService = sharedService;
    }

    @Override
    public ResponseEntity<ResponseBatch> cancelacionAutDig(HttpServletRequest urlRequest) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JsonProcessingException {

        log.info("SE CAMBIA EL ESTADO DE LOS ***CDTS DIGITALES RENOVADOS*** A ESTADO 5 YA QUE FUERON YA CARGADOS A " +
                "LA TRAMA CONTABLE DEL DIA");
        cambiarEstado(3);

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        maps.put("url", new JobParameter(sharedService.generarUrl(urlRequest.getRequestURL().toString(), "OPLBATCHDIARIO")));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(jobPgsCdtCancel, parameters);

        log.info("JobExecution: " + jobExecution.getStatus());

        while (jobExecution.isRunning()) {
            log.info("...");
        }

        if (jobExecution.getStatus().isUnsuccessful()) {
//            almacenarEstadoCancelSQLServer(urlRequest.getRequestURL().toString(), "CANCELACION_FAIL");
            log.error("OCURRIO UN ERROR :(");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error realizando los pagos automaticos: " +
                    jobExecution.getStatus());
        } else {
            log.info("Se inicia el consumo del servicio expuesto en MODULO-OPLTRADUCTOR para generar las tramas de " +
                    "CANCELACIONES a traves del Traductor CADI.");
            String tramaTraductor = tramasTraductorCadi(urlRequest.getRequestURL().toString(),
                    urlRequest.getRequestURL().toString());

            if (Optional.ofNullable(tramaTraductor).isPresent() && !tramaTraductor.isEmpty()) {
                JSONObject body = new JSONObject(tramaTraductor);
                HashMap<String, Object> resultTramasCadi = new Gson().fromJson(body.getJSONObject("result")
                        .toString(), HashMap.class);
                log.info("TRAMAS: \n" + resultTramasCadi.toString());
                cambiarEstado(1);
                cambiarEstado(2);
            }
//            almacenarEstadoCancelSQLServer(urlRequest.getRequestURL().toString(), "CANCELACION_COMPLETED");
            log.info("inicio job reporte de cuentas contables...");
            JobParameters parametersFile = new JobParametersBuilder().addDate("date", new Date()).addLong("time",
                    System.currentTimeMillis()).toJobParameters();
            jobExecution = jobLauncher.run(jobReportesPgCuentasContables, parametersFile);
        }

        return responseService.getResponseJob(jobExecution, "EL PAGO DEL CDT HA FALLADO", urlRequest);
    }

    public String tramasTraductorCadi(String url, String urlBatch) {
        String responseBody;
        try {
            log.info("start TRADUCTOR CADI...");
            String host = sharedService.generarUrl(urlBatch, "OPLBATCHDIARIO");
            final String urlTraductor = host + "TRADUCTOR/DO/v1/process/cancelacion";
                    //"http://localhost:8082/DO/v1/process/cancelacion";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(urlTraductor,
                    HttpMethod.POST,
                    null,
                    new ParameterizedTypeReference<String>() {
                    });
            responseBody = response.getBody();
        } catch (Exception e) {
            almacenarEstadoCancelSQLServer(url, "CANCELACION_FAIL");
            log.error("Error en CrossTraductorCadiTasklet: {0}", e);
            throw new UnexpectedJobExecutionException("Error en CrossTraductorCadiTasklet");
        }
        return responseBody;
    }

    /**
     * Este metodo realiza el consumo del servicio <i><u>estadoRenaut</u></i> expuesto por <i><u>MODULE_OPLSSQLS</u></i>,
     * el cual almacena el estado final de todo el proceso de cancelación.
     *
     * @param http   URL donde se consume el servicio.
     * @param estado Los estados (AJUSTAR LA DOCUMENTACION)
     */
    public void almacenarEstadoCancelSQLServer(String http, String estado) {
        String host = sharedService.generarUrl(http, "OPLBATCHDIARIO");
        final String url = host + "OPLSSQLS/CDTSDesmaterializado/v1/renovacion/estadoRenaut?status=" + estado;
        //"http://localhost:8080/CDTSDesmaterializado/v1/renovacion/estadoRenaut?status="+estado;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.POST,
                null,
                new ParameterizedTypeReference<String>() {
                });
        response.getStatusCodeValue();
    }

    public void cambiarEstado(Integer estado){
        repositorySalPg.updateStatusReinvested(estado);
    }


}
