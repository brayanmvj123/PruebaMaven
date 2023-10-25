package com.bdb.opalogdoracle.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.TipDepositanteEntity;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface TipDepositanteService {

    void agregarDepositante(List<? extends TipDepositanteEntity> items);

    void guardarDepositante(ByteArrayOutputStream archivo);

    ByteArrayOutputStream cargarDepositante();
    
}
