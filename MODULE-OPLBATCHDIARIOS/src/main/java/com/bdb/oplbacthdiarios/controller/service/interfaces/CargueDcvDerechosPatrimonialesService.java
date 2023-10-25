/*
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Esteban Talero <etalero@bancodebogota.com.co>.
 */
package com.bdb.oplbacthdiarios.controller.service.interfaces;

import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseBatch;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Interface encargada de leer y realizar el cargue del archivo DCV dcob01s
 *
 * @author: Esteban Talero
 * @version: 19/11/2020
 * @since: 19/11/2020
 */
public interface CargueDcvDerechosPatrimonialesService {

    ResponseEntity<ResponseBatch> loadDerechosPatrimoniales(HttpServletRequest request) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, IOException;

}
