package com.example.weatherchallenge.validators;

import com.example.weatherchallenge.exceptions.DuplicateEntityException;
import com.example.weatherchallenge.model.User;
import com.example.weatherchallenge.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardValidator {
    @Autowired
    private BoardRepository boardRepository;

    private String entity = "Board";
    private String name = "Name";
    private String username = "Username";

    public void validate(String boardName, User user) {
        if (boardRepository.existsBoardByNameAndUser(boardName, user)) {
            throw new DuplicateEntityException(this.entity, this.name, boardName, this.username, user.getUsername());
        }
    }
}
