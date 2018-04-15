package com.example.weatherchallenge.ifactories;

import com.example.weatherchallenge.model.Location;
import com.example.weatherchallenge.model.LocationWeatherStatus;
import com.example.weatherchallenge.model.dto.LocationWeatherDto;

public interface ILocationWeatherStatusFactory {
    LocationWeatherStatus create(LocationWeatherDto locationWeatherDto, Location location);
}
