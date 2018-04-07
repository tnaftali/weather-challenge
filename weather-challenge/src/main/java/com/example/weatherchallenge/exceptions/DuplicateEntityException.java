package com.example.weatherchallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DuplicateEntityException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public DuplicateEntityException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("There is already a %s with the %s: '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}