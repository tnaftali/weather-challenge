package com.example.weatherchallenge.iservices;

import com.example.weatherchallenge.model.dto.LocationCriteriaDto;
import com.example.weatherchallenge.model.dto.LocationDto;

public interface ILocationService {
    LocationDto addLocationToBoard(LocationCriteriaDto criteria);
    void deleteLocationFromBoard(LocationCriteriaDto criteria);
}
