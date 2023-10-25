package com.bdb.opalogdoracle.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.HisCDTSLargeEntity;
import com.bdb.opaloshare.persistence.entity.SalRenautdigEntity;

import java.util.List;

public interface ApersobreRenautService {

    //ResponseEntity<RequestResult<HashMap<String, Object>>> apersobreRenaut(HttpServletRequest http) throws Exception;

    boolean aperIntoRenaut(String http);

    List<SalRenautdigEntity> getCdtsRenaut();

    List<HisCDTSLargeEntity> getInfoHisCdts(Long cdtsRenaut);

    void almacenarEstadoRenautSQLServer(String http, String estado);

}
