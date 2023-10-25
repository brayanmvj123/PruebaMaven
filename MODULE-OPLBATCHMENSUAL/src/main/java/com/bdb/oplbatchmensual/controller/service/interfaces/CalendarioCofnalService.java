package com.bdb.oplbatchmensual.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.OplCarCalendarconDownEntity;
import com.bdb.oplbatchmensual.persistence.response.batch.ResponseBatch;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CalendarioCofnalService {
    void deleteData();

    void migrarCalendarioCofnal(String host);

    void almacenar(List<? extends OplCarCalendarconDownEntity> items);

    ResponseEntity<ResponseBatch> consumeJobsCalendario(HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException;

    JobExecution runJob(Job job, HttpServletRequest urlRequest) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException;

}
