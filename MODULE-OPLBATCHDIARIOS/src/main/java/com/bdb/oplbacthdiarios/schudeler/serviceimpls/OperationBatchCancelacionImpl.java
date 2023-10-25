package com.bdb.oplbacthdiarios.schudeler.serviceimpls;

import com.bdb.opaloshare.persistence.entity.*;
import com.bdb.opaloshare.persistence.repository.*;
import com.bdb.oplbacthdiarios.persistence.jsonschema.request.cancelacionaut.JSONCancelacionAut;
import com.bdb.oplbacthdiarios.persistence.jsonschema.request.cancelacionaut.TransaccionPago;
import com.bdb.oplbacthdiarios.persistence.response.cancelacionaut.ResponsePagCdt;
import com.bdb.oplbacthdiarios.schudeler.services.OperationBatchCancelacion;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@CommonsLog
public class OperationBatchCancelacionImpl implements OperationBatchCancelacion {

    final RepositorySalPg repositorySalPg;
    final RepositoryTransaccionesPago repositoryTransaccionesPago;
    final RepositoryDatosCliente repositoryDatosCliente;
    final RepositoryTipId repositoryTipId;
    final RepositoryHisRenovaCdt repositoryHisRenovaCdt;
    final RepositoryHisCDTSCancelation repositoryHisCDTSCancelation;
    final RepositorySalPgdigitalDown repositorySalPgdigitalDown;
    final RepositoryTipVarentorno repositoryTipVarentorno;

    public OperationBatchCancelacionImpl(RepositorySalPg repositorySalPg,
                                         RepositoryTransaccionesPago repositoryTransaccionesPago,
                                         RepositoryDatosCliente repositoryDatosCliente,
                                         RepositoryTipId repositoryTipId,
                                         RepositoryHisRenovaCdt repositoryHisRenovaCdt,
                                         RepositoryHisCDTSCancelation repositoryHisCDTSCancelation,
                                         RepositorySalPgdigitalDown repositorySalPgdigitalDown,
                                         RepositoryTipVarentorno repositoryTipVarentorno) {
        this.repositorySalPg = repositorySalPg;
        this.repositoryTransaccionesPago = repositoryTransaccionesPago;
        this.repositoryDatosCliente = repositoryDatosCliente;
        this.repositoryTipId = repositoryTipId;
        this.repositoryHisRenovaCdt = repositoryHisRenovaCdt;
        this.repositoryHisCDTSCancelation = repositoryHisCDTSCancelation;
        this.repositorySalPgdigitalDown = repositorySalPgdigitalDown;
        this.repositoryTipVarentorno = repositoryTipVarentorno;
    }

