package com.bdb.opalogdoracle.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.TipDaneEntity;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface TipDaneService {

    void agregarDane(List<? extends TipDaneEntity> items);

    void guardarDane(ByteArrayOutputStream archivo);

    ByteArrayOutputStream cargarDane();

}
