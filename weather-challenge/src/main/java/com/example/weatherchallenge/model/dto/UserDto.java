package com.example.weatherchallenge.model.dto;

import java.util.Date;

public class UserDto {
    public UserDto(String name, Date createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

    public String name;
    public Date createdAt;
}
