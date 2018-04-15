package com.example.weatherchallenge.controllers;

import com.example.weatherchallenge.iservices.ILocationService;
import com.example.weatherchallenge.model.Location;
import com.example.weatherchallenge.model.dto.LocationCriteriaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class LocationController {
    @Autowired
    private ILocationService locationService;

    @CrossOrigin
    @PostMapping("/locations/add")
    public ResponseEntity<Location> addLocationToBoard(@Valid @RequestBody LocationCriteriaDto locationCriteriaDto) {
        locationService.addLocationToBoard(locationCriteriaDto);

        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @DeleteMapping("/locations/delete")
    public ResponseEntity<Location> deleteLocationFromBoard(@Valid @RequestBody LocationCriteriaDto locationCriteriaDto) {
        locationService.deleteLocationFromBoard(locationCriteriaDto);

        return ResponseEntity.ok().build();
    }
}