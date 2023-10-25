package com.bdb.oplbacthdiarios.controller.service.control;

import com.bdb.oplbacthdiarios.controller.service.interfaces.CancelacionDigService;
import com.bdb.oplbacthdiarios.persistence.response.batch.ResponseBatch;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("CDTSDesmaterializado/v1")
@CommonsLog
public class ControllerCancelacionDig {

    private final CancelacionDigService cancelacionDigService;

    public ControllerCancelacionDig(CancelacionDigService cancelacionDigService) {
        this.cancelacionDigService = cancelacionDigService;
    }

    @GetMapping(value = "cancelacion/cdtdigital", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ResponseBatch> cancelacionCdtDigital(HttpServletRequest urlRequest)
            throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, JsonProcessingException {
        return cancelacionDigService.cancelacionAutDig(urlRequest);
    }

}
