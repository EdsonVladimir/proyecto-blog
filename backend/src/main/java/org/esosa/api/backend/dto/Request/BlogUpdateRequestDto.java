package org.esosa.api.backend.dto.Request;

import jakarta.validation.constraints.NotBlank;

public class BlogUpdateRequestDto {

    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

    @NotBlank(message = "El tema es obligatorio")
    private String tema;

    @NotBlank(message = "El contenido es obligatorio")
    private String contenido;

    private String periodicidad;

    // 👉 NOTA: No incluimos usuarioId aquí

    public @NotBlank(message = "El titulo es obligatorio") String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank(message = "El titulo es obligatorio") String titulo) {
        this.titulo = titulo;
    }

    public @NotBlank(message = "El tema es obligatorio") String getTema() {
        return tema;
    }

    public void setTema(@NotBlank(message = "El tema es obligatorio") String tema) {
        this.tema = tema;
    }

    public @NotBlank(message = "El contenido es obligatorio") String getContenido() {
        return contenido;
    }

    public void setContenido(@NotBlank(message = "El contenido es obligatorio") String contenido) {
        this.contenido = contenido;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }
}