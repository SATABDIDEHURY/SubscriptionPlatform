package com.myProject.AuthService.service.impl;

import com.myProject.AuthService.entity.RefreshToken;
import com.myProject.AuthService.exception.InvalidRefreshToken;
import com.myProject.AuthService.repository.RefreshTokenRepository;
import com.myProject.AuthService.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Value("${jwt.refresh.expiration}")
    private long refreshExpirationMs;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public RefreshToken createRefreshToken(String userid) {
//        refreshTokenRepository.deleteByEmail(userid);
        refreshTokenRepository.deleteByUserId(userid);
        RefreshToken token = new RefreshToken();
//        System.out.println(userid.getClass()+"  >>  "+ userid);
        token.setUserId(userid);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(LocalDateTime.now().plusSeconds(refreshExpirationMs / 1000));

        return refreshTokenRepository.save(token);
    }

    @Override
//    @Transactional(noRollbackFor = InvalidRefreshToken.class)
    public RefreshToken validateToken(String token){
//        System.out.println("Token >>>>"+token+"####");
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(()-> new InvalidRefreshToken("Invalid Refresh Token."));

        if(refreshToken.getExpiryDate().isBefore(LocalDateTime.now())){
//            refreshTokenRepository.delete(refreshToken);
            deleteRefreshToken(refreshToken);
            throw new InvalidRefreshToken("Refresh Token Expired");
        }

        return refreshToken;
    }

    @Transactional
    private void deleteRefreshToken(RefreshToken refreshToken){
        refreshTokenRepository.delete(refreshToken);
    }
    @Transactional
    public void logout(String refreshToken){
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() ->  new InvalidRefreshToken("Invalid Refresh Token."));

        refreshTokenRepository.delete(token);
    }
}
