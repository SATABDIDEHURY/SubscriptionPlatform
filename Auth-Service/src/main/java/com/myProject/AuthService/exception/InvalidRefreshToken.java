package com.myProject.AuthService.exception;

public class InvalidRefreshToken extends RuntimeException{
    public InvalidRefreshToken(String message){
        super(message);
    }
}
