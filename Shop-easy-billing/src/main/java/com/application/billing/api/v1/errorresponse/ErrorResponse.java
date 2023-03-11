package com.application.billing.api.v1.errorresponse;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ErrorResponse extends RuntimeException {
    private final HttpStatus httpCode;
    private final String error;
}
