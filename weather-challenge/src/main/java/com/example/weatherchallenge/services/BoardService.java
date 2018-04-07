package com.example.weatherchallenge.services;

import com.example.weatherchallenge.exceptions.ResourceNotFoundException;
import com.example.weatherchallenge.factories.BoardFactory;
import com.example.weatherchallenge.integration.WeatherIntegrationService;
import com.example.weatherchallenge.iservices.IBoardService;
import com.example.weatherchallenge.mappers.EntityMapper;
import com.example.weatherchallenge.model.Board;
import com.example.weatherchallenge.model.User;
import com.example.weatherchallenge.model.dto.BoardDto;
import com.example.weatherchallenge.model.dto.LocationCriteriaDto;
import com.example.weatherchallenge.repositories.BoardRepository;
import com.example.weatherchallenge.repositories.UserRepository;
import com.example.weatherchallenge.validators.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService implements IBoardService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @Autowired
    private BoardFactory boardFactory;

    @Autowired
    private EntityMapper entityMapper;

    @Autowired
    private WeatherIntegrationService weatherIntegrationService;

    public BoardDto getBoardByUserAndName(String username, String name) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        return boardRepository.findAllByUserAndName(user, name).toDto();
    }

    public Board createBoard(String boardName, String username) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        boardValidator.validateBoardName(boardName);

        return boardRepository.save(boardFactory.create(boardName, user));
    }

    public List<BoardDto> getAllBoardsByUser(String username) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        return entityMapper.boardListToBoardDtoList(boardRepository.findAllByUser(user));
    }

    public BoardDto addLocationToBoard(LocationCriteriaDto locationCriteriaDto) {
        User user = userRepository.findById(locationCriteriaDto.username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", locationCriteriaDto.username));

        weatherIntegrationService.getCityCurrentWeather(locationCriteriaDto.location);

        return new BoardDto("asd", "tobias");
    }
}
