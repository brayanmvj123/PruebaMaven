package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.implement.TimeoutRetryInterceptor;
import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.ConsumoGenericoService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@CommonsLog
public class ConsumoGenericoImpl implements ConsumoGenericoService {

    private RestTemplate restTemplate;

    public ConsumoGenericoImpl(){
        // Configura el RestTemplate con el interceptor de tiempo de espera personalizado
        restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        // Agregar un interceptor con un timeout de 5 segundos
        interceptors.add(new TimeoutRetryInterceptor(3, 5000));

// Agregar otro interceptor con un timeout de 10 segundos
        interceptors.add(new TimeoutRetryInterceptor(3, 10000));
        this.restTemplate.setInterceptors(interceptors);
    }

    @Override
    public <T> ResponseEntity<String> getResponseService(HttpEntity<T> requestEntity, String url) {
        try {
            return restTemplate.exchange(url,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<String>() {
                    });
        } catch (Exception e){
            log.error("Se geenero error en el servicio: " + url + ", por: " + e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se geenero error en el servicio: " +
                    url + ", por: " + e);
        }
    }

}
