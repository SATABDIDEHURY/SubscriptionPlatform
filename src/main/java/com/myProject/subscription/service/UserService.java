package com.myProject.subscription.service;

import com.myProject.subscription.dto.UserDto;
import com.myProject.subscription.entity.User;

import java.util.Optional;

public interface UserService {
//    public Optional<User> findByEmail(User user);
    public User resisterUser(UserDto user);
}
