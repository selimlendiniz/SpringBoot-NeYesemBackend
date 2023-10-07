package com.neyesem.neyesembackend.exception;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(String entityName, Long entityId) {
        super(String.format("%s already exists with: ID %d", entityName, entityId));
    }

    public EntityAlreadyExistsException(String entityName,String variable, String entityId) {
        super(String.format("%s already exists with: %s %s ", entityName, variable, entityId));
    }

}