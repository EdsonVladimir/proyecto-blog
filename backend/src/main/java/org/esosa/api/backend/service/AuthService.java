package org.esosa.api.backend.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.esosa.api.backend.dto.Response.LoginResponse;
import org.esosa.api.backend.model.Usuario;
import org.esosa.api.backend.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(String correo, String password) {
        System.out.println("Autenticando usuario: " + correo + " con contraseña: " + password);
        Usuario usuario = usuarioRepository.findByCorreoElectronico(correo);
        System.out.println("Usuario encontrado: " + usuario);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String jwt = Jwts.builder()
                .setSubject(usuario.getCorreoElectronico())
                .claim("role", usuario.getIdRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000)) // 1 hora
                .signWith(key)
                .compact();
        System.out.println("JWT generado: " + jwt + " para el usuario: " + usuario);
        return new LoginResponse(jwt, usuario);
    }
}
