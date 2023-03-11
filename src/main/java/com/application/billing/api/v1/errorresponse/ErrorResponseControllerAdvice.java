package com.application.billing.api.v1.errorresponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ErrorResponseControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ErrorResponse.class)
    public ResponseEntity<Map<String, String>> publishError(ErrorResponse e, WebRequest webRequest) {
        return new ResponseEntity<>(Map.of("error", e.getError()), e.getHttpCode());
    }
}
