package com.bdb.oplbacthdiarios.controller.service.interfaces;

import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseBatch;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface CdtDiarioTesoreriaService {

    ResponseEntity<ResponseBatch> cdtDiarioTesoreria(HttpServletRequest request) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException;

    ResponseEntity<ResponseBatch> cdtDiarioTesoreriaRenovado(HttpServletRequest request) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException;

    ResponseEntity<ResponseBatch> cdtDiarioTesoreriaCancelado(HttpServletRequest request) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException;

}
