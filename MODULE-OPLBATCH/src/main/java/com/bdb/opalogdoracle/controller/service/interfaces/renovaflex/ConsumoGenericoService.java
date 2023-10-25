package com.bdb.opalogdoracle.controller.service.interfaces.renovaflex;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

public interface ConsumoGenericoService {
    <T> ResponseEntity<String> getResponseService(HttpEntity<T> requestEntity, String url);
}
