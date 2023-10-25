package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.AbonoService;
import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.CapitalService;
import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.InformacionClienteService;
import com.bdb.opalogdoracle.mapper.MapperDebito;
import com.bdb.opalogdoracle.persistence.model.debito.request.DebitoRequest;
import com.bdb.opalogdoracle.persistence.model.debito.response.ResponseDebito;
import com.bdb.opalogdoracle.persistence.model.exception.ControlCdtsException;
import com.bdb.opalogdoracle.persistence.model.servicecancel.InfoCtaClienteModel;
import com.bdb.opalogdoracle.persistence.model.servicecancel.PaymentTransaction;
import com.bdb.opalogdoracle.persistence.model.servicecancel.ResponsePagCdt;
import com.bdb.opaloshare.persistence.entity.*;
import com.bdb.opaloshare.persistence.repository.RepositoryDebitoAut;
import com.bdb.opaloshare.persistence.repository.RepositoryHisCDTSCancelation;
import com.bdb.opaloshare.persistence.repository.RepositoryHisCtrCdts;
import com.bdb.opaloshare.persistence.repository.RepositoryParEndpointDown;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@CommonsLog
public class CapitalImpl implements CapitalService {

    private final RepositoryHisCtrCdts repositoryHisCtrCdts;
    private final MapperDebito mapperDebito;
    private final RepositoryParEndpointDown repositoryParEndpointDown;
    private final AbonoService abonoService;
    private final InformacionClienteService informacionClienteService;
    private final RepositoryDebitoAut repositoryDebitoAut;
    private final RepositoryHisCDTSCancelation repositoryHisCDTSCancelation;

    public CapitalImpl(RepositoryHisCtrCdts repositoryHisCtrCdts,
                       MapperDebito mapperDebito,
                       RepositoryParEndpointDown repositoryParEndpointDown,
                       AbonoService abonoService,
                       InformacionClienteService informacionClienteService,
                       RepositoryDebitoAut repositoryDebitoAut,
                       RepositoryHisCDTSCancelation repositoryHisCDTSCancelation) {
        this.repositoryHisCtrCdts = repositoryHisCtrCdts;
        this.mapperDebito = mapperDebito;
        this.repositoryParEndpointDown = repositoryParEndpointDown;
        this.abonoService = abonoService;
        this.informacionClienteService = informacionClienteService;
        this.repositoryDebitoAut = repositoryDebitoAut;
        this.repositoryHisCDTSCancelation = repositoryHisCDTSCancelation;
    }

