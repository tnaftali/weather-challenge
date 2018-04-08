package com.example.weatherchallenge.model.dto;

import java.util.ArrayList;
import java.util.Date;

public class BoardDto {
    public BoardDto(String name, String username, ArrayList<String> locations) {
        this.name = name;
        this.username = username;
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.locations = locations;
    }

    public String name;
    public String username;
    public Date createdAt;
    public Date updatedAt;
    public ArrayList<String> locations;
}
