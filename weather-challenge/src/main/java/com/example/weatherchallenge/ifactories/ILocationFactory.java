package com.example.weatherchallenge.ifactories;

import com.example.weatherchallenge.model.Location;

public interface ILocationFactory extends IFactory<Location, String> {
    Location create(String name);
}
