package com.example.weatherchallenge.factories;

import com.example.weatherchallenge.ifactories.IBoardFactory;
import com.example.weatherchallenge.model.Board;
import com.example.weatherchallenge.model.User;
import org.springframework.stereotype.Service;

@Service
public class BoardFactory implements IBoardFactory {
    public Board create(String boardName, User user) {
        return new Board(boardName, user);
    }
}
