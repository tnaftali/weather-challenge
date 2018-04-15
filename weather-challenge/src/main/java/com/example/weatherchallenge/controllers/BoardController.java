package com.example.weatherchallenge.controllers;

import com.example.weatherchallenge.iservices.IBoardService;
import com.example.weatherchallenge.model.dto.BoardDto;
import com.example.weatherchallenge.model.dto.LocationWeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {
    @Autowired
    private IBoardService boardService;

    @CrossOrigin
    @GetMapping("/boards/{username}")
    public List<BoardDto> getAllBoardsByUser(@PathVariable(value = "username") String username) {
        return this.boardService.getAllBoardsByUser(username);
    }

    @CrossOrigin
    @GetMapping("/boards/{username}/{name}")
    public ArrayList<LocationWeatherDto> getAllBoardLocationsWeather(@PathVariable(value = "username") String username, @PathVariable(value = "name") String name) {
        return this.boardService.getAllBoardLocationsWeather(username, name);
    }

    @CrossOrigin
    @PostMapping("/boards/{username}/{name}")
    public ResponseEntity<BoardDto> createBoard(@PathVariable(value = "username") String username, @PathVariable(value = "name") String name) {
        boardService.createBoard(name, username);

        return ResponseEntity.ok().build();
    }
}