    @Override
    public BigDecimal capital(SalRenautdigEntity cdt, String endpoint) throws ControlCdtsException {
        log.info("Se inicia el consumo el servicio CAPITAL para ajustar el capital " + cdt.getNumCdt());
        HisCtrCdtsEntity hisCtrCdtsEntity = repositoryHisCtrCdts.findById(new HisCtrCdtsPk(cdt.getNumCdt(), cdt.getNumTit(), 1L))
                .orElseThrow(() -> new ControlCdtsException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Inconveniente"));

        Optional<HisCondicionCdtsEntity> hisCondicionCdtsEntity = hisCtrCdtsEntity.getHisCondicionCdtsEntity()
                .stream()
                .findFirst();

        Optional<HisCtaCliEntity> hisCtaCliEntity = hisCtrCdtsEntity.getHisCtaCliEntities()
                .stream()
                .findFirst();

        if (hisCondicionCdtsEntity.isPresent()) {
            log.info("Se valida y el CDT " + cdt.getNumCdt() + " por el momento no presenta ni aumentos ni abonos de capital.");
            if (hisCondicionCdtsEntity.get().getCapital().compareTo(new BigDecimal("0")) == 0)
                return new BigDecimal("0");

            if (hisCondicionCdtsEntity.get().getCapital().compareTo(new BigDecimal("0")) > 0
                    && hisCtaCliEntity.isPresent()
                    && !validarExistenciaDebito(cdt.getNumCdt(), cdt.getCodIsin(), cdt.getNumTit())) {
                boolean resultadoDebito = debito(debitoRequest(hisCondicionCdtsEntity.get().getCapital(),
                        hisCtrCdtsEntity, hisCtaCliEntity.get()), cdt.getCodIsin(), 0);
                if (resultadoDebito) return hisCondicionCdtsEntity.get().getCapital();
                else return new BigDecimal("0");
            }

            if (hisCondicionCdtsEntity.get().getCapital().compareTo(new BigDecimal("0")) < 0
                    && !validarExistenciaAbono(cdt.getNumCdt(), cdt.getCodIsin(), cdt.getNumTit())) {
                log.info("NO se encontraron abonos realizados, se procedera a realizar el abono por disminución de capital ...");
                return abonoCapital(endpoint, cdt, hisCondicionCdtsEntity.get());
            } else {
                log.warn("Encontro abonos realizados, se validara si corresponde al capital ...");
                return validarAbonosRealizados(cdt, hisCondicionCdtsEntity.get());
            }
        }
        return new BigDecimal("0");
    }

    public BigDecimal abonoCapital(String endpoint, SalRenautdigEntity cdt, HisCondicionCdtsEntity hisCondicionCdtsEntity) {
        InfoCtaClienteModel ctaClient = informacionClienteService.obtenerInformacion(cdt);

        List<PaymentTransaction> payment = new ArrayList<>();
        payment.add(new PaymentTransaction(3, new BigDecimal(Math.abs(hisCondicionCdtsEntity.getCapital().longValue()))));
        ctaClient.setTransaccionPagoList(payment);

        ResponseEntity<ResponsePagCdt> response = abonoService.abono(endpoint, ctaClient);
        log.info("paso response...." + response.getStatusCode());
        //Se ajustara cuando se ingrese a la condición cuando se tenga el ajuste el contable, por el momento se deja asi
        if (response.getStatusCode().isError()) return hisCondicionCdtsEntity.getCapital();
        if (response.getStatusCode().is2xxSuccessful()) return hisCondicionCdtsEntity.getCapital();
        return hisCondicionCdtsEntity.getCapital();
    }

    public DebitoRequest debitoRequest(BigDecimal capital, HisCtrCdtsEntity hisCtrCdtsEntity, HisCtaCliEntity hisCtaCliEntity) {
        return mapperDebito.debitoRequest(capital, hisCtrCdtsEntity, hisCtaCliEntity);
    }

    public boolean debito(DebitoRequest debitoRequest, String codIsin, int intentos) {
        log.info("Se inicia la operación capital...");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DebitoRequest> requestEntity = new HttpEntity<>(debitoRequest, httpHeaders);

        final String url = repositoryParEndpointDown.findById(12L)
                .orElse(new ParEndpointDownEntity(1L, "http://localhost:8082/CDTSDesmaterializado/v1/debito")).getRuta();
        //"http://localhost:8080/CDTSDesmaterializado/v1/aperturaCDTsDes";
        log.info("URL CONSUMIDA: " + url);

        ResponseEntity<ResponseDebito> response = getResponseDebitoResponseEntity(requestEntity, url);

        log.info("STATUS DEL SERVICIO DE APERTURA: " + response.getStatusCode());

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("El debito fue exitoso.");
            almacenarResultadoDebito(debitoRequest.getNumCdt(), codIsin, debitoRequest.getNumeroId(), response.getBody().getResult());
            return true;
        } else {
            log.error("El debito fue rechazado.");
            almacenarResultadoDebito(debitoRequest.getNumCdt(), codIsin, debitoRequest.getNumeroId(), response.getBody().getResult());
            for (int i = intentos; i < 3; i++) {
                debito(debitoRequest, codIsin, 0);
            }
            return false;
        }
    }

    public ResponseEntity<ResponseDebito> getResponseDebitoResponseEntity(HttpEntity<DebitoRequest> requestEntity, String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<ResponseDebito>() {
                });
    }

    public boolean validarExistenciaDebito(Long numCdt, String codIsin, String numTit) {
        return repositoryDebitoAut.findById(new HisDebitoAutPk(codIsin, numCdt, numTit)).isPresent();
    }

    public boolean validarExistenciaAbono(Long numCdt, String codIsin, String numTit) {
        return repositoryHisCDTSCancelation.findById(new HisCDTSCancelationPk(codIsin, numCdt, numTit)).isPresent();
    }

    public void almacenarResultadoDebito(Long numCdt, String codIsin, String numTit, String resultadoDebito) {
        repositoryDebitoAut.save(new HisDebitoAutEntity(codIsin, numCdt, numTit, resultadoDebito, LocalDateTime.now()));
    }

    public BigDecimal validarAbonosRealizados(SalRenautdigEntity cdt, HisCondicionCdtsEntity hisCondicionCdtsEntity) {
        HisCDTSCancelationPk hisCDTSCancelationPk = new HisCDTSCancelationPk(cdt.getCodIsin(), cdt.getNumCdt(), cdt.getNumTit());
        List<HisCDTSCancelationEntity> hisCDTSCancelationAllById = repositoryHisCDTSCancelation
                .findAllById(Collections.singletonList(hisCDTSCancelationPk));
        long abonoCapital;
        if (!hisCDTSCancelationAllById.isEmpty()) {
            abonoCapital = hisCDTSCancelationAllById
                    .stream()
                    .filter(cancelAut -> cancelAut.getPago().contains("\"tipPayment\":\"K\""))
                    .count();
            if (abonoCapital > 0) {
                log.info("El abono encontrado CORRESPONDE a una disminución de capital, retornará la disminución del capital.");
                return hisCondicionCdtsEntity.getCapital();
            }
        }
        log.info("El abono encontrado no corresponde a una disminución de capital.");
        return new BigDecimal(0);
    }

}
