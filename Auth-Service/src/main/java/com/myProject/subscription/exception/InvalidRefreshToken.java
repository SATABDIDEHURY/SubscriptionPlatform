package com.myProject.subscription.exception;

public class InvalidRefreshToken extends RuntimeException{
    public InvalidRefreshToken(String message){
        super(message);
    }
}
