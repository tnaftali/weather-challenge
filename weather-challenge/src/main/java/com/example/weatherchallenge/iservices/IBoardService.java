package com.example.weatherchallenge.iservices;

import com.example.weatherchallenge.model.dto.BoardDto;
import com.example.weatherchallenge.model.dto.LocationWeatherDto;

import java.util.ArrayList;
import java.util.List;

public interface IBoardService {
    BoardDto createBoard(String name, String username);
    List<BoardDto> getAllBoardsByUser(String username);
    ArrayList<LocationWeatherDto> getAllBoardLocationsWeather(String username, String boardName);
}
