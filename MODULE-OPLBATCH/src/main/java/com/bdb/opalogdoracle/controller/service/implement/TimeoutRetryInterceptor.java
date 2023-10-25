package com.bdb.opalogdoracle.controller.service.implement;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;

public class TimeoutRetryInterceptor implements ClientHttpRequestInterceptor {

    private final int maxAttempts;
    private final long timeoutMillis;

    public TimeoutRetryInterceptor(int maxAttempts, long timeoutMillis) {
        this.maxAttempts = maxAttempts;
        this.timeoutMillis = timeoutMillis;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        int attempt = 0;
        do {
            try {
                return execution.execute(request, body);
            } catch (HttpServerErrorException e) {
                // Handle HTTP server error (5xx) here
                throw e; // Re-throw the exception if needed
            } catch (IOException e) {
                // Handle IO errors here
            } catch (Exception e) {
                // Handle other exceptions here
            }

            attempt++;
            if (attempt < maxAttempts) {
                try {
                    Thread.sleep(timeoutMillis);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    throw new IllegalStateException("Thread interrupted", ex);
                }
            }
        } while (attempt < maxAttempts);

        throw new IOException("Max retry attempts reached");
    }
}
