package org.esosa.api.backend.service;

import org.esosa.api.backend.model.Usuario;
import org.esosa.api.backend.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;

    }

    public Usuario crearUsuario(String  nombres, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String paisResidencia, String correoElectronico, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Usuario usuario = new Usuario( nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, paisResidencia, correoElectronico, encodedPassword, 2L);
        return usuarioRepository.save(usuario);
    }
}
