package org.esosa.api.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.esosa.api.backend.dto.Request.ComentarioRequestDto;
import org.esosa.api.backend.service.ComentarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comentarios")
@Tag(name = "Comentar Blogs", description = "Operaciones relacionadas con los comentarios en los blogs")
public class ComentarioController {
    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @Operation(
            summary = "Crear comentario",
            description = "Crea un nuevo comentario para un blog especifico",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @PostMapping("/crear")
    public ResponseEntity<String> crearComentario(@Valid @RequestBody ComentarioRequestDto comentarioRequestDto) {
        var nuevoComentario = comentarioService.crearComentario(
                comentarioRequestDto.getBlogId(),
                comentarioRequestDto.getUsuarioId(),
                comentarioRequestDto.getContenido()
        );

        return ResponseEntity.ok("Comentario creado con ID: " + nuevoComentario.getIdComentario());
    }
}
