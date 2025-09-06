package org.esosa.api.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "blog_comentario", schema = "blogs")
public class Comentario {
    @Id
    private Long idComentario;

    @Column("id_blog")
    private Long idBlog;

    @Column("id_usuario")
    private Long idUsuario;

    @Column("contenido")
    private String contenido;

    public Comentario( Long idBlog, Long idUsuario, String contenido) {
        this.idBlog = idBlog;
        this.idUsuario = idUsuario;
        this.contenido = contenido;
    }

    public Long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Long idComentario) {
        this.idComentario = idComentario;
    }

    public Long getIdBlog() {
        return idBlog;
    }

    public void setIdBlog(Long idBlog) {
        this.idBlog = idBlog;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
