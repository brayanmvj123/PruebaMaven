package com.bdb.opalo.oficina.controller.service.interfaces;

import com.bdb.opalo.oficina.persistence.response.ResponseBatch;
import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface ClosingOfficesService {

    boolean getFile();



    void deleteData();

    ResponseEntity<ResponseBatch> officesClosed(HttpServletRequest urlRequest) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, ErrorFtps, JobRestartException, IOException;

}
