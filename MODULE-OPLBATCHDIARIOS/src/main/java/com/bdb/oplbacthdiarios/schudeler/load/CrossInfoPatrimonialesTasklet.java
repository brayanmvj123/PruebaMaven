package com.bdb.oplbacthdiarios.schudeler.load;

import com.bdb.opaloshare.controller.service.interfaces.CrucePatrimonioDTO;
import com.bdb.opaloshare.persistence.entity.HisCDTSLargeEntity;
import com.bdb.opaloshare.persistence.entity.HisTranpgEntity;
import com.bdb.opaloshare.persistence.entity.SalPgdigitalDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositorySalPgdigitalDown;
import com.bdb.opaloshare.persistence.repository.RepositoryTransaccionesPago;
import com.bdb.oplbacthdiarios.controller.service.interfaces.DerechosPatrimonialesService;
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

public class CrossInfoPatrimonialesTasklet implements Tasklet {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DerechosPatrimonialesService derechosPatrimonialesService;

    @Autowired
    RepositorySalPgdigitalDown repoSalPgdigital;

    @Autowired
    RepositoryTransaccionesPago repositoryTransaccionesPago;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        logger.info("start repoSalPgdigital.deleteAll()...");
        repoSalPgdigital.deleteAll();

        List<CrucePatrimonioDTO> crecesToSal = repoSalPgdigital.loadInfoFromCrossData();
        crecesToSal.forEach(x -> logger.info("crecesToSal: {} {}", x.getNumCdt(), x.getCodIsin()));

