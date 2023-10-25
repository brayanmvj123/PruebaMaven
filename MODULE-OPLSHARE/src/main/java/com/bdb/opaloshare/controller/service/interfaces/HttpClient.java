package com.bdb.opaloshare.controller.service.interfaces;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

public interface HttpClient {

    <K> ResponseEntity<K> get(String url, String messageLogs, Class<K> classResponse);

    <T, K> ResponseEntity<K> post(T request, String url, String messageLogs, Class<K> classResponse);

    <T, K> ResponseEntity<K> post(T request, String url, String messageLogs, ParameterizedTypeReference<K> classResponse);
}
