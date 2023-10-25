package com.bdb.opalogdoracle.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.HisLoginDownEntity;
import com.bdb.opaloshare.persistence.entity.OplParCorreoEntity;

import java.util.List;

public interface HisLoginService {

    void bloquearUsuario(List<? extends HisLoginDownEntity> items);

    String cantidadDias();

    /**
     * List<HisLoginDownEntity> con el resultado de la consulta en la base de datos
     * con los usuarios segun el estado enviado
     *
     * @return
     */
    boolean generarExcelSegunEstadoUsuario(List<HisLoginDownEntity> data, String tipoArchivo);

    /**
     * @param tipoArchivo String origin de quien consume el servicio
     * @return List
     */
    List<HisLoginDownEntity> generarData(String tipoArchivo);
}
