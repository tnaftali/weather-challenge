package com.example.weatherchallenge.factories;

import com.example.weatherchallenge.ifactories.ILocationWeatherStatusFactory;
import com.example.weatherchallenge.model.Location;
import com.example.weatherchallenge.model.LocationWeatherStatus;
import com.example.weatherchallenge.model.dto.LocationWeatherDto;
import com.example.weatherchallenge.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class LocationWeatherStatusFactory implements ILocationWeatherStatusFactory {
    @Autowired
    private DateUtils dateUtils;

    public LocationWeatherStatus create(LocationWeatherDto locationWeatherDto, Location location) {
        Date date = dateUtils.getDateFromWeatherResponseDate(locationWeatherDto.date);

        return new LocationWeatherStatus(locationWeatherDto.name, locationWeatherDto.title, locationWeatherDto.temperature, locationWeatherDto.currentWeather, date, location);
    }
}
