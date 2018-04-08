package com.example.weatherchallenge.ifactories;

import com.example.weatherchallenge.model.User;

public interface IUserFactory extends IFactory<User, String> {
    User create(String Username);
}
