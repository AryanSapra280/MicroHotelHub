package com.userservice.UserService.services;

import java.util.*;

import com.userservice.UserService.entities.User;

public interface IUserService {
    List<User> getAllUser();
    User getUserByEmail(String email);
    User saveUser(User user);
    User updateUser(User user);
}
