package org.esosa.api.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.esosa.api.backend.dto.Request.BlogRequestDto;
import org.esosa.api.backend.dto.Request.BlogUpdateRequestDto;
import org.esosa.api.backend.dto.Response.BlogDto;
import org.esosa.api.backend.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @Operation(
            summary = "Crear un nuevo blog",
            description = """
                    Crea un nuevo blog con los datos proporcionados.
                    - `titulo`: Título del blog (obligatorio)
                    - `tema`: Tema del blog (obligatorio)
                    - `contenido`: Contenido completo del blog (obligatorio)
                    - `periodicidad`: Periodicidad de publicación (opcional)
                    - `usuarioId`: ID del autor (obligatorio)
                    """,
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blog creado correctamente",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida o datos incompletos",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No autorizado",
                    content = @Content)
    })
    @PostMapping("/crear")
    public ResponseEntity<String> crearBlog(@Valid @RequestBody BlogRequestDto blogRequestDto) {
        var nuevoBlog = blogService.crearBlog(
                blogRequestDto.getTitulo(),
                blogRequestDto.getTema(),
                blogRequestDto.getContenido(),
                blogRequestDto.getPeriodicidad(),
                blogRequestDto.getUsuarioId()
        );
        return ResponseEntity.ok("Blog creado con ID: " + nuevoBlog.getIdBlog());
    }

    @Operation(
            summary = "Actualizar un blog existente",
            description = """
                    Actualiza los datos de un blog existente identificado por su ID.
                    Los campos que se pueden actualizar son:
                    - `titulo`
                    - `tema`
                    - `contenido`
                    - `periodicidad`
                    """,
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blog actualizado correctamente",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida o datos incompletos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Blog no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No autorizado",
                    content = @Content)
    })
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarBlog(
            @PathVariable Long id,
            @Valid @RequestBody BlogUpdateRequestDto blogRequestDto
    ) {
        var actualizadoBlog = blogService.editarBlog(
                id,
                blogRequestDto.getTitulo(),
                blogRequestDto.getTema(),
                blogRequestDto.getContenido(),
                blogRequestDto.getPeriodicidad()
        );
        return ResponseEntity.ok("Blog actualizado con ID: " + actualizadoBlog.getIdBlog());
    }

    @Operation(
            summary = "Obtener todos los blogs",
            description = """
                    Retorna la lista completa de blogs.
                    Cada blog incluye:
                    - Autor (datos del usuario)
                    - Lista de imágenes asociadas
                    - Lista de comentarios con autor de cada comentario
                    """,
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de blogs obtenida correctamente",
                    content = @Content(schema = @Schema(implementation = BlogDto.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado",
                    content = @Content)
    })
    @GetMapping("/todos")
    public ResponseEntity<List<BlogDto>> obtenerTodosLosBlogs() {
        var blogs = blogService.obtenerBlogs();
        return ResponseEntity.ok(blogs);
    }

    @Operation(
            summary = "Obtener un blog por ID",
            description = "Retorna un solo blog identificado por su ID, incluyendo autor, imágenes y comentarios",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blog obtenido correctamente",
                    content = @Content(schema = @Schema(implementation = BlogDto.class))),
            @ApiResponse(responseCode = "404", description = "Blog no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No autorizado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> obtenerBlogPorId(@PathVariable("id") Long id) {
        BlogDto blog = blogService.obtenerBlog(id);
        return ResponseEntity.ok(blog);
    }
}