    @Override
    public void consumoServicioCanAutDig(String host) {
        log.info("ENTRA AL CONSUMO DEL SERVICIO consumoServicioCanAutDig.");

        List<SalPgDownEntity> pgDownEntityList = repositorySalPg.findAllByEstadoAndCodProdIsBetween(4,
                3001, 3002);

        log.info("Se valida que los CDTs Digitales que se desean cancelar no se hayan pagado antes.");
        pgDownEntityList = pgDownEntityList.stream()
                .filter(cdt -> !repositoryHisCDTSCancelation.existsById(new HisCDTSCancelationPk(cdt.getCodIsin(),
                        cdt.getNumCdt(),
                        cdt.getNumTit())))
                .collect(Collectors.toList());

        log.info("Se valida la cantidad limite de CDTs Digitales para cancelar en el piloto.");
        long limite = Long.parseLong(repositoryTipVarentorno.findByDescVariable("LIMITE_PILOTO_CANCAUT")
                .getValVariable());

        log.info("Se valida la tabla SAL_PGDIGITAL para constatar las cancelaciones.");
        pgDownEntityList = pgDownEntityList.stream()
                .filter(cdt -> repositorySalPgdigitalDown.existsByCodIsinAndNumCdtAndNumTit(cdt.getCodIsin(),
                        new BigDecimal(cdt.getNumCdt()),
                        cdt.getNumTit()))
                .sorted(Comparator.comparing(SalPgDownEntity::getCapPg))
                .limit(limite)
                .collect(Collectors.toList());

        for (SalPgDownEntity item : pgDownEntityList) {

            HisTranpgEntity oplTipTranAndNumCta = hisTranpgInfoCta(item.getNumCdt());
            List<HisTranpgEntity> hisTranpgEntityList = recordsTranPgCancel(item,
                    oplTipTranAndNumCta.getNroPordDestino(),
                    oplTipTranAndNumCta.getOplTiptransTblTipTrasaccion());

            JSONCancelacionAut requestCancelAut = jsonCancelAut(item, oplTipTranAndNumCta, hisTranpgEntityList);
            ResponseEntity<ResponsePagCdt> response = getResponsePagCdt(requestCancelAut, host);

            log.warn("STATUS SERVICE PAG: " + response.getStatusCode());
            if (response.getStatusCode().value() == HttpStatus.OK.value()) {
                log.info("SE REALIZO EL PAGO DE FORMA EXITOSA ...");
                repositoryTransaccionesPago.saveAll(hisTranpgEntityList);
                repositorySalPg.updateStateValue(requestCancelAut.getNumCdt(), 1);
            } else {
                log.error("ERROR AL CONSUMIR EL SERVICIO DE PAGOS ...");
                repositoryTransaccionesPago.saveAll(cambioPagoCtaCont(item, hisTranpgEntityList));
                repositorySalPg.updateStateValue(requestCancelAut.getNumCdt(), 2);
                validateCdtCancel(requestCancelAut);

            }
        }
    }

    public ResponseEntity<ResponsePagCdt> getResponsePagCdt(JSONCancelacionAut jsonCancelacionAut, String host) {
        log.info("Se inicia el proceso getResponsePagCdt...");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setCacheControl(CacheControl.noCache());

        HttpEntity<JSONCancelacionAut> requestEntity = new HttpEntity<>(jsonCancelacionAut, httpHeaders);
        final String url = host + "OPLPAGOSAUT/CDTSDesmaterializado/v1/cancelacion/pagosaut";
//        "http://localhost:8080/CDTSDesmaterializado/v1/cancelacion/pagosaut";
        RestTemplate restTemplate = new RestTemplate();

        try{
            return restTemplate.exchange(url,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<ResponsePagCdt>() {
                    });
        } catch(HttpStatusCodeException e){
            log.error("Se tuvo error general en el consumo del servicio del BUS.");
            return ResponseEntity.status(500).body(new ResponsePagCdt(HttpStatus.INTERNAL_SERVER_ERROR,
                    LocalDateTime.now(), "Error general"));
        }

    }

    /**
     * SI EL PAGO FALLA DEBE GENERARSE LOS REGISTROS EN LA TRANPG A CUENTA CONTABLE.
     *
     * @param item
     * @param hisTranpgEntityList
     * @return
     */
    public List<HisTranpgEntity> cambioPagoCtaCont(SalPgDownEntity item, List<HisTranpgEntity> hisTranpgEntityList) {
        log.info("Se inicia el proceso cambioPagoCtaCont...");

        List<HisTranpgEntity> tranpgEntityList = new ArrayList<>();
        hisTranpgEntityList.stream()
                .filter(numcdt -> numcdt.getHisCDTSLargeEntity().getNumCdt().equals(item.getNumCdt().toString()))
                .forEach(cdt -> {
                    if (!cdt.getProceso().equals("5")) cdt.setOplTiptransTblTipTrasaccion(6);
                    else cdt.setOplTiptransTblTipTrasaccion(cdt.getOplTiptransTblTipTrasaccion());
                    tranpgEntityList.add(cdt);
                });
        return tranpgEntityList;
    }

