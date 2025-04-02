package com.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.dto.RegisterRequest;
import com.security.dto.UserDTO;
import com.security.reqeuest.AuthenticationRequest;
import com.security.response.AuthenticationResponse;
import com.security.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@Tag(name = "Autenticação", description = "Endpoints para autenticação e registro de usuários")
public class AuthenticationController {
    
    private final AuthenticationService authenticationService;
    

    @PostMapping("/register")
    @Operation(
        summary = "Registrar novo usuário",
        description = "Cria um novo usuário no sistema e retorna um token JWT válido"
    )
    public ResponseEntity<UserDTO> register(@RequestBody RegisterRequest request) {
        AuthenticationResponse response = authenticationService.register(request);

        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + response.getAccessToken())
                .header("Refresh-Token", response.getRefreshToken())
                .body(response.getUser()); 
    }

   
    @PostMapping("/authenticate")
    @Operation(
        summary = "Autenticar usuário",
        description = "Autentica o usuário com email e senha e retorna um token JWT válido"
    )
    public ResponseEntity<UserDTO> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);

        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + response.getAccessToken())
                .header("Refresh-Token2", response.getRefreshToken())
                .body(response.getUser());
    }
    
}