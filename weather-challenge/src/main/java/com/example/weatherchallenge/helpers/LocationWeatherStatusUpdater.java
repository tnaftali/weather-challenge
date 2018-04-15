package com.example.weatherchallenge.helpers;

import com.example.weatherchallenge.model.LocationWeatherStatus;
import com.example.weatherchallenge.model.dto.LocationWeatherDto;
import com.example.weatherchallenge.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationWeatherStatusUpdater {
    @Autowired
    private DateUtils dateUtils;

    public LocationWeatherStatus update(LocationWeatherStatus locationWeatherStatus, LocationWeatherDto locationWeatherDto) {
        locationWeatherStatus.setTemperature(locationWeatherDto.temperature);
        locationWeatherStatus.setCurrentWeather(locationWeatherDto.currentWeather);
        locationWeatherStatus.setWeatherStatusDate(dateUtils.getDateFromWeatherResponseDate(locationWeatherDto.date));
        locationWeatherStatus.setLastUpdate();

        return locationWeatherStatus;
    }
}
