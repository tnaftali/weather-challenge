package com.example.weatherchallenge.helpers;

import com.example.weatherchallenge.exceptions.ResourceNotFoundException;
import com.example.weatherchallenge.factories.LocationWeatherStatusFactory;
import com.example.weatherchallenge.integration.WeatherIntegrationService;
import com.example.weatherchallenge.integration.model.dto.Weather.WeatherServiceResponseDto;
import com.example.weatherchallenge.mappers.EntityMapper;
import com.example.weatherchallenge.model.Location;
import com.example.weatherchallenge.model.LocationWeatherStatus;
import com.example.weatherchallenge.model.dto.LocationWeatherDto;
import com.example.weatherchallenge.repositories.LocationWeatherStatusRepository;
import com.example.weatherchallenge.searchers.LocationWeatherStatusSearcher;
import com.example.weatherchallenge.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class LocationWeatherStatusHelper {
    @Autowired
    private LocationWeatherStatusRepository locationWeatherStatusRepository;
    @Autowired
    private LocationWeatherStatusFactory locationWeatherStatusFactory;
    @Autowired
    private LocationWeatherStatusUpdater locationWeatherStatusUpdater;
    @Autowired
    private LocationWeatherStatusSearcher locationWeatherStatusSearcher;
    @Autowired
    private WeatherIntegrationService weatherIntegrationService;
    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private DateUtils dateUtils;

    public LocationWeatherStatus getUpdatedOrCreate(Location location, LocationWeatherDto locationWeatherDto) {
        LocationWeatherStatus locationWeatherStatus;
        try {
            locationWeatherStatus = locationWeatherStatusSearcher.findByLocation(location);
            locationWeatherStatus = locationWeatherStatusUpdater.update(locationWeatherStatus, locationWeatherDto);
        } catch (ResourceNotFoundException e) {
            return locationWeatherStatusFactory.create(locationWeatherDto, location);
        }

        return locationWeatherStatus;
    }

    public ArrayList<LocationWeatherDto> findByLocations(List<Location> locations) {
        ArrayList<LocationWeatherDto> list = new ArrayList<>();
        for (Iterator<Location> i = locations.iterator(); i.hasNext(); ) {
            Location location = i.next();
            LocationWeatherDto locationWeatherDto = getWeatherStatusForLocation(location);

            list.add(locationWeatherDto);
        }

        return list;
    }

    private LocationWeatherDto getWeatherStatusForLocation(Location location) {
        LocationWeatherStatus locationWeatherStatus = locationWeatherStatusSearcher.findByLocation(location);

        if (!dateUtils.lessThanHalfHourDifference(locationWeatherStatus.getUpdatedAt(), new Date())) {
            WeatherServiceResponseDto response = weatherIntegrationService.getLocationsCurrentWeather("\"" + location.getName() + "\"");
            LocationWeatherDto locationWeatherDto = entityMapper.mapToLocationWeatherDto(response, location);

            locationWeatherStatus = locationWeatherStatusUpdater.update(locationWeatherStatus, locationWeatherDto);
            locationWeatherStatusRepository.save(locationWeatherStatus);

            return locationWeatherDto;
        }

        return entityMapper.mapToLocationWeatherDto(locationWeatherStatus, location);
    }

}
