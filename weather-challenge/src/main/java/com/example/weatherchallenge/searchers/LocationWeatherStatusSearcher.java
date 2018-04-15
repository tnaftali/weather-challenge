package com.example.weatherchallenge.searchers;

import com.example.weatherchallenge.exceptions.ResourceNotFoundException;
import com.example.weatherchallenge.model.Location;
import com.example.weatherchallenge.model.LocationWeatherStatus;
import com.example.weatherchallenge.repositories.LocationWeatherStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationWeatherStatusSearcher {
    @Autowired
    private LocationWeatherStatusRepository locationWeatherStatusRepository;

    private static String entity = "LocationWeatherStatus";
    private static String location = "location";

    public LocationWeatherStatus findByLocation(Location location) {
        LocationWeatherStatus locationWeatherStatus = locationWeatherStatusRepository.findByLocation(location);
        if (locationWeatherStatus == null) {
            throw new ResourceNotFoundException(this.entity, this.location, location.getName());
        }

        return locationWeatherStatus;
    }
}
