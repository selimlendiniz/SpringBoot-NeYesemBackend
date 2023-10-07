package com.neyesem.neyesembackend.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entityName, Long entityId) {
        super(String.format("%s with ID %d not found", entityName, entityId));
    }

    public EntityNotFoundException(String entityName,String variable, String value) {
        super(String.format("%s with %s %d not found", entityName, variable, value));
    }

}