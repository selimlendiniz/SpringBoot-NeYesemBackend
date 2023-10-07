package com.neyesem.neyesembackend.exception;

import ch.qos.logback.core.spi.ErrorCodes;
import org.springframework.http.HttpStatus;

public class GenericException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;


    public GenericException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