    public JSONCancelacionAut jsonCancelAut(SalPgDownEntity item, HisTranpgEntity hisTranpgEntity,
                                            List<HisTranpgEntity> hisTranpgEntityList) {
        log.info("Se inicia el proceso jsonCancelAut...");

        JSONCancelacionAut jsonCancelacionAut = new JSONCancelacionAut();

        jsonCancelacionAut.setTipoId(getTipidParDownEntity(item.getNumTit()));

        jsonCancelacionAut.setNumeroId(item.getNumTit());
        jsonCancelacionAut.setNombreBeneficiario(item.getNomTit()); //VALIDAR
        jsonCancelacionAut.setNumCdt(item.getNumCdt());
        jsonCancelacionAut.setCodIsin(item.getCodIsin());
        jsonCancelacionAut.setOficinaOrigen(hisTranpgEntity.getUnidDestino()); //VALIDAR
        jsonCancelacionAut.setCtaInv(Long.parseLong(item.getCtaInv()));

        jsonCancelacionAut.setTipoCtaInv(hisTranpgEntity.getOplTiptransTblTipTrasaccion());

        jsonCancelacionAut.setNumCta(Long.parseLong(hisTranpgEntity.getNroPordDestino()));

        jsonCancelacionAut.setTipoCta(hisTranpgEntity.getOplTiptransTblTipTrasaccion());

        jsonCancelacionAut.setOficinaRadic(Integer.parseInt(hisTranpgEntity.getUnidOrigen())); //VALIDAR

        jsonCancelacionAut.setTransaccionPagoList(hisTranpgEntityList
                .stream()
                .filter(tranpg -> Long.parseLong(tranpg.getHisCDTSLargeEntity().getNumCdt()) == item.getNumCdt())
                .filter(proceso -> !proceso.getProceso().equals("5"))
                .map(x -> new TransaccionPago(Integer.parseInt(x.getProceso()), x.getValor()))
                .sorted(Comparator.comparing(TransaccionPago::getTipoPago))
                .collect(Collectors.toList()));

        return jsonCancelacionAut;
    }

    public HisTranpgEntity hisTranpgInfoCta(Long numCdt) {
        log.info("Se inicia el proceso hisTranpgInfoCta...");

        Optional<HisRenovaCdtEntity> byCdtAct = Optional.ofNullable(repositoryHisRenovaCdt.findByCdtAct(numCdt));
        if (byCdtAct.isPresent()) {
            log.info("SI ES UNA RENOVACION ..." + byCdtAct.get().getCdtOrigen());
            return repositoryTransaccionesPago.buscarTransaccionCdtDig(byCdtAct
                    .get().getCdtOrigen().toString(), "1");
        } else {
            log.info("NO ES RENOVACIÓN");
            return repositoryTransaccionesPago.buscarTransaccionCdtDig(numCdt.toString(), "1");
        }
    }

