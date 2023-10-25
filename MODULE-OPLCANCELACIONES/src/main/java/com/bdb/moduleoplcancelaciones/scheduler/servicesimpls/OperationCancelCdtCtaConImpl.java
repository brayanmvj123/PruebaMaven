package com.bdb.moduleoplcancelaciones.scheduler.servicesimpls;

import com.bdb.moduleoplcancelaciones.scheduler.service.OperationCancelCdtaCtaCon;
import com.bdb.opaloshare.persistence.entity.*;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opaloshare.persistence.repository.*;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@CommonsLog
public class OperationCancelCdtCtaConImpl implements OperationCancelCdtaCtaCon {

    final RepositorySalPg repositorySalPg;
    final RepositoryTransacciones repositoryTransacciones;

    public OperationCancelCdtCtaConImpl(RepositorySalPg repositorySalPg, RepositoryTransacciones repositoryTransacciones) {
        this.repositorySalPg = repositorySalPg;
        this.repositoryTransacciones = repositoryTransacciones;
    }

    @Override
    public void cancelarCdtCtaContables(String host) {
        log.info("ENTRA AL CONSUMO DEL SERVICIO cancelarCdtCtaContables.");

        //FALTA AGREGAR LA RESTRICCION DE COD PRODUCTO DIFERENTE AL DE TESORERIA
        List<SalPgDownEntity> pgDownEntityList = repositorySalPg.findAllByEstadoAndCodProdIsNot(4, 3001);

        for (SalPgDownEntity item : pgDownEntityList) {

//            HisTranpgEntity tranpagNumCdt = hisTranpgInfoCta(item.getNumCdt());
            List<HisTransaccionesEntity> hisTranpgEntityList = registrosTranPgCancel(item,
                    "000000000",
                    6);

            ResponseEntity<RequestResult<Map<String, List<SalTramastcDownEntity>>>> response = getResponsePagCdt(host);

            log.warn("STATUS SERVICE PAG: " + response.getStatusCode());
            if (response.getStatusCode().value() == HttpStatus.OK.value()) {
                log.info("SE REALIZO EL PAGO DE FORMA EXITOSA ...");
                repositoryTransacciones.saveAll(hisTranpgEntityList);
            } else {
                log.error("ERROR AL CONSUMIR EL SERVICIO DE PAGOS ...");
                repositoryTransacciones.saveAll(hisTranpgEntityList);
            }
        }
    }

    public ResponseEntity<RequestResult<Map<String, List<SalTramastcDownEntity>>>> getResponsePagCdt(String host) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setCacheControl(CacheControl.noCache());

        HttpEntity<RequestResult<Map<String, List<SalTramastcDownEntity>>>> requestEntity = new HttpEntity<>(null, httpHeaders);
        final String url = //host + "OPLPAGOSAUT/CDTSDesmaterializado/v1/cancelacion/pagosaut";
                "http://localhost:8080/CDTSDesmaterializado/v1/cancelacion/pagosaut";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<RequestResult<Map<String, List<SalTramastcDownEntity>>>>() {
                });
    }

    public List<HisTransaccionesEntity> registrosTranPgCancel(SalPgDownEntity cru, String numCta, Integer tipTransaccion) {
        List<HisTransaccionesEntity> hisTranpg = new ArrayList<>();

        if (cru.getCapPg().compareTo(BigDecimal.ZERO) > 0) {
            log.info("cdt abono para pago {" + cru.getNumCdt() + "} - capital {" + cru.getCapPg() + "}");
            hisTranpg.add(guardarHisTranpg(cru.getNumTit(), 2, numCta != null ? numCta : "NULL",
                    cru.getNumTit(), cru.getNroOficina().toString(), cru.getNroOficina().toString(), cru.getNumCdt().toString(),
                    cru.getCapPg().toString(), 3, tipTransaccion));
        } else {
            log.info("cdt abono para pago " + cru.getNumCdt() + " - " + " capital 0");
            hisTranpg.add(guardarHisTranpg(cru.getNumTit(), 2, numCta != null ? numCta : "NULL",
                    cru.getNumTit(), cru.getNroOficina().toString(), cru.getNroOficina().toString(), cru.getNumCdt().toString(),
                    "0", 3, tipTransaccion));
        }
        if (cru.getIntNeto().compareTo(BigDecimal.ZERO) > 0) {
            log.info("cdt abono para pago " + cru.getNumCdt() + " - " + " intereses " + cru.getIntNeto());
            hisTranpg.add(guardarHisTranpg(cru.getNumTit(), 2, numCta != null ? numCta : "NULL",
                    cru.getNumTit(), cru.getNroOficina().toString(), cru.getNroOficina().toString(), cru.getNumCdt().toString(),
                    cru.getIntNeto().toString(), 4, tipTransaccion));
        } else {
            log.info("cdt abono para pago " + cru.getNumCdt() + " - " + " intereses 0");
            hisTranpg.add(guardarHisTranpg(cru.getNumTit(), 2, numCta != null ? numCta : "NULL",
                    cru.getNumTit(), cru.getNroOficina().toString(), cru.getNroOficina().toString(), cru.getNumCdt().toString(),
                    "0", 4, tipTransaccion));
        }
        if (cru.getRteFte().compareTo(BigDecimal.ZERO) > 0) {
            log.info("cdt abono para pago " + cru.getNumCdt() + " - " + " rteFte " + cru.getRteFte());
            hisTranpg.add(guardarHisTranpg(cru.getNumTit(), 2, cru.getNumCdt().toString(), cru.getNumTit(),
                    cru.getNroOficina().toString(), cru.getNroOficina().toString(), cru.getNumCdt().toString(),
                    cru.getRteFte().toString(), 5, 8));
        } else {
            log.info("cdt abono para pago " + cru.getNumCdt() + " - " + " rteFte 0");
            hisTranpg.add(guardarHisTranpg(cru.getNumTit(), 2, cru.getNumCdt().toString(), cru.getNumTit(),
                    cru.getNroOficina().toString(), cru.getNroOficina().toString(), cru.getNumCdt().toString(),
                    "0", 5, 8));
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
    private HisTransaccionesEntity guardarHisTranpg(String idCliente, Integer tipTran, String nroPord, String idBen, String unidadOrigen,
                                             String unidadDestino, String numCdt, String valor, Integer proceso, Integer tblTipTransa) {
        HisTransaccionesEntity hisTransaccionesEntity = new HisTransaccionesEntity();
        hisTransaccionesEntity.setIdCliente(Long.valueOf(idCliente));
        hisTransaccionesEntity.setOplTiptranTblTipTran(tipTran);
        hisTransaccionesEntity.setNroPordDestino(nroPord);
        hisTransaccionesEntity.setIdBeneficiario(Long.valueOf(idBen));
        hisTransaccionesEntity.setUnidOrigen(unidadOrigen);
        hisTransaccionesEntity.setUnidDestino(unidadDestino);
        hisTransaccionesEntity.setValor(new BigDecimal(valor));
        hisTransaccionesEntity.setOplTipprocesoTblTipProceso(proceso);
        hisTransaccionesEntity.setOplTiptransTblTipTransaccion(tblTipTransa);
        return hisTransaccionesEntity;
    }

}
