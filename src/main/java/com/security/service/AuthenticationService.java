package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.dto.AuthenticationRequest;
import com.security.dto.AuthenticationResponse;
import com.security.dto.RegisterRequest;
import com.security.model.Role;
import com.security.model.User;
import com.security.repository.UserRepository;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository repository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private EmailService emailService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        
        repository.save(user);
        //send mail 
        emailService.sendEmail(request.getEmail(), request.getPassword());
        
        var jwtToken = "Bearer "+jwtService.generateToken(user);
        var refreshToken = "Bearer "+jwtService.generateRefreshToken(user);
        
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        
        return AuthenticationResponse.builder()
                .accessToken("Bearer "+jwtToken)
                .refreshToken("Bearer "+refreshToken)
                .build();
    }
}