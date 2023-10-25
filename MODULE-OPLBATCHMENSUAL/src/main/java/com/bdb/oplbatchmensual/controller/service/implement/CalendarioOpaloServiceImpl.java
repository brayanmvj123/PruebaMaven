package com.bdb.oplbatchmensual.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.OplCarCalendarconDownEntity;
import com.bdb.opaloshare.persistence.entity.OplHisCalendarioDownEntity;
import com.bdb.opaloshare.persistence.entity.OplHisCalendarioDownIdEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryCarCalendarconDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryHisCalendarioDownEntity;
import com.bdb.oplbatchmensual.controller.service.interfaces.CalendarioOpaloService;
import com.bdb.oplbatchmensual.persistence.response.batch.ResponseBatch;
import com.bdb.oplbatchmensual.persistence.response.batch.ResponseService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@CommonsLog
public class CalendarioOpaloServiceImpl implements CalendarioOpaloService {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value="JobCalendarioOpalo")
    Job jobCalendarioOpalo;

    @Autowired
    private RepositoryCarCalendarconDownEntity repositoryCarCalendarconDownEntity;

    @Autowired
    private RepositoryHisCalendarioDownEntity repositoryHisCalendarioDownEntity;

    @Autowired
    ResponseService responseService;

    @Override
    public void migrarCalendarioOpalo(String host) {
        log.info("SE PROCEDE A ARMAR EL CALENDARIO.");
        //consultar data car
        //armar calendario his
        List<OplHisCalendarioDownEntity> calendarioOpalo=analisis();
        //guardar o actualizar
        almacenar(calendarioOpalo);
    }

    private List<OplHisCalendarioDownEntity> analisis(){
        LocalDate today= LocalDate.now();
        log.info("dia actual "+today.toString());
        log.info("mes "+today.getMonthValue()+" - "+(today.getMonthValue()+3));
        List<OplHisCalendarioDownEntity> calendarioOpalo= new ArrayList<>();
        int mes_inicio=today.getMonthValue();
        int mes_fin=today.getMonthValue()+3;
        if(mes_fin>12){
            List<OplCarCalendarconDownEntity> oplCalendarCon = this.repositoryCarCalendarconDownEntity.findByMesGreaterThanEqualAndMesLessThan(
                    mes_inicio, 13
            );
            for (OplCarCalendarconDownEntity item:oplCalendarCon) {
                calendarioOpalo.add(saveDay(today,item));
            }
            today=today.plusYears(1);
            log.info("add year"+today.toString());
            List<OplCarCalendarconDownEntity> oplCalendarCon2=this.repositoryCarCalendarconDownEntity.findByMesGreaterThanEqualAndMesLessThan(
                    0, mes_fin-12
            );
            for (OplCarCalendarconDownEntity item:oplCalendarCon2) {
                calendarioOpalo.add(saveDay(today,item));
            }
        }else {
            List<OplCarCalendarconDownEntity> oplCalendarCon =this.repositoryCarCalendarconDownEntity.findByMesGreaterThanEqualAndMesLessThan(
                    mes_inicio, mes_fin
            );
            for (OplCarCalendarconDownEntity item:oplCalendarCon) {
                calendarioOpalo.add(saveDay(today,item));
            }
        }
        return calendarioOpalo;
    }
    private OplHisCalendarioDownEntity saveDay(LocalDate today,OplCarCalendarconDownEntity item){
        OplHisCalendarioDownEntity hiscalendario = new OplHisCalendarioDownEntity();
        OplHisCalendarioDownIdEntity fecha = new OplHisCalendarioDownIdEntity();
        fecha.setAnno(today.getYear());
        fecha.setMes(item.getMes());
        fecha.setDia(item.getDia());
        hiscalendario.setFecha(fecha);
        //validar codigo
        int valorDia = 1;//1 -> dia habil, 0 -> dia no habil
        if (item.getValor() != 0) {
            valorDia = 0;
        }
        hiscalendario.setValor(valorDia);
        return hiscalendario;
    }
    @Override
    public void almacenar(List<? extends OplHisCalendarioDownEntity> items) {
        this.repositoryHisCalendarioDownEntity.saveAll(items);
    }

    @Override
    public ResponseEntity<ResponseBatch> consumeJobsCalendario(HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("----------SE PROCEDE JOB--------------");
        JobExecution actualizacionCalendarioCofnal = runJob(jobCalendarioOpalo, urlRequest);
        if (actualizacionCalendarioCofnal.getStatus().isUnsuccessful()){
            log.error("OCURRIO UN ERROR");
            actualizacionCalendarioCofnal.getStepExecutions().forEach(stepExecution -> log.error(stepExecution.getExitStatus().getExitDescription()));
            ResponseBatch responseEntity = responseService.resultJob(HttpStatus.INTERNAL_SERVER_ERROR.toString(), urlRequest.getRequestURL().toString(),
                    actualizacionCalendarioCofnal.getJobId().toString(), "N/A", "EL CRUCE HA FALLADO", null);
            return new ResponseEntity<>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity<>(responseBatch(actualizacionCalendarioCofnal, urlRequest), validateStatus(actualizacionCalendarioCofnal));
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

    public ResponseBatch responseBatch(JobExecution jobExecution, HttpServletRequest urlRequest){
        return responseService.resultJob(validateStatus(jobExecution).toString(),
                urlRequest.getRequestURL().toString(), jobExecution.getJobId().toString(),
                jobExecution.getStatus().toString(), null, jobExecution.getStepExecutions());
    }

    public HttpStatus validateStatus(JobExecution jobExecution){
        return jobExecution.getStatus().isUnsuccessful() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK;
    }
}
