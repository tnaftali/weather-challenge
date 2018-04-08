package com.example.weatherchallenge.factories;

import com.example.weatherchallenge.ifactories.IUserFactory;
import com.example.weatherchallenge.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserFactory implements IUserFactory {
    public User create(String username) {
        return new User(username);
    }
}
