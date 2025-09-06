package org.esosa.api.backend.controller;

import org.esosa.api.backend.dto.Request.LoginRequest;

import org.esosa.api.backend.dto.Response.LoginResponse;
import org.esosa.api.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Intentando iniciar sesión para: " + loginRequest.getCorreoElectronico()+ " " +loginRequest.getPassword());
        LoginResponse response = authService.login(
                loginRequest.getCorreoElectronico(),
                loginRequest.getPassword()
        );
        return ResponseEntity.ok(response);
    }
}
