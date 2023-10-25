package com.bdb.oplbacthdiarios.controller.service.interfaces;

import com.bdb.oplbacthdiarios.persistence.model.XLSXSheetModel;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ReporteExcel {
	
	public void loadDatatableToXlsxRequest () throws JsonProcessingException;
	
	public void generateExcel(XLSXSheetModel xlsx);

}
