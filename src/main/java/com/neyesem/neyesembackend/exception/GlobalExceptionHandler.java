package com.neyesem.neyesembackend.exception;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Users Exceptions

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ApiException> handleUserNotFoundException(UserNotFoundException e) {

        ApiException apiException = new ApiException(
                new Date(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage());

        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<ApiException> handleUserAlreadyExistsException(UserAlreadyExistsException e) {

        ApiException apiException = new ApiException(
                new Date(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                e.getMessage());

        return new ResponseEntity<>(apiException, HttpStatus.CONFLICT);

    }

    //Restaurant Exceptions
    @ExceptionHandler(value = RestaurantNotFoundException.class)
    public ResponseEntity<ApiException> handleRestaurantNotFoundException(RestaurantNotFoundException e) {
        ApiException apiException = new ApiException(
                new Date(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage()
        );
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiException> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        ApiException apiException = new ApiException(
                new Date(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage()
        );


        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiException> handleExpiredJwtException(ExpiredJwtException e) {

        ApiException apiException = new ApiException(
                new Date(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                e.getMessage()
        );

        return new ResponseEntity<>(apiException, HttpStatus.UNAUTHORIZED);
    }


}
