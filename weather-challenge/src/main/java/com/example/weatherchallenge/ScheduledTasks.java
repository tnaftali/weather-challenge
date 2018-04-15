package com.example.weatherchallenge;

import com.example.weatherchallenge.services.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
        private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    private LocationService locationService;

    @Scheduled(fixedRate = 1800000)
    public void getAllLocationsWeather() {
        logger.info("GetAllLocationsWeather scheduled task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        String locationsUpdated = this.locationService.getAllLocationsWeather();
        logger.info("Locations updated: {}", locationsUpdated);
    }
}
