package com.example.weatherchallenge.ivalidators;

public interface IValidator<T> {
    void validate(T objectToValidate);
}
