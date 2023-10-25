package com.bdb.moduleoplcancelaciones.controller.service.implement;

import com.bdb.moduleoplcancelaciones.controller.service.interfaces.PagosAutomaticosService;
import com.bdb.moduleoplcancelaciones.persistence.JSONSchema.request.JSONCancelacionAut;
import com.bdb.moduleoplcancelaciones.persistence.JSONSchema.request.TransaccionPago;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.HisInfoClienteEntity;
import com.bdb.opaloshare.persistence.entity.HisTransaccionesEntity;
import com.bdb.opaloshare.persistence.entity.SalPgDownEntity;
import com.bdb.opaloshare.persistence.entity.TipidParDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryInfoClienteHis;
import com.bdb.opaloshare.persistence.repository.RepositorySalPg;
import com.bdb.opaloshare.persistence.repository.RepositoryTipId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@CommonsLog
public class PagosAutomaticosServiceImpl implements PagosAutomaticosService {

    @Autowired
    SharedService sharedService;

    @Autowired
    private RepositorySalPg repositorySalPg;

    @Autowired
    private RepositoryInfoClienteHis repositoryInfoCliente;

    @Autowired
    private RepositoryTipId repositoryTipId;

    @Override
    public void pagosAutomaticos(HttpServletRequest http, Integer tipTransaccion, Long nroPordDestino, List<HisTransaccionesEntity> hisTransaccionesEntityList) throws Exception {

        String host = sharedService.generarUrl(http.getRequestURL().toString(), "OPLCANCELACIONES");
        log.info("ENTRA AL CONSUMO DEL SERVICIO consumoServicioCanAutDig.");
        List<SalPgDownEntity> pgDownEntityList = repositorySalPg.findAllByEstadoAndCodProdIsBetween(4, 3001, 3002);

        for (SalPgDownEntity item : pgDownEntityList) {

            JSONCancelacionAut jsonCancelacionAut = jsonCancelacion(item, tipTransaccion, nroPordDestino, hisTransaccionesEntityList);

            ResponseEntity<String> response = getResponsePagCdt(jsonCancelacionAut, host);

            log.warn("STATUS SERVICE PAG: " + response.getStatusCode());
            if (response.getStatusCode().value() == HttpStatus.OK.value()) {
//            if (true) {
                log.info("SE REALIZO EL PAGO DE FORMA EXITOSA ...");
                repositorySalPg.updateStateValue(jsonCancelacionAut.getNumCdt(), 1);
                System.out.println("Se actualiza el estado a '1' en el registro "+item.getNumCdt()+" con tipo de pago "
                        +tipTransaccion+" en la tabla SalPG");
            } else {
                log.error("ERROR AL CONSUMIR EL SERVICIO DE PAGOS ...");
                repositorySalPg.updateStateValue(jsonCancelacionAut.getNumCdt(), 2);
                System.out.println("Se actualiza el estado a '2' en el registro "+item.getNumCdt()+" con tipo de pago "
                        +tipTransaccion+" en la tabla SalPG");
            }
        }

    }


    public JSONCancelacionAut jsonCancelacion(SalPgDownEntity item, Integer tipTransaccion, Long nroPordDestino,
                                              List<HisTransaccionesEntity> hisTranpgEntityList) {

        JSONCancelacionAut jsonCancelacionAut = new JSONCancelacionAut();

        jsonCancelacionAut.setTipoId(getTipidParDownEntity(item.getNumTit()));
        jsonCancelacionAut.setNumeroId(item.getNumTit());
        jsonCancelacionAut.setNombreBeneficiario(item.getNomTit());
        jsonCancelacionAut.setNumCdt(item.getNumCdt());
        jsonCancelacionAut.setCodIsin(item.getCodIsin());
        jsonCancelacionAut.setOficinaOrigen(item.getNroOficina().intValue());
        jsonCancelacionAut.setCtaInv(Long.parseLong(item.getCtaInv()));
        jsonCancelacionAut.setTipoCtaInv(tipTransaccion);
        jsonCancelacionAut.setNumCta(nroPordDestino);
        jsonCancelacionAut.setTipoCta(tipTransaccion);
        jsonCancelacionAut.setOficinaRadic(item.getNroOficina().intValue());
        jsonCancelacionAut.setTransaccionPagoList(hisTranpgEntityList
                .stream()
                .filter(tranpg -> tranpg.getOplCdtxctainvTblNumCdt().equals(item.getNumCdt()))
                .filter(proceso -> !proceso.getOplTipprocesoTblTipProceso().equals("5"))
                .map(x -> new TransaccionPago(x.getOplTipprocesoTblTipProceso(), x.getValor()))
                .collect(Collectors.toList()));

        return jsonCancelacionAut;
    }


    public ResponseEntity<String> getResponsePagCdt(JSONCancelacionAut jsonCancelacionAut, String host) throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setCacheControl(CacheControl.noCache());

        HttpEntity<JSONCancelacionAut> requestEntity = new HttpEntity<>(jsonCancelacionAut, httpHeaders);
        final String url = host + "OPLPAGOSAUT/CDTSDesmaterializado/v1/cancelacion/pagosaut";
//                "http://localhost:8080/CDTSDesmaterializado/v1/cancelacion/pagosaut";
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.exchange(url,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<String>() {
                    });
        } catch (Exception e) {
            log.error("No se puedo completar el proceso, falló el servicio de pagos automáticos, revisar");
            throw new Exception("No se puedo completar el proceso, falló el servicio de pagos automáticos, revisar");
        }
    }

    /**
     * @param numTit
     * @return
     */
    private String getTipidParDownEntity(String numTit) {
        Optional<HisInfoClienteEntity> clienteById = repositoryInfoCliente.findById(numTit);
        if (clienteById.isPresent()) {
            Integer codId = Integer.parseInt(clienteById.get().getOplTipidTblCodId());
            Optional<TipidParDownEntity> byId = repositoryTipId.findById(codId);
            if (byId.isPresent()) return byId.get().getHomoCrm();
        }
        return null;
    }
}
