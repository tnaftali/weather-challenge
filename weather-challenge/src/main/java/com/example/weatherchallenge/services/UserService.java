package com.example.weatherchallenge.services;

import com.example.weatherchallenge.factories.UserFactory;
import com.example.weatherchallenge.iservices.IUserService;
import com.example.weatherchallenge.mappers.EntityMapper;
import com.example.weatherchallenge.model.User;
import com.example.weatherchallenge.model.dto.UserDto;
import com.example.weatherchallenge.repositories.BoardRepository;
import com.example.weatherchallenge.repositories.UserRepository;
import com.example.weatherchallenge.searchers.BoardSearcher;
import com.example.weatherchallenge.searchers.UserSearcher;
import com.example.weatherchallenge.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserFactory userFactory;
    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private UserSearcher userSearcher;
    @Autowired
    private BoardRepository boardRepository;

    public UserDto createUser(String name) {
        userValidator.validate(name);

        User user  = userFactory.create(name);
        userRepository.save(user);

        return entityMapper.userToDto(user);
    }

    public List<UserDto> getAllUsers() {
        return entityMapper.userListToUserDtoList(userRepository.findAll());
    }

    public UserDto getById(String username) {
        User user = userSearcher.findById(username);

        return entityMapper.userToDto(user);
    }

    public void deleteUser(String username) {
        User user = userSearcher.findById(username);

        boardRepository.deleteAllByUser(user);

        userRepository.delete(user);
    }
}
