package com.bdb.opalogdoracle.controller.service.interfaces.renovaflex;

import com.bdb.opaloshare.persistence.entity.VarentornoDownEntity;
import com.google.gson.JsonObject;

public interface ControlEstadoService {

    JsonObject getProcesoRenovacion();

    int obtenerPaso();

    String obtenerEstado();

    boolean validacionEstado();

    VarentornoDownEntity actualizarControlRenovacion(String valorEstado, Integer valorPaso);

    void actualizarHistorialResultado(String paso);

}
