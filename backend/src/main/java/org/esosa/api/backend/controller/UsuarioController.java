package org.esosa.api.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.esosa.api.backend.dto.Request.UsuarioRequestDto;
import org.esosa.api.backend.model.Usuario;
import org.esosa.api.backend.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios Autores", description = "Operaciones relacionadas con los usuarios autores de blogs")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(
            summary = "Registrar Author",
            description = "Crea un nuevo autor para poder crear Blogs"
    )
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

    @Operation(
            summary = "Obtener Authores",
            description = "Obtener todos los autores registrados"
    )
    @GetMapping("/authores")
    public ResponseEntity<?> obtenerUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerUsuarios());
    }
}
