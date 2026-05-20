package com.myProject.subscription.controller;

import com.myProject.subscription.dto.UserDto;
import com.myProject.subscription.entity.User;
import com.myProject.subscription.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserDto user){
        System.out.println("It Is controller class");

        return userService.resisterUser(user);
    }
}
