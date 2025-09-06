package org.esosa.api.backend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Tag(name = "Iniciar Sesion", description = "Operaciones relacionadas con el inicio de sesión de authores, tanto para crear Blogs como para comentar Blogs")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Intentando iniciar sesión para: " + loginRequest.getCorreoElectronico()+ " " +loginRequest.getPassword());
        LoginResponse response = authService.login(
                loginRequest.getCorreoElectronico(),
                loginRequest.getPassword()
        );
        return ResponseEntity.ok(response);
    }
}
