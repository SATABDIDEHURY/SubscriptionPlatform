package com.myProject.subscription.service;

import com.myProject.subscription.dto.LoginDto;
import com.myProject.subscription.dto.RegisterDto;
import com.myProject.subscription.dto.RegisterRespDto;
import com.myProject.subscription.entity.User;

public interface AuthService {
//    public Optional<User> findByEmail(User user);
    public RegisterRespDto resister(RegisterDto user);
    public String login(LoginDto request);
}
