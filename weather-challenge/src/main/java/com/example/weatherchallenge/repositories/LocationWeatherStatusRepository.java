package com.example.weatherchallenge.repositories;

import com.example.weatherchallenge.model.Location;
import com.example.weatherchallenge.model.LocationWeatherStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationWeatherStatusRepository extends JpaRepository<LocationWeatherStatus, Long> {
    LocationWeatherStatus findByLocation(Location location);
}