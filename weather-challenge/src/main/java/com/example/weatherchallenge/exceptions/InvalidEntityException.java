package com.example.weatherchallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidEntityException extends RuntimeException {
    private String resourceName;
    private Object resourceValue;

    public InvalidEntityException(String resourceName, Object resourceValue) {
        super(String.format("The %s '%s' is invalid", resourceName, resourceValue));
        this.resourceName = resourceName;
        this.resourceValue = resourceValue;
    }
}