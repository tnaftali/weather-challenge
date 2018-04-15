package com.example.weatherchallenge.services;

import com.example.weatherchallenge.factories.UserFactory;
import com.example.weatherchallenge.iservices.IUserService;
import com.example.weatherchallenge.model.User;
import com.example.weatherchallenge.repositories.BoardRepository;
import com.example.weatherchallenge.repositories.UserRepository;
import com.example.weatherchallenge.searchers.UserSearcher;
import com.example.weatherchallenge.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserFactory userFactory;
    @Autowired
    private UserSearcher userSearcher;
    @Autowired
    private BoardRepository boardRepository;

    public void createUser(String username) {
        userValidator.validateDuplicate(username);

        User user  = userFactory.create(username);
        userRepository.save(user);
    }

    public void deleteUser(String username) {
        userValidator.validateExistence(username);
        User user = userSearcher.findById(username);

        boardRepository.deleteAllByUser(user);
        userRepository.delete(user);
    }
}
