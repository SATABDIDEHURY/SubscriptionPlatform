package com.myProject.subscription.entity;

import jakarta.persistence.*;
import lombok.*;

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

//    @Enumerated(EnumType.STRING)
//    private Role role;

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
