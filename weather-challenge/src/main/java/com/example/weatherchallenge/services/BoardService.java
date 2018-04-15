package com.example.weatherchallenge.services;

import com.example.weatherchallenge.factories.BoardFactory;
import com.example.weatherchallenge.helpers.LocationWeatherStatusHelper;
import com.example.weatherchallenge.iservices.IBoardService;
import com.example.weatherchallenge.mappers.EntityMapper;
import com.example.weatherchallenge.model.Board;
import com.example.weatherchallenge.model.User;
import com.example.weatherchallenge.model.dto.BoardDto;
import com.example.weatherchallenge.model.dto.LocationWeatherDto;
import com.example.weatherchallenge.repositories.BoardRepository;
import com.example.weatherchallenge.searchers.UserSearcher;
import com.example.weatherchallenge.validators.BoardValidator;
import com.example.weatherchallenge.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService implements IBoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardValidator boardValidator;
    @Autowired
    private BoardFactory boardFactory;
    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private UserSearcher userSearcher;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private LocationWeatherStatusHelper locationWeatherStatusHelper;

    public void createBoard(String boardName, String username) {
        userValidator.validateExistence(username);
        User user = userSearcher.findById(username);

        boardValidator.validateDuplicate(boardName, user);
        Board board = boardFactory.create(boardName, user);
        boardRepository.save(board);
    }

    public List<BoardDto> getAllBoardsByUser(String username) {
        userValidator.validateExistence(username);
        User user = userSearcher.findById(username);

        return entityMapper.boardListToBoardDtoList(boardRepository.findAllByUser(user));
    }

    public ArrayList<LocationWeatherDto> getAllBoardLocationsWeather(String username, String boardName) {
        userValidator.validateExistence(username);
        User user = userSearcher.findById(username);

        boardValidator.validateExistence(boardName, user);
        Board board = boardRepository.findBoardByUserAndName(user, boardName);

        return locationWeatherStatusHelper.findByLocations(board.getLocations());
    }
}
