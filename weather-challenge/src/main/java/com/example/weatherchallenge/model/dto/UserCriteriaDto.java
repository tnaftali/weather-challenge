package com.example.weatherchallenge.model.dto;

import javax.validation.constraints.NotBlank;

public class UserCriteriaDto {
    @NotBlank
    private String username;
}
