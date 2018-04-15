package com.example.weatherchallenge.controllers;

import com.example.weatherchallenge.iservices.IUserService;
import com.example.weatherchallenge.model.User;
import com.example.weatherchallenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IUserService userService;

    @CrossOrigin
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @CrossOrigin
    @PostMapping("/users/{username}")
    public ResponseEntity<User>  createUser(@PathVariable(value = "username") String username) {
        userService.createUser(username);

        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @Transactional
    @DeleteMapping("/users/{username}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "username") String username) {
        userService.deleteUser(username);

        return ResponseEntity.ok().build();
    }
}