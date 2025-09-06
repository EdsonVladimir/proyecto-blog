package org.esosa.api.backend.dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ComentarioDto {
    @JsonProperty("id_comentario")
    private Long idComentario;

    private String contenido;

    private UsuarioDto usuario;

    public Long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Long idComentario) {
        this.idComentario = idComentario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }
}
