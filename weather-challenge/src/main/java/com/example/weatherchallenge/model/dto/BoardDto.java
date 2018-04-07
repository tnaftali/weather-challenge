package com.example.weatherchallenge.model.dto;

import java.util.Date;

public class BoardDto {
    public BoardDto(String name, String username) {
        this.name = name;
        this.username = username;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public String name;
    public String username;
    public Date createdAt;
    public Date updatedAt;
}
