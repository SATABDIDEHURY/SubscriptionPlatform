package com.myProject.AuthService.service.impl;

import com.myProject.AuthService.dto.LoginDto;
import com.myProject.AuthService.dto.LoginResponseDto;
import com.myProject.AuthService.dto.RegisterDto;
import com.myProject.AuthService.dto.RegisterRespDto;
import com.myProject.AuthService.entity.User;
import com.myProject.AuthService.exception.InvalidCredentialException;
import com.myProject.AuthService.exception.UserAlreadyExistsException;
import com.myProject.AuthService.exception.UserNotFoundException;
import com.myProject.AuthService.jwt.JwtUtil;
import com.myProject.AuthService.repository.UserRepository;
import com.myProject.AuthService.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public RegisterRespDto resister(@NonNull RegisterDto registerDto){
        userRepository.findByEmail(registerDto.getEmail())
                .ifPresent(user1 ->{
                    throw new UserAlreadyExistsException("Email Already Exists");
                });
//        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

//        Always try to use constructor instead of @Builder, @Setter, @AllArgsConstructor for Entity classes
//        User user = User.builder()
//                .name(registerDto.getName())
//                .email(registerDto.getEmail())
//                .password(passwordEncoder.encode(registerDto.getPassword()))
//                .build();
//        System.out.println("Name : "+registerDto.getName());
        User user = new User(registerDto.getName(),
                registerDto.getEmail(),
                passwordEncoder.encode(registerDto.getPassword()),
                LocalDateTime.now()
        );

        User savedUser = userRepository.save(user);
        return new RegisterRespDto(savedUser.getName(), savedUser.getEmail(), savedUser.getCreatedAt());
    }


    public LoginResponseDto login(LoginDto request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new UserNotFoundException("User Not present for this mail."));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new InvalidCredentialException("Invalid Credential");
        }

        return new LoginResponseDto(user.getId().toString(), jwtUtil.generateToken(user.getId().toString()));

    }

}
