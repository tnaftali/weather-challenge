package com.example.weatherchallenge.model.dto;

import java.util.Date;

public class UserDto {
    public UserDto(String username , Date createdAt) {
        this.username = username;
        this.createdAt = createdAt;
    }

    public String username ;
    public Date createdAt;
}
