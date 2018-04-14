package com.example.weatherchallenge.validators;

import com.example.weatherchallenge.exceptions.DuplicateEntityException;
import com.example.weatherchallenge.exceptions.InvalidEntityException;
import com.example.weatherchallenge.exceptions.ResourceNotFoundException;
import com.example.weatherchallenge.integration.WeatherIntegrationService;
import com.example.weatherchallenge.model.Board;
import com.example.weatherchallenge.model.Location;
import com.example.weatherchallenge.model.User;
import com.example.weatherchallenge.repositories.BoardRepository;
import com.example.weatherchallenge.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationValidator {
    @Autowired
    private WeatherIntegrationService weatherIntegrationService;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private BoardRepository boardRepository;

    private static String location = "Location";
    private static String name = "Name";

    public void validate(String location, String boardName, User user) {
        validateLocationWithService(location);
        validateLocationInBoard(location, boardName, user);
    }

    private void validateLocationWithService(String location) {
        if (!weatherIntegrationService.existsLocationCurrentWeather(location)) {
            throw new InvalidEntityException(this.location, location);
        }
    }

    private void validateLocationInBoard(String location, String boardName, User user) {
        Board board = boardRepository.findBoardByUserAndName(user, boardName);
        List<Location> list = board.getLocations();

        if (list.stream().filter(o -> o.getName().equals(location)).findFirst().isPresent()) {
            throw new DuplicateEntityException(this.location, this.name, location);
        }
    }

    public void validateLocationExistenceInRepository(String location) {
        if (!locationRepository.existsLocationByName(location)) {
            throw new ResourceNotFoundException(this.location, this.name, location);
        }
    }
}
