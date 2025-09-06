package org.esosa.api.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.esosa.api.backend.service.ImagenService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/images")
@Tag(name = "Imagenes Blogs", description = "Operaciones relacionadas con las imagenes de los blogs")
public class ImagenController {
    private final ImagenService imagenService;

    public ImagenController(ImagenService imagenService) {
        this.imagenService = imagenService;
    }

    @Operation(
            summary = "Añadir imagen al blog",
            description = "Añade imagenes al blog especifico",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createImagen(
            @RequestParam("file") MultipartFile file,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("idBlog") Long idBlog) {
        try {
            String uploadDir = System.getProperty("user.dir") + "/uploads/imagenes/";

            File directorio = new File(uploadDir);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            String filePath = uploadDir + file.getOriginalFilename();
            File destino = new File(filePath);

            file.transferTo(destino);

            var nuevaImagen = imagenService.createImagen(idBlog, "uploads/imagenes/" + file.getOriginalFilename(), descripcion);

            return ResponseEntity.ok("Imagen creada con ID: " + nuevaImagen.getIdImagen());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error al subir el archivo: " + e.getMessage());
        }
    }

}
