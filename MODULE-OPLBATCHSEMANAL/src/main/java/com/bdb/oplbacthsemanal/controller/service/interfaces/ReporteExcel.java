package com.bdb.oplbacthsemanal.controller.service.interfaces;

import com.bdb.oplbacthsemanal.persistence.model.XLSXSheetModel;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ReporteExcel {
	
	public void loadDatatableToXlsxRequest () throws JsonProcessingException;
	
	public void generateExcel(XLSXSheetModel xlsx);

}
