package com.example.weatherchallenge.services;

import com.example.weatherchallenge.factories.LocationFactory;
import com.example.weatherchallenge.factories.LocationWeatherStatusFactory;
import com.example.weatherchallenge.helpers.LocationHelper;
import com.example.weatherchallenge.helpers.LocationWeatherStatusHelper;
import com.example.weatherchallenge.integration.WeatherIntegrationService;
import com.example.weatherchallenge.integration.model.dto.Weather.WeatherServiceResponseDto;
import com.example.weatherchallenge.iservices.ILocationService;
import com.example.weatherchallenge.mappers.EntityMapper;
import com.example.weatherchallenge.model.Board;
import com.example.weatherchallenge.model.Location;
import com.example.weatherchallenge.model.LocationWeatherStatus;
import com.example.weatherchallenge.model.User;
import com.example.weatherchallenge.model.dto.LocationCriteriaDto;
import com.example.weatherchallenge.model.dto.LocationWeatherDto;
import com.example.weatherchallenge.repositories.BoardRepository;
import com.example.weatherchallenge.repositories.LocationRepository;
import com.example.weatherchallenge.repositories.LocationWeatherStatusRepository;
import com.example.weatherchallenge.searchers.BoardSearcher;
import com.example.weatherchallenge.searchers.UserSearcher;
import com.example.weatherchallenge.validators.LocationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService implements ILocationService {
    @Autowired
    private LocationValidator locationValidator;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private UserSearcher userSearcher;
    @Autowired
    private BoardSearcher boardSearcher;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private LocationHelper locationHelper;
    @Autowired
    private WeatherIntegrationService weatherIntegrationService;
    @Autowired
    private LocationWeatherStatusRepository locationWeatherStatusRepository;
    @Autowired
    private LocationWeatherStatusHelper locationWeatherStatusHelper;

    public void addLocationToBoard(LocationCriteriaDto locationCriteriaDto) {
        User user = userSearcher.findById(locationCriteriaDto.username);

        locationValidator.validate(locationCriteriaDto.location, locationCriteriaDto.boardName, user);

        Board board = boardSearcher.findByUserAndName(user, locationCriteriaDto.boardName);
        Location location = locationHelper.getOrCreate(locationCriteriaDto.location);
        WeatherServiceResponseDto weatherServiceResponse = weatherIntegrationService.getLocationsCurrentWeather("\"" + location.getName() + "\"");
        LocationWeatherDto locationWeatherDto = entityMapper.mapToLocationWeatherDto(weatherServiceResponse, location);

        locationRepository.save(location);

        LocationWeatherStatus locationWeatherStatus = locationWeatherStatusHelper.getUpdatedOrCreate(location, locationWeatherDto);
        board.addLocation(location);
        boardRepository.save(board);
        locationWeatherStatusRepository.save(locationWeatherStatus);
    }

    public void deleteLocationFromBoard(LocationCriteriaDto locationCriteriaDto) {
        locationValidator.validateLocationExistence(locationCriteriaDto.location);

        User user = userSearcher.findById(locationCriteriaDto.username);
        Board board = boardSearcher.findByUserAndName(user, locationCriteriaDto.boardName);
        board.removeLocation(locationCriteriaDto.location);

        boardRepository.save(board);
    }

    public String getAllLocationsWeather() {
        List<Location> locations = locationRepository.findAll();
        Location[] locationsArray = locations.toArray(new Location[locations.size()]);
        String stringLocations = entityMapper.mapLocationsToString(locations);

        if (!StringUtils.isEmpty(stringLocations)) {
            WeatherServiceResponseDto response = weatherIntegrationService.getLocationsCurrentWeather(stringLocations);
            List<LocationWeatherDto> locationsWeather = entityMapper.mapToLocationWeatherDtoList(response, locationsArray);

            int locationIndex = 0;
            for (Iterator<LocationWeatherDto> i = locationsWeather.iterator(); i.hasNext(); ) {
                LocationWeatherDto item = i.next();
                String locationName = locationsArray[locationIndex].getName();
                Location location = findLocationByName(locations, locationName);

                if (location != null) {
                    LocationWeatherStatus locationWeatherStatus = locationWeatherStatusHelper.getUpdatedOrCreate(location, item);
                    locationWeatherStatusRepository.save(locationWeatherStatus);
                    locationIndex++;
                }
            }
        }

        return entityMapper.mapLocationsToString(locations);
    }

    private Location findLocationByName(List<Location> locations, String locationName) {
        Optional<Location> locationResult = locations.stream().filter(x -> x.getName().equals(locationName)).findFirst();
        if (locationResult.isPresent()) {
            return locationResult.get();
        }

        return null;
    }
}
