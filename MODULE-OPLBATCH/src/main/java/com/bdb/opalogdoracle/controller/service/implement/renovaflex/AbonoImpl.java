package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.AbonoService;
import com.bdb.opalogdoracle.persistence.model.servicecancel.InfoCtaClienteModel;
import com.bdb.opalogdoracle.persistence.model.servicecancel.PaymentTransaction;
import com.bdb.opalogdoracle.persistence.model.servicecancel.ResponsePagCdt;
import com.bdb.opaloshare.persistence.entity.HisCDTSCancelationEntity;
import com.bdb.opaloshare.persistence.entity.HisCDTSCancelationPk;
import com.bdb.opaloshare.persistence.repository.RepositoryHisCDTSCancelation;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.apachecommons.CommonsLog;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@CommonsLog
public class AbonoImpl implements AbonoService {

    private final RepositoryHisCDTSCancelation repositoryHisCDTSCancelation;
    private final String RESPONSE = "{\"response\":[resultado]}";
    private final String RESULT = "\"result\":\"100\",\"notification\":\"TP en_US 100 General Error\",\"tipPayment\":\"";

    public AbonoImpl(RepositoryHisCDTSCancelation repositoryHisCDTSCancelation) {
        this.repositoryHisCDTSCancelation = repositoryHisCDTSCancelation;
    }

    @Override
    public ResponseEntity<ResponsePagCdt> abono(String endpoint, InfoCtaClienteModel ctaClient) {
        RestTemplate rt = new RestTemplate();
        try {
            return rt.postForEntity(endpoint, ctaClient, ResponsePagCdt.class);
        } catch (HttpStatusCodeException e) {
            log.error("Se tuvo error general en el consumo del servicio del BUS.");
            almacenarPago(ctaClient);
            return ResponseEntity.status(500).body(new ResponsePagCdt(HttpStatus.INTERNAL_SERVER_ERROR,
                    LocalDateTime.now(), "Error general"));
        }

    }
    public String almacenarPago(InfoCtaClienteModel item) {
        log.info("Se inicia el almacenamiento pago fallido...");
        Optional<HisCDTSCancelationEntity> cdtsCancelationById = repositoryHisCDTSCancelation
                .findById(new HisCDTSCancelationPk(item.getCodIsin(), item.getNumCdt(), item.getNumeroId()));
        if (cdtsCancelationById.isPresent()) {
            String resultadoActualizado = anadirTipoPago(resultadoTipoPago(item.getTransaccionPagoList()), cdtsCancelationById.get().getPago());
            almacenarPagoFallido(item, resultadoActualizado);
            return resultadoActualizado;
        } else {
            String resultado = RESPONSE.replace("resultado", resultadoTipoPago(item.getTransaccionPagoList()));
            almacenarPagoFallido(item, resultado);
            return resultado;
        }
    }

    public void almacenarPagoFallido(InfoCtaClienteModel item, String resultadoPago) {
        log.info("Se inicia el almacenamiento del pago fallido...");
        HisCDTSCancelationEntity hisCDTSCancelationEntity = new HisCDTSCancelationEntity(item.getCodIsin(),
                item.getNumCdt(),
                item.getNumeroId(),
                resultadoPago,
                LocalDateTime.now());
        repositoryHisCDTSCancelation.save(hisCDTSCancelationEntity);
    }

    public String anadirTipoPago(String resultadoPago, String resultadoAlmacenado) {
        log.info("Se añade un nuevo tipo de pago ...");
        JsonObject resultadoNuevo = new Gson().fromJson(resultadoPago, JsonObject.class);

        JsonObject resultadoAntiguo = new Gson().fromJson(resultadoAlmacenado, JsonObject.class);
        resultadoAntiguo.getAsJsonArray("response").add(resultadoNuevo);
        return String.valueOf(resultadoAntiguo.toString());
    }

    public String resultadoTipoPago(List<PaymentTransaction> tipoPagos) {
        return tipoPagos.stream()
                .map(item -> RESULT.concat(homologarTipoPago(item.getTipoPago())) + "\"")
                .collect(Collectors.joining(",", "{", "}"));
    }

    public String homologarTipoPago(Integer tipoPago){
        return tipoPago == 3 ? "K" : "I";
    }

}
