package com.example.weatherchallenge.controllers;

import com.example.weatherchallenge.model.User;
import com.example.weatherchallenge.model.dto.UserDto;
import com.example.weatherchallenge.repositories.UserRepository;
import com.example.weatherchallenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{username}")
    public UserDto getUserById(@PathVariable(value = "username") String username) {
        return userService.getById(username);
    }

    @PostMapping("/users/{username}")
    public UserDto createUser(@PathVariable(value = "username") String username) {
        return userService.createUser(username);
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "username") String username) {
        userService.deleteUser(username);

        return ResponseEntity.ok().build();
    }
}