package com.example.weatherchallenge.model.dto;

import javax.validation.constraints.NotBlank;

public class LocationCriteriaDto {
    @NotBlank
    public String boardName;
    @NotBlank
    public String username;
    @NotBlank
    public String location;
}
