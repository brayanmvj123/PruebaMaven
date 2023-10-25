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
package com.bdb.opalo.control.controller.service.implement;

import com.bdb.opalo.control.controller.service.interfaces.ControlCdtService;
import com.bdb.opalo.control.persistence.Constants.ConstantsControlCdt;
import com.bdb.opalo.control.persistence.dto.ControlCdtDto;
import com.bdb.opalo.control.persistence.exception.ControlCdtsException;
import com.bdb.opaloshare.persistence.entity.*;
import com.bdb.opaloshare.persistence.repository.OplMaedcvTmpDownTblRepository;
import com.bdb.opaloshare.persistence.repository.RepositoryCDTSLarge;
import com.bdb.opaloshare.persistence.repository.RepositoryHisCtrCdts;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

/**
 * Service encargado del Control renovacion CDT
 *
 * @author: Esteban Talero
 * @version: 23/10/2020
 * @since: 01/10/2020
 */
@Service
@CommonsLog
public class ControlCdtImpl implements ControlCdtService {

    /**
     * Repository para manejar el control de la renavocacion marcacion del CDT
     */
    @Autowired
    RepositoryHisCtrCdts repositoryHisCtrCdts;

    /**
     * Repository encargado de buscar si el CDT aplica para renovacion
     */
    @Autowired
    RepositoryCDTSLarge repositoryCDTSLarge;

    /**
     * Repository encargado de buscar alguna variable de Entorno
     */
    @Autowired
    RepositoryTipVarentorno repositoryTipVarentorno;

    /**
     * Repository encargado de buscar el CDT en la tabla TMP mae
     */
    @Autowired
    OplMaedcvTmpDownTblRepository repositoryMaeCdtsTmpDownTbl;

    /**
     * Clase constants
     */
    ConstantsControlCdt constantsControlCdt = new ConstantsControlCdt();


    /**
     * Metodo encargado de guardar o actualizar la marcacion de la renovacion CDT
     *
     * @param controlCdtDto objeto de entrada con los datos del CDT a marcar renovacion
     * @return boolean
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean controlCdtMarcacion(ControlCdtDto controlCdtDto) throws ControlCdtsException {
        List<HisCDTSLargeEntity> listaCdt = repositoryCDTSLarge.findByNumCdt(controlCdtDto.getParametros().getNumCdt().toString());
        //Valida que el CDT este en la tabla Historica
        if (listaCdt.isEmpty()) {
            throw new ControlCdtsException(204, "Cdt no aplica para renovacion automatica", controlCdtDto);
        }
        for (HisCDTSLargeEntity hisCdt : listaCdt) {
            if (hisCdt.getOplEstadosTblTipEstado() == 3) {
                //Metodo que orquesta las validaciones de CDT digital para saber si se puede renovar
                orquestacionValidacionesControlCdt(controlCdtDto);
            }
        }
        //Si el CDT cumple las condiciones se procede a guardar o actualizar la marcacion
        creaOmodificaCtrCdt(controlCdtDto);
        return true;
    }

    /**
     * Metodo encargado de orquestar las validaciones del CDT si aplica o no para renovacion automatica
     * Valida si el Cdt esta en estado 3 es decir Constituido
     * Valida si cumple la fecha de proximo pago
     * Valida si el Cdt esta registrado en la tabla Historica de CDTS digitales
     *
     * @param controlCdtDto parametros recibios en el request de la peticion
     * @throws ControlCdtsException exception de negocio
     */
    private void orquestacionValidacionesControlCdt(ControlCdtDto controlCdtDto) throws ControlCdtsException {

        //Consulta los dias habiles para renovar el CDT en la tabla de varEntorno
        Integer diasHabilesRenovacion = Integer.parseInt(repositoryTipVarentorno
                .findByDescVariable("DIAS_RENOVACION").getValVariable());

        //Consulta el CDT y sus datos en la tabla TMP_MAE
        List<MaeDCVTempDownEntity> listaMaeCdtsDigitales = repositoryMaeCdtsTmpDownTbl.findByNumCdt(controlCdtDto.getParametros()
                .getNumCdt().toString(), controlCdtDto.getParametros().getNumTit());

        //Valida que la fecha de proximo pago no este en los dias habiles(Este valor esta en la varEntorno)
        if (Optional.ofNullable(listaMaeCdtsDigitales).isPresent()) {
            if (listaMaeCdtsDigitales.size() > 0) {
                for (MaeDCVTempDownEntity maeCdt : listaMaeCdtsDigitales) {
                    DateTimeFormatter dateFechaProxPagFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate dateFechaProxPag = LocalDate.parse(maeCdt.getFechaProxPg(), dateFechaProxPagFormat);
                    if (esDisponible(dateFechaProxPag, diasHabilesRenovacion) == 2) {
                        throw new ControlCdtsException(204,
                                "De acuerdo a la fecha de proximo pago del CDT no se puede renovar", controlCdtDto);
                    }
                }
            } else {
                throw new ControlCdtsException(204,
                        "De acuerdo a la fecha de proximo pago del CDT no se puede renovar", controlCdtDto);
            }
        }
    }

