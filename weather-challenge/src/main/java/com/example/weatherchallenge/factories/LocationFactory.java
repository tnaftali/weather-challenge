package com.example.weatherchallenge.factories;

import com.example.weatherchallenge.ifactories.ILocationFactory;
import com.example.weatherchallenge.model.Location;
import org.springframework.stereotype.Service;

@Service
public class LocationFactory implements ILocationFactory {
    public Location create(String name) {
        return new Location(name);
    }
}
