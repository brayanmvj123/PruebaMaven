package com.bdb.moduleoplcovtdsa.controller.service.interfaces;

import com.bdb.moduleoplcovtdsa.persistence.JSONSchema.JSONConsultaBVBM;
import com.bdb.moduleoplcovtdsa.persistence.model.ResponseBVBM;

public interface CDTsBVBMService {

	ResponseBVBM consultaCDTsBVBM(JSONConsultaBVBM pruebas);
	
}
