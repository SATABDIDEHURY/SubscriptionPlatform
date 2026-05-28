package com.myProject.AuthService.service;

import com.myProject.AuthService.entity.RefreshToken;

public interface RefreshTokenService {
    public RefreshToken createRefreshToken(String userid);
    public RefreshToken validateToken(String token);
//    public void deleteByMail(String email);
    public void logout(String refreshToken);
}
