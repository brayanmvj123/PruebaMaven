package com.bdb.opalo.mds.controller.service.interfaces;

import com.bancodebogota.rdm.classification.service.GetEntityMembersFault;
import com.bdb.opalo.mds.persistence.ResponseBatch;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface RatesService {

    void enviarInfoJob(List<String> infoMds) throws IOException;

    ResponseEntity<ResponseBatch> responseConsumeRates(HttpServletRequest httpServletRequest) throws GetEntityMembersFault,
            JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, IOException;

}
