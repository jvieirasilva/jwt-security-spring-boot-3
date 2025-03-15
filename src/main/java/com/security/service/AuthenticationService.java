package com.security.service;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.dto.AuthenticationRequest;
import com.security.dto.RegisterRequest;
import com.security.dto.UserDTO;
import com.security.model.Role;
import com.security.model.User;
import com.security.repository.UserRepository;
import com.security.response.AuthenticationResponse;

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
                .isActive(true)
                .isNotLocked(true)
                .isChangePassword(false)
                .build();

        repository.save(user);

        // Envia e-mail de boas-vindas com a senha
        emailService.sendEmail(request.getEmail(), request.getPassword());

        // Geração dos tokens JWT
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        // Criando o DTO do usuário
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .profileImageUrl(user.getProfileImageUrl())
                .lastLoginDate(user.getLastLoginDate())
                .lastLoginDateDisplay(user.getLastLoginDateDisplay())
                .joinDate(user.getJoinDate())
                .role(user.getRole().name())
                //.authorities(user.getAuthorities()) // Como já é um String[], não precisa de conversão
                .isActive(user.isActive())
                .isNotLocked(user.isNotLocked())
                .isChangePassword(user.isChangePassword())
                .build();

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .user(userDTO) // Inclui o usuário na resposta
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

            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .fullName(user.getFullName())
                    .email(user.getEmail())
                    .profileImageUrl(user.getProfileImageUrl())
                    .lastLoginDate(user.getLastLoginDate())
                    .lastLoginDateDisplay(user.getLastLoginDateDisplay())
                    .joinDate(user.getJoinDate())
                    .role(user.getRole().name())
                    //.authorities(user.getAuthorities())
                    .isActive(user.isActive())
                    .isNotLocked(user.isNotLocked())
                    .isChangePassword(user.isChangePassword())
                    .build();

            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .user(userDTO)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Falha na autenticação", e);
        }
    }


}