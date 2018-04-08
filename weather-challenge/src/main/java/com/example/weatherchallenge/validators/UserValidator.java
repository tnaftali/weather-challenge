package com.example.weatherchallenge.validators;

import com.example.weatherchallenge.exceptions.DuplicateEntityException;
import com.example.weatherchallenge.ivalidators.IValidator;
import com.example.weatherchallenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidator implements IValidator<String> {
    @Autowired
    private UserRepository userRepository;

    private String entity = "User";
    private String validationProperty = "Username";

    public void validate(String username) {
        if (userRepository.existsUserByUsername(username)) {
            throw new DuplicateEntityException(this.entity, this.validationProperty, username);
        }
    }
}
