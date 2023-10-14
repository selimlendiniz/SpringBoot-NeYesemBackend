package com.neyesem.neyesembackend.exception;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.RefreshFailedException;
import java.util.Date;
import java.util.HashMap;

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

    @ExceptionHandler(value = InvalidPasswordException.class)
    public ResponseEntity<ApiException> handleInvalidPasswordException(InvalidPasswordException e) {

        ApiException apiException = new ApiException(
                new Date(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage());

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);

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

        HashMap<String, Object> payload = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(
                error -> payload.put(error.getField(), error.getDefaultMessage())
        );

        ApiException apiException = new ApiException(
                new Date(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getBindingResult().getAllErrors().toString(),
                payload

        );


        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiException> handleExpiredJwtException(ExpiredJwtException e) {

        ApiException apiException = new ApiException(
                new Date(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                e.getHeader().toString()

        );

        return new ResponseEntity<>(apiException, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ApiException> handleIllegalArgumentException(IllegalArgumentException e) {
        ApiException apiException = new ApiException(
                new Date(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage()
        );
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ApiException> handleBadCredentialsException(BadCredentialsException e) {
        ApiException apiException = new ApiException(
                new Date(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage()
        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RefreshTokenMissing.class)
    public ResponseEntity<ApiException> handleRefreshTokenMissing(RefreshTokenMissing e) {
        ApiException apiException = new ApiException(
                new Date(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage()
        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RefreshFailedException.class)
    public ResponseEntity<ApiException> handleRefreshFailedException(RefreshFailedException e) {
        ApiException apiException = new ApiException(
                new Date(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage()
        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ApiException> handleTokenException(TokenException e) {
        ApiException apiException = new ApiException(
                new Date(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage()
        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

}
