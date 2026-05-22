package com.myProject.subscription.jwt;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;

@Component
public class JwtUtil {
    private String secret;
    private long expiration;
    private Key siningKey;

    @PostConstruct
    public void init(){

    }
}
