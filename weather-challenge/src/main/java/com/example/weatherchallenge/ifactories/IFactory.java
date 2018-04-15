package com.example.weatherchallenge.ifactories;

public interface IFactory<T, X> {
    T create(X param);
}