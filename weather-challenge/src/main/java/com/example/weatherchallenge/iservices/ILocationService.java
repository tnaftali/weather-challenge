package com.example.weatherchallenge.iservices;

import com.example.weatherchallenge.model.dto.LocationCriteriaDto;

public interface ILocationService {
    void addLocationToBoard(LocationCriteriaDto criteria);
    void deleteLocationFromBoard(LocationCriteriaDto criteria);
    String getAllLocationsWeather();
}
