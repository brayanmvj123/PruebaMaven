package com.bdb.opalo.control.controller.service.implement;

import com.bdb.opalo.control.controller.service.config.ConfigurationWebClient;
import com.bdb.opalo.control.controller.service.interfaces.SimuladorRendimientoService;
import com.bdb.opalo.control.persistence.JSONSchema.JSONSimulador;
import com.bdb.opalo.control.persistence.JSONSchema.ParametrosJSONSimulador;
import com.bdb.opalo.control.persistence.JSONSchema.ResultJSONCuotas;
import com.bdb.opaloshare.persistence.entity.ParEndpointDownEntity;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opaloshare.persistence.repository.RepositoryParEndpointDown;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLException;
import java.math.BigDecimal;
import java.time.Duration;

@Service
@Slf4j
public class WebClientImpl implements SimuladorRendimientoService {
    RepositoryParEndpointDown endpointDown;
    ParEndpointDownEntity uri;
    private final ConfigurationWebClient configurationWebClient;

    public WebClientImpl(RepositoryParEndpointDown endpointDown, ConfigurationWebClient configurationWebClient){
        this.endpointDown = endpointDown;
        this.configurationWebClient = configurationWebClient;
    }

    public RequestResult<ResultJSONCuotas> simulador(Integer base, BigDecimal capital, String periodicidad, int retencion,
                                                     BigDecimal tasaFija, String plazo){
        log.info("ENTRO AL SIMULADOR");
        JSONSimulador requestSimu = new JSONSimulador();
        requestSimu.setCanal("OPL766");
        ParametrosJSONSimulador requestParam = new ParametrosJSONSimulador();
        requestParam.setBase(String.valueOf(base == 360 ? 2 : 1));
        requestParam.setCapital(String.valueOf(capital));
        requestParam.setDiasPlazo(plazo);

        requestParam.setPeriodicidad(String.valueOf(1));
        requestParam.setRetencion(String.valueOf(retencion));
        requestParam.setTasaFija(String.valueOf(tasaFija));
        requestSimu.setParametros(requestParam);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONSimulador> requestEntity = new HttpEntity<>(
                requestSimu, headers);

        String resultJSONCuotas = null;
        ParEndpointDownEntity host = this.endpointDown.getParametro(13L);
        log.info("ESTA ES LA RUTA: " +  host.getRuta());
        try {
            resultJSONCuotas = configurationWebClient.creationWebClienteTrusted().post()
                    .uri(host.getRuta() + "/TasaEfectivaNominal/v1/simulacionCuota")
                    .body(Mono.just(requestSimu), JSONSimulador.class)
                    .retrieve()
                    .bodyToMono(String.class)
                    .timeout(Duration.ofSeconds(30))
                    .block();
        } catch (SSLException e) {
            throw new RuntimeException(e);
        }

        return new Gson().fromJson(resultJSONCuotas, new TypeToken<RequestResult<ResultJSONCuotas>>(){}.getType());
    }
}