    /**
     * El metodo valida si el CDT Digital se encuentra disponible para renovación.
     *
     * @param fechaProxPg           Es tipo LocalDate.
     * @param diasHabilesRenovacion Busca en la tabla {@link VarentornoDownEntity} el minimo de dias
     *                              para realizar la validación.
     * @return 1 (UNO) DISPONIBLE para la renovación ó 2 (DOS) NO DISPONIBLE para la renovación
     */
    public Integer esDisponible(LocalDate fechaProxPg, Integer diasHabilesRenovacion) {
        return ChronoUnit.DAYS.between(LocalDate.now(), fechaProxPg) > diasHabilesRenovacion
                ? constantsControlCdt.disponible : constantsControlCdt.noDisponible;
    }

    /**
     * Metodo encargado de consultar en la base de datos por numero de CDT y retornar los registros correspondientes
     * a el y guarda o modifica para marcarlos con el codigo de control recibido en el request de este servicio
     *
     * @param controlCdtDto objeto de entrada del servicio
     */
    private void creaOmodificaCtrCdt(ControlCdtDto controlCdtDto) throws ControlCdtsException {
        //Busca los registro del CDT en la tabla de Historia de Controles CDTs digitales
        List<HisCtrCdtsEntity> registroCdtControl = repositoryHisCtrCdts.findByNumCdtAndParControlesEntity(controlCdtDto.getParametros().getNumCdt(),
                new ParControlesEntity(controlCdtDto.getParametros().getCodMarcacion()));

        if (Optional.ofNullable(registroCdtControl).isPresent()) {
            //Si el CDT ya existia en la tabla de controles y solo es actualizar los datos como por ejemplo el tipo
            //de control
            if (registroCdtControl.size() > 0) {
                for (HisCtrCdtsEntity dataCtrCdt : registroCdtControl) {
                    if (dataCtrCdt.getFechaModificacion().toLocalDate().isEqual(LocalDate.now())) {
                        throw new ControlCdtsException(409,
                                "El servicio de controlCDTs solo se puede consumir una vez al día", controlCdtDto);
                    }
                    actualizarCtrCdt(controlCdtDto, dataCtrCdt);
                }
            } else {
                // Si el CDT se va a marcar por primera vez en la tabla de controles
                crearCtrCdt(controlCdtDto, controlCdtDto.getParametros().getNumTit());
            }
        }
    }

    /**
     * Metodo encargado de mapear el objeto de entrada con el objeto entity para guardar en la
     * base de datos la marcacion de renovacion CDT
     *
     * @param controlCdtDto objeto de entrada del servicio
     * @param numTit        numero de titular del CDT
     */
    private void crearCtrCdt(ControlCdtDto controlCdtDto, String numTit) {
        HisCtrCdtsEntity hisCtrCDTSLarge = new HisCtrCdtsEntity();
        ParControlesEntity parControlesEntity = new ParControlesEntity();
        hisCtrCDTSLarge.setNumCdt(controlCdtDto.getParametros().getNumCdt());
        hisCtrCDTSLarge.setNumTit(numTit);
        hisCtrCDTSLarge.setFechaCreacion(LocalDateTime.now());
        hisCtrCDTSLarge.setCodCut(controlCdtDto.getParametros().getCodCut());
        hisCtrCDTSLarge.setNovedadV(controlCdtDto.getParametros().getNovedadV());
        hisCtrCDTSLarge.setFechaModificacion(LocalDateTime.now());
        hisCtrCDTSLarge.setDescripcion((controlCdtDto.getParametros().getDescripcion() == null) ? " " :
                controlCdtDto.getParametros().getDescripcion());
        parControlesEntity.setTipControl(controlCdtDto.getParametros().getCodMarcacion());
        hisCtrCDTSLarge.setParControlesEntity(parControlesEntity);

        repositoryHisCtrCdts.save(hisCtrCDTSLarge);
    }

    /**
     * Metodo encargado de actualizar en la base de datos la marcacion de renovacion CDT
     *
     * @param controlCdtDto objeto de entrada del servicio
     * @param dataCtrCdt    numero de titular del CDT
     */
    private void actualizarCtrCdt(ControlCdtDto controlCdtDto, HisCtrCdtsEntity dataCtrCdt) {
        repositoryHisCtrCdts.updateByNumCdt(dataCtrCdt.getFechaCreacion(),
                (controlCdtDto.getParametros().getNovedadV() == null) ? dataCtrCdt.getNovedadV() : controlCdtDto.getParametros().getNovedadV(),
                LocalDateTime.now(),
                (controlCdtDto.getParametros().getDescripcion() == null) ? " " : controlCdtDto.getParametros().getDescripcion(),
                controlCdtDto.getParametros().getCodMarcacion().intValue(),
                (controlCdtDto.getParametros().getCodCut() == null) ? dataCtrCdt.getCodCut() : controlCdtDto.getParametros().getCodCut(),
                controlCdtDto.getParametros().getNumCdt(),
                controlCdtDto.getParametros().getNumTit(),
                controlCdtDto.getParametros().getCodMarcacion());
    }
}
