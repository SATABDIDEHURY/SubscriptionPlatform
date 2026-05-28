package com.myProject.AuthService.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
