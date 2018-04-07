package com.example.weatherchallenge.controllers;

import com.example.weatherchallenge.iservices.IBoardService;
import com.example.weatherchallenge.model.Board;
import com.example.weatherchallenge.model.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LocationController {

//    @Autowired
//    ILocationService locationService;

//    @GetMapping("/boards/{username}/{name}")
//    public BoardDto getBoardByUserAndName(@PathVariable(value = "username") String username, @PathVariable(value = "name") String name) {
//        return this.boardService.getBoardByUserAndName(username, name);
//    }
//
//    @GetMapping("/boards/{username}")
//    public List<BoardDto> getAllBoardsByUser(@PathVariable(value = "username") String username) {
//        return this.boardService.getAllBoardsByUser(username);
//    }
//
//    @PostMapping("/boards/{username}/{name}")
//    public Board createBoard(@PathVariable(value = "username") String username, @PathVariable(value = "name") String name) {
//        return boardService.createBoard(name, username);
//    }
}