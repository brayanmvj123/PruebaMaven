package com.bdb.opalogdoracle.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.OficinaParDownEntity;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface OficinaService {

    void agregarOficina(List<? extends OficinaParDownEntity> items);

    void guardarOficina(ByteArrayOutputStream archivo);

    ByteArrayOutputStream cargarOficina();
    
}
