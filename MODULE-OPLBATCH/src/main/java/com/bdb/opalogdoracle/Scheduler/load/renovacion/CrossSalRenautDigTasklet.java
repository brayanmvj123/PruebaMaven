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
package com.bdb.opalogdoracle.Scheduler.load.renovacion;

import com.bdb.opaloshare.controller.service.interfaces.CrucePatrimonioRenautDTO;
import com.bdb.opaloshare.persistence.entity.HisCDTSLargeEntity;
import com.bdb.opaloshare.persistence.entity.HisTranpgEntity;
import com.bdb.opaloshare.persistence.entity.SalRenautdigEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryHisRenovaCdt;
import com.bdb.opaloshare.persistence.repository.RepositorySalRenautdig;
import com.bdb.opaloshare.persistence.repository.RepositoryTransaccionesPago;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tasklet encargado de limpiar y almacenar los registros de marcacion renovacion CDT DIG
 *
 * @author: Esteban Talero
 * @version: 10/10/2020
 * @since: 09/10/2020
 */
public class CrossSalRenautDigTasklet implements Tasklet {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RepositorySalRenautdig repositorySalRenautdig;

    @Autowired
    RepositoryTransaccionesPago repositoryTransaccionesPago;

    @Autowired
    private RepositoryHisRenovaCdt repositoryHisRenovaCdt;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {


        try {
            logger.info("start repositorySalRenautdig.findAll()...");
            List<SalRenautdigEntity> listSalRenautDig = repositorySalRenautdig.findAll();

            logger.info("Busca los CDTs con estado P en la tabla de salidaRenaut ");
            List<SalRenautdigEntity> cdtEstadoC =
                    listSalRenautDig.stream().filter(name -> name.getEstadoV().equals("P")).collect(Collectors.toList());
            
            if (cdtEstadoC.size()<1) {
                logger.info("start repositorySalRenautdig.deleteAll()...");
                repositorySalRenautdig.deleteAll(listSalRenautDig);
            }

            List<CrucePatrimonioRenautDTO> crucePatRenautList = repositorySalRenautdig.loadInfoFromCrossRenautData();
            logger.info("crucePatRenautList: " + crucePatRenautList.toString());
            List<SalRenautdigEntity> salRenautdig = new ArrayList<>();
            List<HisTranpgEntity> hisTranpg = new ArrayList<>();

            crucePatRenautList.forEach(dataSalRenaut -> {
                logger.info("start crucePatRenautList.forEach(dataSalRenaut ->)...");
                SalRenautdigEntity cdtExisteSalRenaut = repositorySalRenautdig.findByNumCdt(Long.valueOf(dataSalRenaut.getNumCdt()));
                logger.info("Consulto exitosamente si el CDT existe o no en la tabla de salida de renautDig... ");
                if (cdtExisteSalRenaut == null) {
                    logger.info("CDT SE GUARDA EN SALRENAUT DADO QUE NO EXISTIA EN ESTA TABLA... ");
                    SalRenautdigEntity salRenautDig = new SalRenautdigEntity();

                    salRenautDig.setCodIsin(dataSalRenaut.getCodIsin());
                    salRenautDig.setNumCdt(Long.valueOf(dataSalRenaut.getNumCdt()));
                    salRenautDig.setTipId(!dataSalRenaut.getTipId().equals("CC") ? "CC" : dataSalRenaut.getTipId() );
                    salRenautDig.setNumTit(dataSalRenaut.getNumTit());
                    salRenautDig.setNomTit(dataSalRenaut.getNomTit());
                    salRenautDig.setCapPg(new BigDecimal(dataSalRenaut.getCapPg()));
                    salRenautDig.setIntBruto(new BigDecimal(dataSalRenaut.getIntBruto()));
                    salRenautDig.setRteFte(new BigDecimal(dataSalRenaut.getRteFte()));
                    salRenautDig.setIntNeto(new BigDecimal(dataSalRenaut.getIntNeto()));
                    salRenautDig.setFormaPago("RENOVACION_DIGITAL");
                    salRenautDig.setEstadoV("P");


                    logger.info("salRenautdig: " + salRenautDig.toString());
                    salRenautdig.add(salRenautDig);

                    logger.info("preparando Data para insertar en hisTranpg: ");

                    if (new BigDecimal(dataSalRenaut.getCapPg()).compareTo(BigDecimal.ZERO) > 0) {
                        System.out.println("cdt " + dataSalRenaut.getNumCdt() + " - " + " capital " + new BigDecimal(dataSalRenaut.getCapPg()));
                        hisTranpg.add(guardarHisTranpg(dataSalRenaut.getNumTit(), "2", dataSalRenaut.getNumCdt(), dataSalRenaut.getNumTit(),
                                dataSalRenaut.getOficina(), dataSalRenaut.getOficina(), dataSalRenaut.getNumCdt(),
                                dataSalRenaut.getCapPg(), "3", 7));
                    } else {
                        System.out.println("cdt " + dataSalRenaut.getNumCdt() + " - " + " capital 0");
                        hisTranpg.add(guardarHisTranpg(dataSalRenaut.getNumTit(), "2", dataSalRenaut.getNumCdt(), dataSalRenaut.getNumTit(),
                                dataSalRenaut.getOficina(), dataSalRenaut.getOficina(), dataSalRenaut.getNumCdt(),
                                "0", "3", 7));
                    }
                    if (new BigDecimal(dataSalRenaut.getIntNeto()).compareTo(BigDecimal.ZERO) > 0) {
                        System.out.println("cdt " + dataSalRenaut.getNumCdt() + " - " + " intereses " + new BigDecimal(dataSalRenaut.getIntNeto()));
                        hisTranpg.add(guardarHisTranpg(dataSalRenaut.getNumTit(), "2", dataSalRenaut.getNumCdt(), dataSalRenaut.getNumTit(),
                                dataSalRenaut.getOficina(), dataSalRenaut.getOficina(), dataSalRenaut.getNumCdt(),
                                dataSalRenaut.getIntNeto(), "4", 7));
                    } else {
                        System.out.println("cdt " + dataSalRenaut.getNumCdt() + " - " + " intereses 0");
                        hisTranpg.add(guardarHisTranpg(dataSalRenaut.getNumTit(), "2", dataSalRenaut.getNumCdt(), dataSalRenaut.getNumTit(),
                                dataSalRenaut.getOficina(), dataSalRenaut.getOficina(), dataSalRenaut.getNumCdt(),
                                "0", "4", 7));
                    }
                    if (new BigDecimal(dataSalRenaut.getRteFte()).compareTo(BigDecimal.ZERO) > 0) {
                        System.out.println("cdt " + dataSalRenaut.getNumCdt() + " - " + " rteFte " + new BigDecimal(dataSalRenaut.getRteFte()));
                        hisTranpg.add(guardarHisTranpg(dataSalRenaut.getNumTit(), "2", dataSalRenaut.getNumCdt(), dataSalRenaut.getNumTit(),
                                dataSalRenaut.getOficina(), dataSalRenaut.getOficina(), dataSalRenaut.getNumCdt(),
                                dataSalRenaut.getRteFte(), "5", 8));
                    } else {
                        System.out.println("cdt " + dataSalRenaut.getNumCdt() + " - " + " rteFte 0");
                        hisTranpg.add(guardarHisTranpg(dataSalRenaut.getNumTit(), "2", dataSalRenaut.getNumCdt(), dataSalRenaut.getNumTit(),
                                dataSalRenaut.getOficina(), dataSalRenaut.getOficina(), dataSalRenaut.getNumCdt(),
                                "0", "5", 8));
                    }
                }
            });
            if (salRenautdig.size() > 0) {
                logger.info("repositorySalRenautdig.saveAll...");
                repositorySalRenautdig.saveAll(salRenautdig);
                long validarRenovacion = salRenautdig.stream()
                        .filter(item -> repositoryHisRenovaCdt.existsByCdtAnt(item.getNumCdt())).count();
                logger.info("CANTIDAD DE CDTS DIGITALES EXISTENTES: {}", validarRenovacion);
                if (validarRenovacion == 0){
                    logger.info("repoHisTranpg.save...");
                    repositoryTransaccionesPago.saveAll(hisTranpg);
                }
            }

        } catch (Exception e) {
            logger.error("Error en CrossRenautDigTasklet: " + e);
            throw new UnexpectedJobExecutionException("Error en CrossRenautDigTasklet");
        }
        return RepeatStatus.FINISHED;
    }

