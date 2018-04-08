package com.example.weatherchallenge.searchers;

import com.example.weatherchallenge.exceptions.ResourceNotFoundException;
import com.example.weatherchallenge.model.User;
import com.example.weatherchallenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSearcher {
    @Autowired
    private UserRepository userRepository;

    private String entity = "User";
    private String username = "Username";

    public User findById(String username) {
        return userRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException(this.entity, this.username, username));
    }
}