    public List<HisTranpgEntity> recordsTranPgCancel(SalPgDownEntity cru, String numCta, Integer tipTransaccion) {
        log.info("Se inicia el proceso recordsTranPgCancel...");

        List<HisTranpgEntity> hisTranpg = new ArrayList<>();

        HisTranpgEntity infoTranpgCdtAct = hisTranpgInfoCta(cru.getNumCdt());

        if (cru.getCapPg().compareTo(BigDecimal.ZERO) > 0) {
            log.info("cdt abono para pago {" + cru.getNumCdt() + "} - capital {" + cru.getCapPg() + "}");
            hisTranpg.add(guardarHisTranpg(cru.getNumTit(), "2", numCta != null ? numCta : "NULL",
                    cru.getNumTit(), infoTranpgCdtAct.getUnidOrigen(), infoTranpgCdtAct.getUnidDestino().toString(), cru.getNumCdt().toString(),
                    cru.getCapPg().toString(), "3", tipTransaccion));
        } else {
            log.info("cdt abono para pago " + cru.getNumCdt() + " - " + " capital 0");
            hisTranpg.add(guardarHisTranpg(cru.getNumTit(), "2", numCta != null ? numCta : "NULL",
                    cru.getNumTit(), infoTranpgCdtAct.getUnidOrigen(), infoTranpgCdtAct.getUnidDestino().toString(), cru.getNumCdt().toString(),
                    "0", "3", tipTransaccion));
        }
        if (cru.getIntNeto().compareTo(BigDecimal.ZERO) > 0) {
            log.info("cdt abono para pago " + cru.getNumCdt() + " - " + " intereses " + cru.getIntNeto());
            hisTranpg.add(guardarHisTranpg(cru.getNumTit(), "2", numCta != null ? numCta : "NULL",
                    cru.getNumTit(), infoTranpgCdtAct.getUnidOrigen(), infoTranpgCdtAct.getUnidDestino().toString(), cru.getNumCdt().toString(),
                    cru.getIntNeto().toString(), "4", tipTransaccion));
        } else {
            log.info("cdt abono para pago " + cru.getNumCdt() + " - " + " intereses 0");
            hisTranpg.add(guardarHisTranpg(cru.getNumTit(), "2", numCta != null ? numCta : "NULL",
                    cru.getNumTit(), infoTranpgCdtAct.getUnidOrigen(), infoTranpgCdtAct.getUnidDestino().toString(), cru.getNumCdt().toString(),
                    "0", "4", tipTransaccion));
        }
        if (cru.getRteFte().compareTo(BigDecimal.ZERO) > 0) {
            log.info("cdt abono para pago " + cru.getNumCdt() + " - " + " rteFte " + cru.getRteFte());
            hisTranpg.add(guardarHisTranpg(cru.getNumTit(), "2", cru.getNumCdt().toString(), cru.getNumTit(),
                    infoTranpgCdtAct.getUnidOrigen(), infoTranpgCdtAct.getUnidDestino().toString(), cru.getNumCdt().toString(),
                    cru.getRteFte().toString(), "5", 8));
        } else {
            log.info("cdt abono para pago " + cru.getNumCdt() + " - " + " rteFte 0");
            hisTranpg.add(guardarHisTranpg(cru.getNumTit(), "2", cru.getNumCdt().toString(), cru.getNumTit(),
                    infoTranpgCdtAct.getUnidOrigen(), infoTranpgCdtAct.getUnidDestino().toString(), cru.getNumCdt().toString(),
                    "0", "5", 8));
        }

        return hisTranpg;
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
    private HisTranpgEntity guardarHisTranpg(String idCliente, String tipTran, String nroPord, String idBen,
                                             String unidadDestino, String unidadOrigen, String numCdt, String valor,
                                             String proceso, Integer tblTipTransa) {
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

    /**
     * Se valida la información del cliente expuesta en el tabla SAL_PG con la data expuesta en la tabla HIS_CLIENTES,
     * se valida los el número y el tipo de identificación, este ultimo se homologa segun el valor del tipo de
     * identificación para CRM.
     *
     * @param numTit Número de identificación del cliente.
     * @return El tipo de identificación homologado en CRM.
     */
    private String getTipidParDownEntity(String numTit) {
        log.info("Se inicia la validación de los datos basicos del cliente.");
        Optional<HisClientesLargeEntity> clienteById = repositoryDatosCliente.findById(numTit);
        if (clienteById.isPresent()) {
            Integer codId = clienteById.get().getOplTipidTblCodId();
            Optional<TipidParDownEntity> byId = repositoryTipId.findById(codId);
            if (byId.isPresent()) return byId.get().getHomoCrm();
        }
        return null;
    }

    private void validateCdtCancel(JSONCancelacionAut requestCancelAut){
        log.info("Se valida si el CDT Fallido al momento de cancelar fue agregado a la tabla.");
        if(!repositoryHisCDTSCancelation.existsById(new HisCDTSCancelationPk(requestCancelAut.getCodIsin(),
                requestCancelAut.getNumCdt(), requestCancelAut.getNumeroId()))){
            log.info("El CDT no se encuentra registrado, se marcara como Error general al momento del pago");
            repositoryHisCDTSCancelation.save(new HisCDTSCancelationEntity(requestCancelAut.getCodIsin(),
                    requestCancelAut.getNumCdt(), requestCancelAut.getNumeroId(), "ERROR GENERAL",
                    LocalDateTime.now()));
        }

    }


}
