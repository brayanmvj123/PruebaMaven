package com.bdb.opalossqls.controller.service.interfaces;

import com.bdb.opalossqls.persistence.entity.DCVSalPgOpl;
import com.bdb.opalossqls.persistence.model.JSONCancelCdtDig;

import java.util.List;

public interface IDVCSalPgOplService {

    DCVSalPgOpl guardar(DCVSalPgOpl dcvSalPgOpl);

    void guardarLista(List<JSONCancelCdtDig> cancelaciones);
}
