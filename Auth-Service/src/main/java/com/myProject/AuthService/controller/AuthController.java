package com.myProject.AuthService.controller;

import com.myProject.AuthService.dto.*;
import com.myProject.AuthService.entity.RefreshToken;
import com.myProject.AuthService.jwt.JwtUtil;
import com.myProject.AuthService.service.AuthService;
import com.myProject.AuthService.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public RegisterRespDto register(@RequestBody RegisterDto user){
//        System.out.println("It Is controller class"+user.getName());

        return authService.resister(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDto request){
        LoginResponseDto loginResponse = authService.login(request);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(loginResponse.getUserId());
        AuthResponse response = new AuthResponse(loginResponse.getAccessToken(), refreshToken.getToken());

        return ResponseEntity.ok(response);
    }
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody String refreshTokenStr){
        RefreshToken refreshToken = refreshTokenService.validateToken(refreshTokenStr);
        String accessToken = jwtUtil.generateToken(refreshToken.getUserId());

        AuthResponse authResponse = new AuthResponse(accessToken, refreshToken.getToken());

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody String refreshToken){
        refreshTokenService.logout(refreshToken);

        return ResponseEntity.ok("Logged out successfully");
    }
}
