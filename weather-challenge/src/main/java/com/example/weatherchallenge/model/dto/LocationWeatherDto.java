package com.example.weatherchallenge.model.dto;

public class LocationWeatherDto {

    public LocationWeatherDto(String name, String title, String date, String temperature, String unit, String currentWeather) {
        this.name = name;
        this.title = title;
        this.date = date;
        this.temperature = temperature;
        this.unit = unit;
        this.currentWeather = currentWeather;
    }

    public String name;
    public String title;
    public String date;
    public String temperature;
    public String unit;
    public String currentWeather;
}
