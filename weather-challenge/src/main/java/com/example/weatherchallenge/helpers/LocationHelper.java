package com.example.weatherchallenge.helpers;

import com.example.weatherchallenge.exceptions.ResourceNotFoundException;
import com.example.weatherchallenge.factories.LocationFactory;
import com.example.weatherchallenge.model.Location;
import com.example.weatherchallenge.searchers.LocationSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationHelper {
    @Autowired
    private LocationSearcher locationSearcher;
    @Autowired
    private LocationFactory locationFactory;

    public Location getOrCreate(String locationName) {
        Location location;
        try {
            location = locationSearcher.findByName(locationName);
        } catch (ResourceNotFoundException e) {
            return locationFactory.create(locationName);
        }

        return location;
    }

}
