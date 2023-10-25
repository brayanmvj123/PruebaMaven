package com.bdb.moduleoplcancelaciones.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.HisInfoCDTEntity;

public interface InfoCDTService {

    HisInfoCDTEntity agregarInfoCDT(String numCdt, String ctaInv, String canal, String codProd);
}
