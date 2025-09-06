package org.esosa.api.backend.controller;

import jakarta.validation.Valid;
import org.esosa.api.backend.dto.Request.UsuarioRequestDto;
import org.esosa.api.backend.model.Usuario;
import org.esosa.api.backend.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearUsuario(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto) {
        Usuario nuevoUsuario = usuarioService.crearUsuario(
                usuarioRequestDto.getNombres(),
                usuarioRequestDto.getApellidoPaterno(),
                usuarioRequestDto.getApellidoMaterno(),
                usuarioRequestDto.getFechaNacimiento(),
                usuarioRequestDto.getPaisResidencia(),
                usuarioRequestDto.getCorreoElectronico(),
                usuarioRequestDto.getPassword()
        );
        return ResponseEntity.ok("Usuario creado con ID: " + nuevoUsuario.getIdUsuario());
    }
}
