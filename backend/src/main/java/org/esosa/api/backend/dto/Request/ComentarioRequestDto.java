package org.esosa.api.backend.dto.Request;

import jakarta.validation.constraints.NotBlank;

public class ComentarioRequestDto {
    private Long blogId;

    private Long usuarioId;

    @NotBlank(message = "El contenido es obligatorio")
    private String contenido;

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
