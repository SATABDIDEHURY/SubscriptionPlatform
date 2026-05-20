package com.myProject.subscription.service.impl;

import com.myProject.subscription.dto.UserDto;
import com.myProject.subscription.entity.User;
import com.myProject.subscription.repository.UserRepository;
import com.myProject.subscription.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User resisterUser(@NonNull UserDto userDto){
        userRepository.findByEmail(userDto.getEmail())
                .ifPresent(user1 ->{
                    throw new RuntimeException("Email Already Exists");
                });
//        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();

        System.out.println("It Is Hitting");

        return userRepository.save(user);
    }
}
