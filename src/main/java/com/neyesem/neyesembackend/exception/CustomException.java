package com.neyesem.neyesembackend.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{

    HttpStatus httpStatus;
    public CustomException(){
        super();
    }

    public CustomException(String message,HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

}
