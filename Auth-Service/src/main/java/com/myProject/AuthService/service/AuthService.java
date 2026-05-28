package com.myProject.AuthService.service;

import com.myProject.AuthService.dto.LoginDto;
import com.myProject.AuthService.dto.LoginResponseDto;
import com.myProject.AuthService.dto.RegisterDto;
import com.myProject.AuthService.dto.RegisterRespDto;

public interface AuthService {
//    public Optional<User> findByEmail(User user);
    public RegisterRespDto resister(RegisterDto user);
    public LoginResponseDto login(LoginDto request);
}
