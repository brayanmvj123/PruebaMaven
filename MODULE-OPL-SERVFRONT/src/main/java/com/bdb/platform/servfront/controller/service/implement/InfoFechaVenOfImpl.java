/*
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Esteban Talero <etalero@bancodebogota.com.co>.
 */
package com.bdb.platform.servfront.controller.service.implement;

import com.bdb.opaloshare.persistence.model.columnselected.ColumnsReportPgWeekly;
import com.bdb.opaloshare.persistence.model.jsonschema.semanal.JSONGetSalPgSemanal;
import com.bdb.opaloshare.persistence.repository.RepositorySalPg;
import com.bdb.opaloshare.persistence.repository.RepositorySalPgSemanalDown;
import com.bdb.platform.servfront.controller.service.interfaces.InfoFechaVenOfiService;
import com.bdb.platform.servfront.mapper.Mapper;
import com.bdb.platform.servfront.model.JSONSchema.JSONGetSalPgDiaria;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service encargada realizar la consulta a las tabla de salida pgSemanal de la base de Datos
 *
 * @author: Andres Marles
 * @version: 07/12/2021
 * @since: 07/12/2021
 */
@Service
@CommonsLog
public class InfoFechaVenOfImpl implements InfoFechaVenOfiService {

    @Autowired
    RepositorySalPgSemanalDown repositorySalPgSemanalDown;

    @Autowired
    RepositorySalPg repositorySalPg;

    @Autowired
    Mapper mapper;

    @Override
    public List<JSONGetSalPgSemanal> consultaDataFechaVenOfiSemanal() {
        List<ColumnsReportPgWeekly> resultList = repositorySalPgSemanalDown.cdtsConciliacion();
        return mapper.listColumnsReportPgWeeklytoJSONGetSalPgDSemanal(resultList);
    }

    @Override
    public List<JSONGetSalPgDiaria> consultaDataFechaVenOfi() {
        List<ColumnsReportPgWeekly> resultList = repositorySalPg.cdtsConciliacion();
        return mapper.listColumnsReportPgWeeklytoJSONGetSalPgDiaria(resultList);
    }

    @Override
    public Page<JSONGetSalPgDiaria> consultaDataFechaVenOfiPagination(Pageable pageable) {
        Page<ColumnsReportPgWeekly> salpg = repositorySalPg.cdtsConciliacionPag(pageable);
        List<JSONGetSalPgDiaria> jsonGetSalPgDiariaList = mapper.listColumnsReportPgWeeklytoJSONGetSalPgDiaria(salpg.getContent());
        return new PageImpl<>(jsonGetSalPgDiariaList, pageable, salpg.getTotalElements());
    }
}
