package com.example.weatherchallenge.iservices;

import com.example.weatherchallenge.model.Board;
import com.example.weatherchallenge.model.dto.BoardDto;
import com.example.weatherchallenge.model.dto.LocationCriteriaDto;

import java.util.List;

public interface IBoardService {
    Board createBoard(String name, String username);
    List<BoardDto> getAllBoardsByUser(String username);
    BoardDto getBoardByUserAndName(String username, String name);
    BoardDto addLocationToBoard(LocationCriteriaDto locationCriteriaDto);
}
