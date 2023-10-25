package com.bdb.moduleoplcancelaciones.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.HisRenovacion;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT.RequestCDTContingencia;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT.RequestCancelCDT;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT.RequestConfirmacionCancelacion;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.ResponseCancelacionCDT.ResponseCdtProxVen;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT.RequestQueryCDT;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CDTService {

    ResponseCdtProxVen cdtProximoAVencer(RequestQueryCDT requestCDT);

    ResponseEntity<?> cancelarCdt(RequestCancelCDT requestCancelCDT, HttpServletRequest http) throws Exception;

    ResponseEntity<?> renovarCDT(RequestConfirmacionCancelacion requestConfirmacionCancelacion, HttpServletRequest httpServletRequest) throws Exception;

    ResponseEntity<?> contingencia(RequestCDTContingencia requestCDTContingencia, HttpServletRequest httpServletRequest ) throws Exception;
}
