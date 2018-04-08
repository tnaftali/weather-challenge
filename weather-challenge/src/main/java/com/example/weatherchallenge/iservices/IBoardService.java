package com.example.weatherchallenge.iservices;

import com.example.weatherchallenge.model.dto.BoardDto;
import java.util.List;

public interface IBoardService {
    BoardDto createBoard(String name, String username);
    List<BoardDto> getAllBoardsByUser(String username);
    BoardDto getBoardByUserAndName(String username, String name);
}
