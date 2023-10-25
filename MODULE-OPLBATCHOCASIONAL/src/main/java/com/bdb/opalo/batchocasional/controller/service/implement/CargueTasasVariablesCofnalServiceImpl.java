package com.bdb.opalo.batchocasional.controller.service.implement;

import com.bdb.opalo.batchocasional.controller.service.interfaces.CargueTasasVariablesCofnalService;
import com.bdb.opalo.batchocasional.persistence.Response.ResponseBatch;
import com.bdb.opalo.batchocasional.persistence.Response.ResponseService;
import com.bdb.opalo.batchocasional.persistence.jsonschema.tasaVariableCofnal.JSONResponseTasaVariableCofnal;
import com.bdb.opalo.batchocasional.persistence.model.TasaVariableCofnalModel;
import com.bdb.opaloshare.persistence.entity.OplHisTasaVariableEntity;
import com.bdb.opaloshare.persistence.entity.OplHisTasaVariableIdEntity;
import com.bdb.opaloshare.persistence.entity.TiptasaParDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryOplTasaVariable;
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

    @Override
    public ResponseEntity<ResponseBatch> consumeJobsTasaVariable(HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("SE PROCEDE A ELIMINAR LOS DATOS EXISTENTES EN LA TABLA.");
        deleteData();
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

    @Override
    public void migrarTasasVariableCofnal(String host){
        log.info("SE PROCEDE A ELIMINAR LOS DATOS EXISTENTES EN LA TABLA.");
        deleteData();
        List<TasaVariableCofnalModel> tasas=consumeServiceTasaVariablecofnal(host);
        log.info("SE PROCEDE A ARMAR LA LISTA DE TASAS A GUARDAR.");
        List<OplHisTasaVariableEntity> oplTasasVariables= new ArrayList<>();
        tasas.forEach( item -> oplTasasVariables.addAll(tansformacionTasaVariable(item))
        );
        log.info("SE PROCEDE A GUARDAR LAS TASAS VARIABLES.");
        almacenar(oplTasasVariables);
    }

    /**
     * Este metodo transforma las tasas variables con formato del sistema de cofnal, al formato de opalo.
     * @param item         <b>Item</b>, tasas Variables con formato de cofnal.
     * @return lista valores de <b><i>TASAS VARIABLES</i></b>.
     */
    public List<OplHisTasaVariableEntity> tansformacionTasaVariable(TasaVariableCofnalModel item){
        //transformacion modelo de tasas de cofnal a opalo
        List<OplHisTasaVariableEntity> oplTasasVariables= new ArrayList<>();
        LocalDate localfecha=LocalDate.parse(item.getFecha(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int fecha=Integer.parseInt(localfecha.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        //DTF
        OplHisTasaVariableEntity dtf=new OplHisTasaVariableEntity();
        OplHisTasaVariableIdEntity dtfid=new OplHisTasaVariableIdEntity();
        dtfid.setFecha(fecha);
        TiptasaParDownEntity tasa=new TiptasaParDownEntity();
        tasa.setTipTasa(2);
        dtfid.setTipotasa(tasa);
        dtf.setId(dtfid);
        dtf.setValor(item.getTasaVarDtf());
        oplTasasVariables.add(dtf);
        //IPC
        OplHisTasaVariableEntity ipc=new OplHisTasaVariableEntity();
        OplHisTasaVariableIdEntity ipcid=new OplHisTasaVariableIdEntity();
        ipcid.setFecha(fecha);
        tasa=new TiptasaParDownEntity();
        tasa.setTipTasa(3);
        ipcid.setTipotasa(tasa);
        ipc.setId(ipcid);
        ipc.setValor(item.getTasaVarIpc());
        oplTasasVariables.add(ipc);
        //IBR un mes
        OplHisTasaVariableEntity ibr=new OplHisTasaVariableEntity();
        OplHisTasaVariableIdEntity ibrid=new OplHisTasaVariableIdEntity();
        ibrid.setFecha(fecha);
        tasa=new TiptasaParDownEntity();
        tasa.setTipTasa(6);
        ibrid.setTipotasa(tasa);
        ibr.setId(ibrid);
        ibr.setValor(item.getTasaVarIbr());
        oplTasasVariables.add(ibr);
        //IBR Overnight
        OplHisTasaVariableEntity ibrover=new OplHisTasaVariableEntity();
        OplHisTasaVariableIdEntity ibroverid=new OplHisTasaVariableIdEntity();
        ibroverid.setFecha(fecha);
        tasa=new TiptasaParDownEntity();
        tasa.setTipTasa(7);
        ibroverid.setTipotasa(tasa);
        ibrover.setId(ibroverid);
        ibrover.setValor(item.getTasaVarIbrDiaria());
        oplTasasVariables.add(ibrover);
        //IBR Tres Meses
        OplHisTasaVariableEntity ibrtrimestral=new OplHisTasaVariableEntity();
        OplHisTasaVariableIdEntity ibrtrimestralid=new OplHisTasaVariableIdEntity();
        ibrtrimestralid.setFecha(fecha);
        tasa=new TiptasaParDownEntity();
        tasa.setTipTasa(8);
        ibrtrimestralid.setTipotasa(tasa);
        ibrtrimestral.setId(ibrtrimestralid);
        ibrtrimestral.setValor(item.getTasaVarIbrTrimestral());
        oplTasasVariables.add(ibrtrimestral);
        //IBR seis Meses
        OplHisTasaVariableEntity ibrsemestral=new OplHisTasaVariableEntity();
        OplHisTasaVariableIdEntity ibrsemestralid=new OplHisTasaVariableIdEntity();
        ibrsemestralid.setFecha(fecha);
        tasa=new TiptasaParDownEntity();
        tasa.setTipTasa(9);
        ibrsemestralid.setTipotasa(tasa);
        ibrsemestral.setId(ibrsemestralid);
        ibrsemestral.setValor(item.getTasaVarIbrSemestral());
        oplTasasVariables.add(ibrsemestral);
        return oplTasasVariables;
    }


    /**
     * Este metodo consume el servicio expuesto en el modulo OPLCOFNAL, el cual se encarga de consultar las tasas variables
     * @param host         <b>Host</b>, esta campo permite completar y saber la dirección del HOST al cual apuntar.
     * @return lista valores de <b><i>TASAS VARIABLES</i></b>.
     */
    public List<TasaVariableCofnalModel> consumeServiceTasaVariablecofnal(String host) {
        log.info("ENTRA AL CONSUMO DE TASAS VARIABLES COFNAL.");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setCacheControl(CacheControl.noCache());

        final String url = //host + "OPALOcofnal/v1/tasasVariables";
        "http://localhost:8080/OPALOcofnal/v1/tasasVariables";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JSONResponseTasaVariableCofnal> response = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<JSONResponseTasaVariableCofnal>() {
                });
        if (response.getStatusCode().is2xxSuccessful() && Objects.requireNonNull(response.getBody()).getResult() != null) {
            JSONResponseTasaVariableCofnal body=response.getBody();
            return body.getResult();
        } else {
            log.error("ERROR AL CONSUMIR EL SIMULADOR CALCULO FECHA INICIO DE PERIODO ...");
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "ERROR AL CONSUMIR EL SIMULADOR CALCULO FECHA INICIO DE PERIODO ...");
        }
    }

}
