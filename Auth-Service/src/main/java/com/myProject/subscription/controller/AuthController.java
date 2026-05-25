package com.myProject.subscription.controller;

import com.myProject.subscription.dto.AuthResponse;
import com.myProject.subscription.dto.LoginDto;
import com.myProject.subscription.dto.RegisterDto;
import com.myProject.subscription.dto.RegisterRespDto;
import com.myProject.subscription.entity.RefreshToken;
import com.myProject.subscription.entity.User;
import com.myProject.subscription.jwt.JwtUtil;
import com.myProject.subscription.service.AuthService;
import com.myProject.subscription.service.RefreshTokenService;
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
        String accessToken = authService.login(request);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.getEmail());
        AuthResponse response = new AuthResponse(accessToken, refreshToken.getToken());

        return ResponseEntity.ok(response);
    }
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody String refreshTokenStr){
        RefreshToken refreshToken = refreshTokenService.validateToken(refreshTokenStr);
        String accessToken = jwtUtil.generateToken(refreshToken.getEmail());

        AuthResponse authResponse = new AuthResponse(accessToken, refreshToken.getToken());

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody String refreshToken){
        refreshTokenService.logout(refreshToken);

        return ResponseEntity.ok("Logged out successfully");
    }
}
