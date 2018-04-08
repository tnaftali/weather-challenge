package com.example.weatherchallenge.controllers;

import com.example.weatherchallenge.iservices.ILocationService;
import com.example.weatherchallenge.model.dto.LocationCriteriaDto;
import com.example.weatherchallenge.model.dto.LocationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class LocationController {

    @Autowired
    ILocationService locationService;

    @PostMapping("/locations/add")
    public LocationDto addLocationToBoard(@Valid @RequestBody LocationCriteriaDto locationCriteriaDto) {
        return locationService.addLocationToBoard(locationCriteriaDto);
    }

    @DeleteMapping("/locations/delete")
    public void deleteLocationFromBoard(@Valid @RequestBody LocationCriteriaDto locationCriteriaDto) {
        locationService.deleteLocationFromBoard(locationCriteriaDto);
    }
}