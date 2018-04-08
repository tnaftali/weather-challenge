package com.example.weatherchallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class DuplicateEntityException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;
    private String secondFieldName;
    private Object secondFieldValue;

    public DuplicateEntityException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("There is already a %s with %s: '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public DuplicateEntityException(String resourceName, String fieldName, Object fieldValue, String secondFieldName, Object secondFieldValue) {
        super(String.format("There is already a %s with %s: '%s' and %s: '%s'", resourceName, fieldName, fieldValue, secondFieldName, secondFieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.secondFieldName = secondFieldName;
        this.secondFieldValue = secondFieldValue;
    }
}