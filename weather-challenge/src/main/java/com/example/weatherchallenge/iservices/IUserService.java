package com.example.weatherchallenge.iservices;

import com.example.weatherchallenge.model.dto.UserDto;
import java.util.List;

public interface IUserService {
    UserDto createUser(String name);
    List<UserDto> getAllUsers();
    UserDto getById(String username);
    void deleteUser(String username);
}
