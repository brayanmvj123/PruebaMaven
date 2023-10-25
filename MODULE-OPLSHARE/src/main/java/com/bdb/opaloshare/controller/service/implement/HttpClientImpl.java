package com.bdb.opaloshare.controller.service.implement;

import com.bdb.opaloshare.controller.service.interfaces.HttpClient;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@CommonsLog
public class HttpClientImpl implements HttpClient {


    @Override
    public <K> ResponseEntity<K> get(String url, String messageLogs, Class<K> classResponse) {

        log.info("ENTRA AL CONSUMO DEL SERVICIO DE " + messageLogs);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setCacheControl(CacheControl.noCache());
        HttpEntity<Object> requestEntity = new HttpEntity<>(httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<K> response = restTemplate.exchange(url,
                    HttpMethod.GET,
                    requestEntity,
                    classResponse);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response;
            } else {
                log.error("ERROR AL CONSUMIR EL SERVICIO DE " + messageLogs);
                throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "ERROR AL CONSUMIR EL SERVICIO DE " + messageLogs);
            }
        } catch (HttpClientErrorException e) {
            log.error("ERROR AL CONSUMIR EL SERVICIO DE ");
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ": NO ESTA DISPONIBLE EL SERVICIO '" + messageLogs + "' , REVISAR");
        }
    }

    @Override
    public <T, K> ResponseEntity<K> post(T request, String url, String messageLogs, Class<K> classResponse) {

        log.info("ENTRA AL CONSUMO DEL SERVICIO DE " + messageLogs);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setCacheControl(CacheControl.noCache());
        HttpEntity<T> requestEntity = new HttpEntity<>(request, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<K> response = restTemplate.exchange(url,
                    HttpMethod.POST,
                    requestEntity,
                    classResponse);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response;
            } else {
                log.error("ERROR AL CONSUMIR EL SERVICIO DE " + messageLogs);
                throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "ERROR AL CONSUMIR EL SERVICIO DE " + messageLogs);
            }
        } catch (HttpClientErrorException e) {
            log.error("ERROR AL CONSUMIR EL SERVICIO DE ");
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ": NO ESTA DISPONIBLE EL SERVICIO '" + messageLogs + "' , REVISAR");
        }
    }


    @Override
    public <T, K> ResponseEntity<K> post(T request, String url, String messageLogs, ParameterizedTypeReference<K> classResponse) {

        log.info("ENTRA AL CONSUMO DEL SERVICIO DE " + messageLogs);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setCacheControl(CacheControl.noCache());
        HttpEntity<T> requestEntity = new HttpEntity<>(request, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<K> response = restTemplate.exchange(url,
                    HttpMethod.POST,
                    requestEntity,
                    classResponse);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response;
            } else {
                log.error("ERROR AL CONSUMIR EL SERVICIO DE " + messageLogs);
                throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "ERROR AL CONSUMIR EL SERVICIO DE " + messageLogs);
            }
        } catch (Exception e) {
            log.error("ERROR AL CONSUMIR EL SERVICIO DE ");
            throw new HttpClientErrorException(HttpStatus.BAD_GATEWAY, ": no está disponible o se presenta un error en el servicio '" + messageLogs + "' , revisar. Descripcion detallada: " +e.getMessage());
        }
    }
}
