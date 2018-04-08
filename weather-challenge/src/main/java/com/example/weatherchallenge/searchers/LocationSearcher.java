package com.example.weatherchallenge.searchers;

import com.example.weatherchallenge.exceptions.ResourceNotFoundException;
import com.example.weatherchallenge.model.Location;
import com.example.weatherchallenge.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationSearcher {
    @Autowired
    private LocationRepository locationRepository;

    private static String entity = "Board";
    private static String name = "name";

    public Location findByName(String name) {
        Location result = locationRepository.findLocationByName(name);
        if (result == null) {
            throw new ResourceNotFoundException(this.entity, this.name, name);
        }

        return result;
    }
}
