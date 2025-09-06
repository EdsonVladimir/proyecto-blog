package org.esosa.api.backend.dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImagenDto {
    @JsonProperty("id_imagen")
    private Long idImagen;
    private String url;
    private String descripcion;


    public Long getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Long idImagen) {
        this.idImagen = idImagen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