    /**
     * Metodo encargado de guardar en la tabla {@link HisTranpgEntity} los valores monetarios que seran usados
     * en el traductor CADI, los campos a guardar son
     * Capital
     * Intereses Netos
     * Rte Fuente
     *
     * @param idCliente     Identificador del Cliente
     * @param tipTran       Tipo de Transaccion
     * @param nroPord       Numero de Producto
     * @param idBen         Identificador del Beneficiario
     * @param unidadOrigen  Unidad Origen
     * @param unidadDestino Unidad Destino
     * @param numCdt        Numero de Cdt
     * @param valor         Valor en pesos
     * @param proceso       Proceso
     * @param tblTipTransa  Tipo Transaccion
     * @return hisTranpgEntity
     */
    private HisTranpgEntity guardarHisTranpg(String idCliente, String tipTran, String nroPord, String idBen, String unidadOrigen,
                                             String unidadDestino, String numCdt, String valor, String proceso, Integer tblTipTransa) {
        HisTranpgEntity hisTranpgEntity = new HisTranpgEntity();
        HisCDTSLargeEntity hisCDTSLargeEntity = new HisCDTSLargeEntity();
        hisTranpgEntity.setIdCliente(Long.valueOf(idCliente));
        hisTranpgEntity.setTipTran(tipTran);
        hisTranpgEntity.setNroPordDestino(nroPord);
        hisTranpgEntity.setIdBeneficiario(Long.valueOf(idBen));
        hisTranpgEntity.setUnidOrigen(unidadOrigen);
        hisTranpgEntity.setUnidDestino(Integer.valueOf(unidadDestino));
        hisCDTSLargeEntity.setNumCdt(numCdt);
        hisTranpgEntity.setHisCDTSLargeEntity(hisCDTSLargeEntity);
        hisTranpgEntity.setValor(new BigDecimal(valor));
        hisTranpgEntity.setProceso(proceso);
        hisTranpgEntity.setOplTiptransTblTipTrasaccion(tblTipTransa);
        return hisTranpgEntity;
    }
}
