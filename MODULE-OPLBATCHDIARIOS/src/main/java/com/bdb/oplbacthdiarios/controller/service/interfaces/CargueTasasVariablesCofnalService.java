package com.bdb.oplbacthdiarios.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.OplHisTasaVariableEntity;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseBatch;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CargueTasasVariablesCofnalService {

    void deleteData();

    void migrarTasasVariableCofnal(String host);

    ResponseEntity<ResponseBatch> almacenar(List<? extends OplHisTasaVariableEntity> items);

    ResponseEntity<ResponseBatch> consumeJobsTasaVariable(HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException;

    JobExecution runJob(Job job, HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException;

}
