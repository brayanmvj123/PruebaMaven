package com.bdb.opalo.oficina.controller.service.interfaces;

import com.bdb.opalo.oficina.persistence.model.CreateOfficeModel;
import com.bdb.opalo.oficina.persistence.response.ResponseBatch;
import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.persistence.entity.OficinaParDownEntity;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface CreateOfficesService {

    ResponseEntity<ResponseBatch> createOffice(HttpServletRequest urlRequest) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, IOException, ErrorFtps;

    boolean getFile();

    void saveOffice(List<? extends OficinaParDownEntity> items);

    OficinaParDownEntity validateOffice(CreateOfficeModel data);

}
