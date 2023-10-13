package com.neyesem.neyesembackend.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;

public class ApiException {

    private Date timestamp;
    private String error;
    private String message;
    private String path;
    HashMap<String,Object> payload;

    public ApiException(Date timestamp, String error, String message, String path, HashMap<String, Object> payload) {
        this.timestamp = timestamp;
        this.error = error;
        this.message = message;
        this.path = path;
        this.payload = payload;
    }

    public ApiException(Date timestamp, String error, String message) {
        this.timestamp = timestamp;
        this.error = error;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HashMap<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(HashMap<String, Object> payload) {
        this.payload = payload;
    }
}
