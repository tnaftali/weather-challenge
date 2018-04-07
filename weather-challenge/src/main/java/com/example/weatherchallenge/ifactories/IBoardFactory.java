package com.example.weatherchallenge.ifactories;

import com.example.weatherchallenge.model.Board;
import com.example.weatherchallenge.model.User;

public interface IBoardFactory {
    Board create(String boardName, User user);
}
