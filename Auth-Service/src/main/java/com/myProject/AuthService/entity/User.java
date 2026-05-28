package com.myProject.AuthService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private LocalDateTime createdAt;

//    @Enumerated(EnumType.STRING)
//    private Role role;

    public User(String name, String email, String password, LocalDateTime createdAt){
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }
}
