package com.example.weatherchallenge.validators;

import com.example.weatherchallenge.exceptions.DuplicateEntityException;
import com.example.weatherchallenge.exceptions.ResourceNotFoundException;
import com.example.weatherchallenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {
    @Autowired
    private UserRepository userRepository;

    private String entity = "User";
    private String validationProperty = "Username";

    public void validateDuplicate(String username) {
        if (userRepository.existsUserByUsername(username)) {
            throw new DuplicateEntityException(this.entity, this.validationProperty, username);
        }
    }

    public void validateExistence(String username) {
        if (!userRepository.existsUserByUsername(username)) {
            throw new ResourceNotFoundException(this.entity, this.validationProperty, username);
        }
    }
}
