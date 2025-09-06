package org.esosa.api.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "blog_imagen", schema = "blogs")
public class Imagen {
    @Id
    private Long idImagen;

    @Column("id_blog")
    private Long idBlog;

    @Column("url")
    private String url;

    @Column("descripcion")
    private String descripcion;

    public Imagen(Long idBlog, String url, String descripcion) {
        this.idBlog = idBlog;
        this.url = url;
        this.descripcion = descripcion;
    }

    public Long getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Long idImagen) {
        this.idImagen = idImagen;
    }

    public Long getIdBlog() {
        return idBlog;
    }

    public void setIdBlog(Long idBlog) {
        this.idBlog = idBlog;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
