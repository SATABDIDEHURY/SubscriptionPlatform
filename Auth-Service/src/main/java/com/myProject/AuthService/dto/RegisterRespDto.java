package com.myProject.AuthService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RegisterRespDto {
    private String name;
    private String email;
    private LocalDateTime createdAt;
}
