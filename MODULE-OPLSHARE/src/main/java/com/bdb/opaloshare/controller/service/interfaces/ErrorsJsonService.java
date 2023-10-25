package com.bdb.opaloshare.controller.service.interfaces;

import java.util.Map;

public interface ErrorsJsonService {

	public String diagnostico(Map<String, String> parametersRequest , String nombreService);
	
	public String problemParamRequest();
	
}
