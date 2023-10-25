package com.bdb.opalogdoracle.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.TipSegmentoEntity;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface TipSegmentoService {

    void agregarSegmento(List<? extends TipSegmentoEntity> items);

    void guardarSegmento(ByteArrayOutputStream archivo);

    ByteArrayOutputStream cargarSegmento();

}