        if (derechosPatrimonialesService.validarPeriodo()) {

            List<SalPgdigitalDownEntity> salItems = new ArrayList<>();
            List<HisTranpgEntity> hisTranpg = new ArrayList<>();

            try {

                crecesToSal.forEach(cru -> {

                    logger.info("start crecesToSal.forEach(cru ->)...");

                    SalPgdigitalDownEntity salpg = new SalPgdigitalDownEntity();

                    salpg.setNumCdt(cru.getNumCdt() != null ? new BigDecimal(cru.getNumCdt()) : new BigDecimal("0"));
                    salpg.setCodIsin(cru.getCodIsin() != null ? cru.getCodIsin() : "NULL");
                    salpg.setTipId(cru.getTipId() != null ? cru.getTipId() : "NULL");
                    salpg.setNumTit(cru.getNumTit() != null ? cru.getNumTit() : "NULL");
                    salpg.setNomTit(cru.getNomTit() != null ? cru.getNomTit() : "NULL");
                    salpg.setIntBruto(cru.getIntBruto() != null ? new BigDecimal(cru.getIntBruto()) : new BigDecimal("0"));
                    salpg.setRteFte(cru.getRteFte() != null ? new BigDecimal(cru.getRteFte()) : new BigDecimal("0"));
                    salpg.setIntNeto(cru.getIntNeto() != null ? new BigDecimal(cru.getIntNeto()) : new BigDecimal("0"));
                    salpg.setTotalPagar(cru.getTotalPagar() != null ? new BigDecimal(cru.getTotalPagar()) : new BigDecimal("0"));
                    salpg.setCapPg(cru.getCapPg() != null ? new BigDecimal(cru.getCapPg()) : new BigDecimal("0"));
                    salpg.setTipCta(cru.getDescTipCta() != null ? cru.getDescTipCta() : "NULL");
                    salpg.setNroCta(cru.getNroCta() != null ? cru.getNroCta() : "NULL");
                    salpg.setOficina(cru.getOficina() != null ? cru.getOficina() : "NULL");
                    salpg.setTipoTran(cru.getTipCta() != null ? Integer.parseInt(cru.getTipCta()) : 0);

                    logger.info("salpg: {}", salpg.toString());
                    salItems.add(salpg);

                    logger.info("preparando Data para insertar en hisTranpg: ");

                    /*if (new BigDecimal(cru.getCapPg()).compareTo(BigDecimal.ZERO) > 0) {
                        logger.info("cdt abono para pago {} - capital {}", cru.getNumCdt(), new BigDecimal(cru.getCapPg()));
                        hisTranpg.add(guardarHisTranpg(cru.getNumTit(), "2", cru.getNroCta() != null ? cru.getNroCta()
                                        : "NULL", cru.getNumTit(), cru.getOficina(), cru.getOficina(), cru.getNumCdt(),
                                cru.getCapPg(), "3", Integer.valueOf(cru.getTipCta())));
                    } else {
                        System.out.println("cdt abono para pago " + cru.getNumCdt() + " - " + " capital 0");
                        hisTranpg.add(guardarHisTranpg(cru.getNumTit(), "2", cru.getNroCta() != null ? cru.getNroCta()
                                        : "NULL", cru.getNumTit(), cru.getOficina(), cru.getOficina(), cru.getNumCdt(),
                                "0", "3", Integer.valueOf(cru.getTipCta())));
                    }
                    if (new BigDecimal(cru.getIntNeto()).compareTo(BigDecimal.ZERO) > 0) {
                        System.out.println("cdt abono para pago " + cru.getNumCdt() + " - " + " intereses " + new BigDecimal(cru.getIntNeto()));
                        hisTranpg.add(guardarHisTranpg(cru.getNumTit(), "2", cru.getNroCta() != null ? cru.getNroCta()
                                        : "NULL", cru.getNumTit(), cru.getOficina(), cru.getOficina(), cru.getNumCdt(),
                                cru.getIntNeto(), "4", Integer.valueOf(cru.getTipCta())));
                    } else {
                        System.out.println("cdt abono para pago " + cru.getNumCdt() + " - " + " intereses 0");
                        hisTranpg.add(guardarHisTranpg(cru.getNumTit(), "2", cru.getNroCta() != null ? cru.getNroCta()
                                        : "NULL", cru.getNumTit(), cru.getOficina(), cru.getOficina(), cru.getNumCdt(),
                                "0", "4", Integer.valueOf(cru.getTipCta())));
                    }
                    if (new BigDecimal(cru.getRteFte()).compareTo(BigDecimal.ZERO) > 0) {
                        System.out.println("cdt abono para pago " + cru.getNumCdt() + " - " + " rteFte " + new BigDecimal(cru.getRteFte()));
                        hisTranpg.add(guardarHisTranpg(cru.getNumTit(), "2", cru.getNumCdt(), cru.getNumTit(),
                                cru.getOficina(), cru.getOficina(), cru.getNumCdt(),
                                cru.getRteFte(), "5", 8));
                    } else {
                        System.out.println("cdt abono para pago " + cru.getNumCdt() + " - " + " rteFte 0");
                        hisTranpg.add(guardarHisTranpg(cru.getNumTit(), "2", cru.getNumCdt(), cru.getNumTit(),
                                cru.getOficina(), cru.getOficina(), cru.getNumCdt(),
                                "0", "5", 8));
                    }*/
                });

                logger.info("repoSalPgdigital.saveAll...");
                repoSalPgdigital.saveAll(salItems);
//                logger.info("repoHisTranpg.save...");
//                repositoryTransaccionesPago.saveAll(hisTranpg);

            } catch (Exception e) {
                logger.error("Error en CrossInfoPatrimonialesTasklet: {0}", e);
                throw new UnexpectedJobExecutionException("Error en CrossInfoPatrimonialesTasklet");
            }
        }else{
            logger.error("Error en CrossInfoPatrimonialesTasklet: El archivo no cumple con la validación del periodo");
            throw new UnexpectedJobExecutionException("Error en CrossInfoPatrimonialesTasklet: El archivo no cumple con " +
                    "la validación del periodo");
        }

        return RepeatStatus.FINISHED;
    }

    /**
     * Metodo encargado de guardar en la tabla {@link HisTranpgEntity} los valores con abono para pago
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
     * @return hisTranpgInfoCta
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
