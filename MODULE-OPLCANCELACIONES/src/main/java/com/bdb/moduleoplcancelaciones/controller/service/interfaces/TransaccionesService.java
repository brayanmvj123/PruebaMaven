package com.bdb.moduleoplcancelaciones.controller.service.interfaces;


import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT.RequestCancelCDT;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface TransaccionesService {

    ResponseEntity<RequestResult<?>> registerTranp(RequestCancelCDT requestCancelCDT, HttpServletRequest http) throws Exception;
}
