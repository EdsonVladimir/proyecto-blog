package org.esosa.api.backend.dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class BlogDto {
    private Long idBlog;
    private String title;
    private String tema;
    private String contenido;
    private String periodicidad;

    @JsonProperty("fecha_reg")
    private LocalDateTime fechaReg;

    private UsuarioDto autor;
    private List<ImagenDto> imagenes;
    private List<ComentarioDto> comentarios;

    public Long getIdBlog() {
        return idBlog;
    }

    public void setIdBlog(Long idBlog) {
        this.idBlog = idBlog;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public LocalDateTime getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(LocalDateTime fechaReg) {
        this.fechaReg = fechaReg;
    }

    public UsuarioDto getAutor() {
        return autor;
    }

    public void setAutor(UsuarioDto autor) {
        this.autor = autor;
    }

    public List<ImagenDto> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<ImagenDto> imagenes) {
        this.imagenes = imagenes;
    }

    public List<ComentarioDto> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioDto> comentarios) {
        this.comentarios = comentarios;
    }
}