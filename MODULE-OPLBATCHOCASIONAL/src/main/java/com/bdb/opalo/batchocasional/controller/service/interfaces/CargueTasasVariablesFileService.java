package com.bdb.opalo.batchocasional.controller.service.interfaces;

import com.bdb.opalo.batchocasional.persistence.Response.ResponseBatch;
import com.bdb.opaloshare.persistence.entity.OplHisTasaVariableEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.util.List;

public interface CargueTasasVariablesFileService {
    void deleteData();

    ResponseEntity<ResponseBatch> almacenar(List<? extends OplHisTasaVariableEntity> items);

    ResponseEntity<ResponseBatch> consumeJobsTasaVariableFile(HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException;

    JobExecution runJob(Job job, HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException;

    ByteArrayOutputStream cargarArchivo();
}
