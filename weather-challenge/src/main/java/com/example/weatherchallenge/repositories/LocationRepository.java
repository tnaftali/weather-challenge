package com.example.weatherchallenge.repositories;

import com.example.weatherchallenge.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    boolean existsLocationByName(String name);
    Location findLocationByName(String name);
}