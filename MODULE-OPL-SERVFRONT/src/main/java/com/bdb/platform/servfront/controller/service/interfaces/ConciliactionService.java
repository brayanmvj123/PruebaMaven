package com.bdb.platform.servfront.controller.service.interfaces;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.persistence.model.email.ResponseEmail;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.platform.servfront.model.JSONSchema.RequestSalPg;
import com.bdb.platform.servfront.model.JSONSchema.RequestSalPgSemanal;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ConciliactionService {

    ResponseEntity<RequestResult<Map<String, Object>>> updateDaily(RequestSalPg request, HttpServletRequest http);

    ResponseEntity<RequestResult<Map<String, Object>>> updateWeekly(RequestSalPgSemanal request, HttpServletRequest http);

    Boolean confirmationDaily() throws ErrorFtps, IOException;

    Boolean confirmationWeekly();

    Map<String, List<ResponseEmail>> sendFilesOfficeDaily(HttpServletRequest http) throws Exception;

    Map<String, Object> sendFilesOfficeWeekly(HttpServletRequest http) throws Exception;
}
