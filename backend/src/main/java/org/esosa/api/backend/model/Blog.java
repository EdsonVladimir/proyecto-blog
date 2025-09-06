package org.esosa.api.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "blog", schema = "blogs")
public class Blog {

    @Id
    private Long idBlog;

    @Column("title")
    private String title;

    @Column("tema")
    private String tema;

    @Column("contenido")
    private String contenido;

    @Column("periodicidad")
    private String periodicidad;

    @Column("id_usuario")
    private Long id_usuario;

    public Blog(String title, String tema, String contenido, String periodicidad, Long id_usuario) {
        this.title = title;
        this.tema = tema;
        this.contenido = contenido;
        this.periodicidad = periodicidad;
        this.id_usuario = id_usuario;
    }

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

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }
}
