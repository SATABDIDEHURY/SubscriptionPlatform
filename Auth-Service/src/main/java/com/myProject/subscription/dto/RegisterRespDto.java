package com.myProject.subscription.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRespDto {
    private String name;
    private String email;
}
