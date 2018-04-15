package com.example.weatherchallenge.searchers;

import com.example.weatherchallenge.exceptions.ResourceNotFoundException;
import com.example.weatherchallenge.model.Board;
import com.example.weatherchallenge.model.User;
import com.example.weatherchallenge.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardSearcher {
    @Autowired
    private BoardRepository boardRepository;

    private static String entity = "Board";
    private static String username = "username";
    private static String name = "name";

    public Board findByUserAndName(User user, String name) {
        Board result = boardRepository.findBoardByUserAndName(user, name);
        if (result == null) {
            throw new ResourceNotFoundException(this.entity, this.username, user.getUsername(), this.name, name);
        }

        return result;
    }
}
