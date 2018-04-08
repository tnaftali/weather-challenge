package com.example.weatherchallenge.validators;

import com.example.weatherchallenge.exceptions.DuplicateEntityException;
import com.example.weatherchallenge.exceptions.InvalidEntityException;
import com.example.weatherchallenge.exceptions.ResourceNotFoundException;
import com.example.weatherchallenge.integration.WeatherIntegrationService;
import com.example.weatherchallenge.ivalidators.IValidator;
import com.example.weatherchallenge.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationValidator implements IValidator<String> {
    @Autowired
    private WeatherIntegrationService weatherIntegrationService;
    @Autowired
    private LocationRepository locationRepository;

    private static String location = "Location";
    private static String name = "Name";

    public void validate(String location) {
        validateLocationWithService(location);
        validateLocationInRepository(location);
    }

    private void validateLocationWithService(String location) {
        if (!weatherIntegrationService.existsLocationCurrentWeather(location)) {
            throw new InvalidEntityException(this.location, location);
        }
    }

    private void validateLocationInRepository(String location) {
        if (locationRepository.existsLocationByName(location)) {
            throw new DuplicateEntityException(this.location, this.name, location);
        }
    }

    public void validateLocationExistenceInRepository(String location) {
        if (!locationRepository.existsLocationByName(location)) {
            throw new ResourceNotFoundException(this.location, this.name, location);
        }
    }
}
