package com.example.weatherchallenge.validators;

import com.example.weatherchallenge.exceptions.DuplicateEntityException;
import com.example.weatherchallenge.ivalidators.IBoardValidator;
import com.example.weatherchallenge.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardValidator implements IBoardValidator {

    @Autowired
    private BoardRepository boardRepository;

    public void validateBoardName(String boardName) {
        if (boardRepository.existsBoardByName(boardName)) {
            throw new DuplicateEntityException("Board", "name", boardName);
        }
    }
}
