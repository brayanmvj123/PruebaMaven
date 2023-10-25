package com.bdb.platform.servfront.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.TxtMediumTbl;

public interface LabelsService {
	
	TxtMediumTbl getLabel(Integer idTxt);
	
	TxtMediumTbl setLabel(TxtMediumTbl report);

}
