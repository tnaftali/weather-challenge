package com.example.weatherchallenge.model.dto;

import java.util.Date;

public class LocationDto {
    protected LocationDto() { }

    public LocationDto(String name) {
        this.name = name;
        this.createdAt = new Date();
    }

    public String name;
    public Date createdAt;
}
