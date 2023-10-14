package com.neyesem.neyesembackend.exception;

import jakarta.validation.constraints.NotBlank;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String message) {
        super(message);
    }
}
