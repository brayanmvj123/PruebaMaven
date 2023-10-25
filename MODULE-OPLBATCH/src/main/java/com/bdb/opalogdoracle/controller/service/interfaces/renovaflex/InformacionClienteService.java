package com.bdb.opalogdoracle.controller.service.interfaces.renovaflex;

import com.bdb.opalogdoracle.persistence.model.servicecancel.InfoCtaClienteModel;
import com.bdb.opaloshare.persistence.entity.SalRenautdigEntity;

public interface InformacionClienteService {

    InfoCtaClienteModel obtenerInformacion(SalRenautdigEntity salRenautdigEntity);

}
