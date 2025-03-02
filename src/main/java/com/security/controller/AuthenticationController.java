package com.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.dto.AuthenticationRequest;
import com.security.dto.AuthenticationResponse;
import com.security.dto.RegisterRequest;
import com.security.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Permite requisições de qualquer origem
@Tag(name = "Autenticação", description = "Endpoints para autenticação e registro de usuários")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(
        summary = "Registrar novo usuário",
        description = "Cria um novo usuário no sistema e retorna um token JWT válido"
    )
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    @Operation(
        summary = "Autenticar usuário",
        description = "Autentica o usuário com email e senha e retorna um token JWT válido"
    )
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
    	 System.out.println("Authentication attempt for email: " + request.getEmail());
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}