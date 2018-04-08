package com.example.weatherchallenge.services;

import com.example.weatherchallenge.factories.BoardFactory;
import com.example.weatherchallenge.iservices.IBoardService;
import com.example.weatherchallenge.mappers.EntityMapper;
import com.example.weatherchallenge.model.Board;
import com.example.weatherchallenge.model.User;
import com.example.weatherchallenge.model.dto.BoardDto;
import com.example.weatherchallenge.repositories.BoardRepository;
import com.example.weatherchallenge.searchers.BoardSearcher;
import com.example.weatherchallenge.searchers.UserSearcher;
import com.example.weatherchallenge.validators.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public BoardDto getBoardByUserAndName(String username, String name) {
        User user = userSearcher.findById(username);

        return entityMapper.boardToDto(boardSearcher.findByUserAndName(user, name));
    }

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
}
