package com.example.weatherchallenge.services;

import com.example.weatherchallenge.factories.BoardFactory;
import com.example.weatherchallenge.integration.WeatherIntegrationService;
import com.example.weatherchallenge.integration.model.dto.Weather.WeatherServiceResponseDto;
import com.example.weatherchallenge.iservices.IBoardService;
import com.example.weatherchallenge.mappers.EntityMapper;
import com.example.weatherchallenge.model.Board;
import com.example.weatherchallenge.model.Location;
import com.example.weatherchallenge.model.User;
import com.example.weatherchallenge.model.dto.BoardDto;
import com.example.weatherchallenge.model.dto.LocationWeatherDto;
import com.example.weatherchallenge.repositories.BoardRepository;
import com.example.weatherchallenge.searchers.BoardSearcher;
import com.example.weatherchallenge.searchers.UserSearcher;
import com.example.weatherchallenge.validators.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService implements IBoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardSearcher boardSearcher;
    @Autowired
    private BoardValidator boardValidator;
    @Autowired
    private BoardFactory boardFactory;
    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private UserSearcher userSearcher;
    @Autowired
    private WeatherIntegrationService weatherIntegrationService;

    public BoardDto createBoard(String boardName, String username) {
        User user = userSearcher.findById(username);

        boardValidator.validate(boardName, user);

        Board board = boardFactory.create(boardName, user);
        boardRepository.save(board);

        return entityMapper.boardToDto(board);
    }

    public List<BoardDto> getAllBoardsByUser(String username) {
        User user = userSearcher.findById(username);

        return entityMapper.boardListToBoardDtoList(boardRepository.findAllByUser(user));
    }

    public ArrayList<LocationWeatherDto> getAllBoardLocationsWeather(String username, String boardName) {
        User user = userSearcher.findById(username);

        Board board = boardRepository.findBoardByUserAndName(user, boardName);
        Location[] boardLocations = board.getLocationsArray();
        String stringBoardLocations =  board.getLocationsAsString(); // todo: searcher
        if (!stringBoardLocations.equals("")) {
            WeatherServiceResponseDto response = weatherIntegrationService.getLocationsCurrentWeather(stringBoardLocations);

            return entityMapper.mapToLocationWeatherDtoList(response, boardLocations);
        }

        return new ArrayList<LocationWeatherDto>() {};
    }
}
