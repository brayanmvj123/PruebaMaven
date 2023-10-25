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
package com.bdb.opalogdoracle.Scheduler.load.renovacion;

import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * Tasklet encargado de limpiar y almacenar los registros de marcacion renovacion CDT DIG en DCV Sql
 *
 * @author: Esteban Talero
 * @version: 10/11/2020
 * @since: 09/10/2020
 */
public class CrossSalDCvRenautDigTasklet implements Tasklet {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SharedService sharedService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        try {
            logger.info("start OPLSQL batch RENAUT DCV DIG...");
            String host = sharedService.generarUrl(chunkContext.getStepContext().getJobParameters().get("url")
                    .toString(), "OPLBATCH");
            final String url = host + "OPLBATCH/CDTSDesmaterializado/v1/controlDcvRenautDig";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(url, null, String.class);
        } catch (Exception e) {
            logger.error("Error en CrossSalDCvRenautDigTasklet: " + e);
            throw new UnexpectedJobExecutionException("Error en CrossSalDCvRenautDigTasklet");
        }
        return RepeatStatus.FINISHED;
    }

}
