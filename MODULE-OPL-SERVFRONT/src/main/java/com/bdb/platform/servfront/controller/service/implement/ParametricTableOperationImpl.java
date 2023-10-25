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

import com.bdb.opaloshare.persistence.entity.*;
import com.bdb.opaloshare.persistence.repository.*;
import com.bdb.platform.servfront.controller.service.interfaces.ParametricTableOperationService;
import com.bdb.platform.servfront.model.JSONSchema.*;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service encargada realizar operaciones a las tablas parametricas de la base de Datos
 *
 * @author: Esteban Talero
 * @version: 02/12/2020
 * @since: 02/12/2020
 */
@Service
@CommonsLog
public class ParametricTableOperationImpl implements ParametricTableOperationService {

    @Autowired
    RepositoryParTipBase repositoryParTipBase;

    @Autowired
    RepositoryParTipPeriod repositoryParTipPeriod;

    @Autowired
    RepositoryParTipTasa repositoryParTipTasa;

    @Autowired
    RepositoryParTipPlazo repositoryParTipPlazo;

    @Autowired
    RepositoryHisCalendarioDownEntity repositoryHisCalendarioDownEntity;

    @Override
    public List<JSONGetTipoBase> consultaTipoBase() {
        List<TipbaseParDownEntity> resultList = repositoryParTipBase.findAll();

        List<JSONGetTipoBase> resultListTipBase = new ArrayList<>();

        resultList.forEach(dataTipBase -> {
            JSONGetTipoBase getTipoBase = new JSONGetTipoBase();
            getTipoBase.setDescBase(dataTipBase.getDescBase());
            getTipoBase.setHomoDcvbta(dataTipBase.getHomoDcvbta());
            getTipoBase.setTipBase(dataTipBase.getTipBase());
            resultListTipBase.add(getTipoBase);
        });

        return resultListTipBase;
    }

    @Override
    public List<JSONGetTipoPeriodo> consultaTipoPeriodo() {
        List<TipPeriodParDownEntity> resultList = repositoryParTipPeriod.findAll();

        List<JSONGetTipoPeriodo> resultListTipPeriod = new ArrayList<>();

        resultList.forEach(dataTipPeriod -> {
            JSONGetTipoPeriodo getTipoPeriod = new JSONGetTipoPeriodo();
            getTipoPeriod.setDescPeriodicidad(dataTipPeriod.getDescPeriodicidad());
            getTipoPeriod.setHomoDcvbta(dataTipPeriod.getHomoDcvbta());
            getTipoPeriod.setHomoSimcuota(dataTipPeriod.getHomoSimcuota());
            getTipoPeriod.setTipPeriodicidad(dataTipPeriod.getTipPeriodicidad());
            resultListTipPeriod.add(getTipoPeriod);
        });

        return resultListTipPeriod;
    }

    @Override
    public List<JSONGetTipoTasa> consultaTipoTasa() {
        List<TiptasaParDownEntity> resultList = repositoryParTipTasa.findByDescTasaNotOrderByTipTasaAsc("IBR Overnight");
                //repositoryParTipTasa.findByOrderByTipTasaAsc();

        List<JSONGetTipoTasa> resultListTipTasa = new ArrayList<>();

        resultList.forEach(dataTipTasa -> {
            JSONGetTipoTasa getTipoBase = new JSONGetTipoTasa();
            getTipoBase.setDescTasa(dataTipTasa.getDescTasa());
            getTipoBase.setHomoDcvbta(dataTipTasa.getHomoDcvbta());
            getTipoBase.setTipTasa(dataTipTasa.getTipTasa());
            resultListTipTasa.add(getTipoBase);
        });

        return resultListTipTasa;
    }

    @Override
    public List<JSONGetTipoPlazo> consultaTipoPlazo() {
        List<TipplazoParDownEntity> resultList = repositoryParTipPlazo.findAll();

        List<JSONGetTipoPlazo> resultListTipPlazo = new ArrayList<>();

        resultList.forEach(dataTipPlazo -> {
            JSONGetTipoPlazo getTipoPlazo = new JSONGetTipoPlazo();
            getTipoPlazo.setDescPlazo(dataTipPlazo.getDescPlazo());
            getTipoPlazo.setHomoDcvbta(dataTipPlazo.getHomoDcvbta());
            getTipoPlazo.setTipPlazo(dataTipPlazo.getTipPlazo());
            resultListTipPlazo.add(getTipoPlazo);
        });

        return resultListTipPlazo;
    }

    @Override
    public List<JSONGetCalendario> consultaCalendarioAnnioNoHabil(int anno) {
        //dias no habiles 0
        System.out.println("año "+anno);
        List<OplHisCalendarioDownEntity> resultList = repositoryHisCalendarioDownEntity.findByFechaAnnoAndValor(anno,0);
        System.out.println("resultado");
        System.out.println(resultList.toString());
        List<JSONGetCalendario> resultListCalendario = new ArrayList<>();

        resultList.forEach(dataCalendario -> {
            JSONGetCalendario getCalendario = new JSONGetCalendario();
            getCalendario.setAnno(dataCalendario.getFecha().getAnno());
            getCalendario.setMes(dataCalendario.getFecha().getMes());
            getCalendario.setDia(dataCalendario.getFecha().getDia());
            getCalendario.setFechaValor(dataCalendario.getValor());
            resultListCalendario.add(getCalendario);
        });

        return resultListCalendario;
    }
}
