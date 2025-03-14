package com.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

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
        
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
                )
            );

            var user = repository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);

            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        } catch (Exception e) {
            System.err.println("Erro na autenticação: " + e.getMessage());
            throw new RuntimeException("Falha na autenticação", e);
        }
    }

}