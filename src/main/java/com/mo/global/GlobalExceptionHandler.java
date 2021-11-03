package com.mo.global;

import com.mo.domain.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    private ResponseEntity<Response> httpClientErrorExceptionHandler(HttpClientErrorException e) {
        Response data = new Response(e.getStatusCode().value(), e.getMessage());
        return new ResponseEntity<>(data, e.getStatusCode());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    private ResponseEntity<Response> httpServerErrorExceptionHandler(HttpServerErrorException e) {
        Response data = new Response(e.getStatusCode().value(), e.getMessage());
        return new ResponseEntity<>(data, e.getStatusCode());
    }

}
