package com.myProject.AuthService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class LoginResponseDto {
    private String userId;
    private String accessToken;
}
