package com.myProject.subscription.service.impl;

import com.myProject.subscription.dto.LoginDto;
import com.myProject.subscription.dto.RegisterDto;
import com.myProject.subscription.dto.RegisterRespDto;
import com.myProject.subscription.entity.User;
import com.myProject.subscription.exception.InvalidCredentialException;
import com.myProject.subscription.exception.UserAlreadyExistsException;
import com.myProject.subscription.exception.UserNotFoundException;
import com.myProject.subscription.jwt.JwtUtil;
import com.myProject.subscription.repository.AuthRepository;
import com.myProject.subscription.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;
    private final JwtUtil jwtUtil;

    public RegisterRespDto resister(@NonNull RegisterDto registerDto){
        authRepository.findByEmail(registerDto.getEmail())
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
                passwordEncoder.encode(registerDto.getPassword())
        );

        User savedUser = authRepository.save(user);
        return new RegisterRespDto(savedUser.getName(), savedUser.getEmail());
    }


    public String login(LoginDto request){
        User user = authRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new UserNotFoundException("User Not present for this mail."));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new InvalidCredentialException("Invalid Credential");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

}
