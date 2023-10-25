package com.bdb.moduleoplcancelaciones.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.HisTransaccionesEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PagosAutomaticosService {

    void pagosAutomaticos(HttpServletRequest http, Integer tipTransaccion, Long nroPordDestino, List<HisTransaccionesEntity> hisTransaccionesEntityList) throws Exception;
}
