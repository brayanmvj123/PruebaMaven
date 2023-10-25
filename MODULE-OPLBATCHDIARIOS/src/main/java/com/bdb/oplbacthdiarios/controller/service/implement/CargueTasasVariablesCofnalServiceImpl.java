package com.bdb.oplbacthdiarios.controller.service.implement;

import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.OplHisTasaVariableEntity;
import com.bdb.opaloshare.persistence.entity.OplHisTasaVariableIdEntity;
import com.bdb.opaloshare.persistence.entity.TiptasaParDownEntity;
import com.bdb.opaloshare.persistence.model.jsonschema.fechaInicioPeriodo.JSONRequestFechaInicioPeriodo;
import com.bdb.opaloshare.persistence.model.jsonschema.obtenerTasa.JSONRequestTasaVariableCofnal;
import com.bdb.opaloshare.persistence.model.jsonschema.obtenerTasa.ParametersTasaVariableCofnal;
import com.bdb.opaloshare.persistence.repository.RepositoryOplTasaVariable;
import com.bdb.oplbacthdiarios.controller.service.interfaces.CargueTasasVariablesCofnalService;
import com.bdb.oplbacthdiarios.persistence.jsonschema.tasaVariableCofnal.JSONResponseTasaVariableCofnal;
import com.bdb.oplbacthdiarios.persistence.model.TasaVariableCofnalModel;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseBatch;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@CommonsLog
public class CargueTasasVariablesCofnalServiceImpl implements CargueTasasVariablesCofnalService {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value="JobTasaVariableCofnal")
    Job jobTasaVariableCofnal;


    @Autowired
    private RepositoryOplTasaVariable repositoryOplTasaVariable;

    @Autowired
    ResponseService responseService;

    @Autowired
    SharedService sharedService;

    @Override
    public ResponseEntity<ResponseBatch> consumeJobsTasaVariable(HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("----------SE PROCEDE JOB--------------");
        JobExecution reportCargueTasaVariable = runJob(jobTasaVariableCofnal, urlRequest);
        if (reportCargueTasaVariable.getStatus().isUnsuccessful()){
            log.error("OCURRIO UN ERROR");
            reportCargueTasaVariable.getStepExecutions().forEach(stepExecution -> log.error(stepExecution.getExitStatus().getExitDescription()));
            ResponseBatch responseEntity = responseService.resultJob(HttpStatus.INTERNAL_SERVER_ERROR.toString(), urlRequest.getRequestURL().toString(),
                    reportCargueTasaVariable.getJobId().toString(), "N/A", "EL CRUCE HA FALLADO", null);
            return new ResponseEntity<>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity<>(responseBatch(reportCargueTasaVariable, urlRequest), validateStatus(reportCargueTasaVariable));
        }
    }

    @Override
    public JobExecution runJob(Job job, HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        maps.put("url", new JobParameter(urlRequest.getRequestURL().toString()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        log.info("JobExecution: " + jobExecution.getStatus());

        while (jobExecution.isRunning()) {
            log.info("...");
        }

        return jobExecution;
    }

    public HttpStatus validateStatus(JobExecution jobExecution){
        return jobExecution.getStatus().isUnsuccessful() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK;
    }

    public ResponseBatch responseBatch(JobExecution jobExecution, HttpServletRequest urlRequest){
        return responseService.resultJob(validateStatus(jobExecution).toString(),
                urlRequest.getRequestURL().toString(), jobExecution.getJobId().toString(),
                jobExecution.getStatus().toString(), null, jobExecution.getStepExecutions());
    }



    @Override
    public void deleteData() {
        repositoryOplTasaVariable.deleteAll();
    }


    @Override
    public ResponseEntity<ResponseBatch> almacenar(List<? extends OplHisTasaVariableEntity> items) {
        repositoryOplTasaVariable.saveAll(items);
        return null;
    }

    public OplHisTasaVariableEntity updateTasa(OplHisTasaVariableEntity item) {
        Optional<OplHisTasaVariableEntity> tasa=repositoryOplTasaVariable.findByIdTipotasaTipTasaAndIdFecha(item.getId().getTipotasa().getTipTasa(),
              item.getId().getFecha()
        );
        if (tasa.isPresent()){
            OplHisTasaVariableEntity tasaExistente=tasa.get();
            log.info("Actualizando tasa "+tasaExistente.getId());
            tasaExistente.setValor(item.getValor());
            return repositoryOplTasaVariable.save(tasaExistente);
        }else{
            log.info("Agregando tasa tasa ");
            return repositoryOplTasaVariable.save(item);
        }
    }

    @Override
    public void migrarTasasVariableCofnal(String host){
        List<TasaVariableCofnalModel> tasas=consumeServiceTasaVariablecofnal(sharedService.generarUrl(host,
                "OPLBATCHDIARIO"));
        log.info("SE PROCEDE A ARMAR LAs DE TASAS.");
        tasas.forEach( item -> {
                tansformacionTasaVariable(item);
            }
        );
    }

    /**
     * Este metodo transforma las tasas variables con formato del sistema de cofnal, al formato de opalo.
     * @param item         <b>Item</b>, tasas Variables con formato de cofnal.
     */
    public void tansformacionTasaVariable(TasaVariableCofnalModel item){
        //transformacion modelo de tasas de cofnal a opalo
        LocalDate localfecha=LocalDate.parse(item.getFecha(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int fecha=Integer.parseInt(localfecha.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        log.info("transformacion modelo de tasas de cofnal a opalo fecha "+fecha);
        //DTF
        OplHisTasaVariableEntity dtf=new OplHisTasaVariableEntity();
        OplHisTasaVariableIdEntity dtfid=new OplHisTasaVariableIdEntity();
        dtfid.setFecha(fecha);
        TiptasaParDownEntity tasa=new TiptasaParDownEntity();
        tasa.setTipTasa(2);
        dtfid.setTipotasa(tasa);
        dtf.setId(dtfid);
        dtf.setValor(item.getTasaVarDtf());
        updateTasa(dtf);
        //IPC
        OplHisTasaVariableEntity ipc=new OplHisTasaVariableEntity();
        OplHisTasaVariableIdEntity ipcid=new OplHisTasaVariableIdEntity();
        ipcid.setFecha(fecha);
        tasa=new TiptasaParDownEntity();
        tasa.setTipTasa(3);
        ipcid.setTipotasa(tasa);
        ipc.setId(ipcid);
        ipc.setValor(item.getTasaVarIpc());
        updateTasa(ipc);
        //IBR un mes
        OplHisTasaVariableEntity ibr=new OplHisTasaVariableEntity();
        OplHisTasaVariableIdEntity ibrid=new OplHisTasaVariableIdEntity();
        ibrid.setFecha(fecha);
        tasa=new TiptasaParDownEntity();
        tasa.setTipTasa(6);
        ibrid.setTipotasa(tasa);
        ibr.setId(ibrid);
        ibr.setValor(item.getTasaVarIbr());
        updateTasa(ibr);
        //IBR Overnight
        OplHisTasaVariableEntity ibrover=new OplHisTasaVariableEntity();
        OplHisTasaVariableIdEntity ibroverid=new OplHisTasaVariableIdEntity();
        ibroverid.setFecha(fecha);
        tasa=new TiptasaParDownEntity();
        tasa.setTipTasa(7);
        ibroverid.setTipotasa(tasa);
        ibrover.setId(ibroverid);
        ibrover.setValor(item.getTasaVarIbrDiaria());
        updateTasa(ibrover);
        //IBR Tres Meses
        OplHisTasaVariableEntity ibrtrimestral=new OplHisTasaVariableEntity();
        OplHisTasaVariableIdEntity ibrtrimestralid=new OplHisTasaVariableIdEntity();
        ibrtrimestralid.setFecha(fecha);
        tasa=new TiptasaParDownEntity();
        tasa.setTipTasa(8);
        ibrtrimestralid.setTipotasa(tasa);
        ibrtrimestral.setId(ibrtrimestralid);
        ibrtrimestral.setValor(item.getTasaVarIbrTrimestral());
        updateTasa(ibrtrimestral);
        //IBR seis Meses
        OplHisTasaVariableEntity ibrsemestral=new OplHisTasaVariableEntity();
        OplHisTasaVariableIdEntity ibrsemestralid=new OplHisTasaVariableIdEntity();
        ibrsemestralid.setFecha(fecha);
        tasa=new TiptasaParDownEntity();
        tasa.setTipTasa(9);
        ibrsemestralid.setTipotasa(tasa);
        ibrsemestral.setId(ibrsemestralid);
        ibrsemestral.setValor(item.getTasaVarIbrSemestral());
        updateTasa(ibrsemestral);
    }

    /**
     * Este metodo consume el servicio expuesto en el modulo OPLCOFNAL, el cual se encarga de consultar las tasas variables del ultimo mes,
     * @param host         <b>Host</b>, esta campo permite completar y saber la dirección del HOST al cual apuntar.
     * @return lista valores de <b><i>TASAS VARIABLES</i></b>.
     */
    public List<TasaVariableCofnalModel> consumeServiceTasaVariablecofnal(String host) {
        log.info("ENTRA AL CONSUMO DE TASAS VARIABLES COFNAL ULTIMO MES.");

//        final String url = host + "OPLCOFNAL/OPALOcofnal/v1/tasasVariables/mes";
        final String url = "http://localhost:8082/OPALOcofnal/v1/tasasVariables/mes";
        log.info("URL de consumo: "+url);

        // Inicio Método para traer tasas de 10 años atrás
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setCacheControl(CacheControl.noCache());

        JSONRequestTasaVariableCofnal request = new JSONRequestTasaVariableCofnal();
        request.setCanal("opalo");
        ParametersTasaVariableCofnal requestParam = new ParametersTasaVariableCofnal();
        requestParam.setFechaInicio(DateTimeFormatter.ofPattern("YYYY/MM/dd").format(LocalDate.now().minusYears(10)));
        requestParam.setFechaFin(DateTimeFormatter.ofPattern("YYYY/MM/dd").format(LocalDate.now()));
        request.setParametros(requestParam);

        HttpEntity<JSONRequestTasaVariableCofnal> requestEntity = new HttpEntity<>(request, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JSONResponseTasaVariableCofnal> response = restTemplate.exchange(
                "http://localhost:8082/OPALOcofnal/v1/tasasVariables/f/obtenertasa",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<JSONResponseTasaVariableCofnal>() {
                });
        // Fin Método para traer tasas de 10 años atrás
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<JSONResponseTasaVariableCofnal> response = restTemplate.exchange(url,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<JSONResponseTasaVariableCofnal>() {
//                });
        if (response.getStatusCode().is2xxSuccessful() && Objects.requireNonNull(response.getBody()).getResult() != null) {
            JSONResponseTasaVariableCofnal body=response.getBody();
            return body.getResult();
        } else {
            log.error("ERROR AL CONSUMIR EL SIMULADOR CALCULO FECHA INICIO DE PERIODO ...");
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "ERROR AL CONSUMIR EL SIMULADOR CALCULO FECHA INICIO DE PERIODO ...");
        }
    }

}
