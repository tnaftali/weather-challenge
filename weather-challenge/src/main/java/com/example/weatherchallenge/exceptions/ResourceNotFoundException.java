package com.example.weatherchallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;
    private String fieldName2;
    private Object fieldValue2;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2) {
        super(String.format("%s not found with %s: '%s' and %s: '%s'", resourceName, fieldName1, fieldValue1, fieldName2, fieldValue2));
        this.resourceName = resourceName;
        this.fieldName = fieldName1;
        this.fieldValue = fieldValue1;
        this.fieldName2 = fieldName2;
        this.fieldValue2 = fieldValue2;
    }
}