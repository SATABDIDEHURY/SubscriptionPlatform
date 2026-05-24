package com.myProject.subscription.service;

import com.myProject.subscription.entity.RefreshToken;

public interface RefreshTokenService {
    public RefreshToken createRefreshToken(String email);
    public RefreshToken validateToken(String token);
//    public void deleteByMail(String email);
    public void logout(String refreshToken);
}
