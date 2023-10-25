package com.bdb.opalogdoracle.controller.service.interfaces;

import com.bdb.opalogdoracle.persistence.model.servicecancel.JSONCancelCdtDig;

import java.util.List;

public interface EnvioCancelacionService {

    List<JSONCancelCdtDig> envioCancelaciones();

}
