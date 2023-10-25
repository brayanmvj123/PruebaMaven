package com.bdb.moduleoplcovtdsa.controller.service.interfaces;

import com.bdb.moduleoplcovtdsa.persistence.JSONSchema.request.JSONRequestInfoCdtReno;
import com.bdb.moduleoplcovtdsa.persistence.JSONSchema.response.JSONResponseInfoCdtReno;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface InfoCdtRenoService {

     ResponseEntity<JSONResponseInfoCdtReno> consultarNumCdt(JSONRequestInfoCdtReno request, HttpServletRequest urlRequest);

}
