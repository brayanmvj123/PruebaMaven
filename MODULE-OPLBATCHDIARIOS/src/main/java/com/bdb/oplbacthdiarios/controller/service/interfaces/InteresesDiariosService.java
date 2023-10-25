package com.bdb.oplbacthdiarios.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.AcuIntefectDcvEntity;
import com.bdb.opaloshare.persistence.entity.CarIntefectDcvEntity;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseBatch;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.util.List;

public interface InteresesDiariosService {

    void almacenarData(ByteArrayOutputStream archivo);

    ByteArrayOutputStream cargarData();

    void almacenar(List<? extends CarIntefectDcvEntity> items);

    void eliminarInformacion();

    void almacenarDataAcumulada(List<? extends AcuIntefectDcvEntity> items);

    ResponseEntity<ResponseBatch> interesesGenerados(HttpServletRequest request) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException;

}
