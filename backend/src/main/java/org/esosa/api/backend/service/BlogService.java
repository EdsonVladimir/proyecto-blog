package org.esosa.api.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.esosa.api.backend.dto.Response.ComentarioDto;
import org.esosa.api.backend.dto.Response.BlogDto;
import org.esosa.api.backend.dto.Response.ImagenDto;
import org.esosa.api.backend.dto.Response.UsuarioDto;
import org.esosa.api.backend.model.Blog;
import org.esosa.api.backend.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    public BlogService(BlogRepository blogRepository, JdbcTemplate jdbcTemplate) {
        this.blogRepository = blogRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public Blog crearBlog(String titulo, String tema, String contenido, String periodicidad, Long usuarioId) {
        Blog blog = new Blog(titulo, tema, contenido, periodicidad, usuarioId);
        return blogRepository.save(blog);
    }

    public Blog editarBlog(Long id, String titulo, String tema, String contenido, String periodicidad) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog no encontrado"));
        blog.setTitle(titulo);
        blog.setTema(tema);
        blog.setContenido(contenido);
        blog.setPeriodicidad(periodicidad);
        return blogRepository.save(blog);
    }

    public List<BlogDto> obtenerBlogs() {
        String sql = """
            SELECT
                b.id_blog,
                b.title,
                b.tema,
                b.contenido,
                b.periodicidad,
                b.fecha_reg,
                json_build_object(
                    'id_usuario', u.id_usuario,
                    'nombres', u.nombres,
                    'apellido_paterno', u.apellido_paterno,
                    'apellido_materno', u.apellido_materno,
                    'correo', u.correo_electronico
                ) AS autor,
                COALESCE(
                    json_agg(DISTINCT jsonb_build_object(
                        'id_imagen', i.id_imagen,
                        'url', i.url,
                        'descripcion', i.descripcion
                    )) FILTER (WHERE i.id_imagen IS NOT NULL), '[]'
                ) AS imagenes,
                COALESCE(
                    json_agg(DISTINCT jsonb_build_object(
                        'id_comentario', c.id_comentario,
                        'contenido', c.contenido,
                        'usuario', json_build_object(
                            'id_usuario', cu.id_usuario,
                            'nombres', cu.nombres,
                            'apellido_paterno', cu.apellido_paterno,
                            'apellido_materno', cu.apellido_materno,
                            'correo', cu.correo_electronico
                        )
                    )) FILTER (WHERE c.id_comentario IS NOT NULL), '[]'
                ) AS comentarios
            FROM blogs.blog b
            INNER JOIN core.usuario u ON b.id_usuario = u.id_usuario
            LEFT JOIN blogs.blog_imagen i ON b.id_blog = i.id_blog
            LEFT JOIN blogs.blog_comentario c ON b.id_blog = c.id_blog
            LEFT JOIN core.usuario cu ON c.id_usuario = cu.id_usuario
            GROUP BY b.id_blog, u.id_usuario
            ORDER BY b.fecha_reg DESC
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            BlogDto blog = new BlogDto();
            blog.setIdBlog(rs.getLong("id_blog"));
            blog.setTitle(rs.getString("title"));
            blog.setTema(rs.getString("tema"));
            blog.setContenido(rs.getString("contenido"));
            blog.setPeriodicidad(rs.getString("periodicidad"));
            blog.setFechaReg(rs.getObject("fecha_reg", LocalDateTime.class));

            try {
                blog.setAutor(objectMapper.readValue(rs.getString("autor"), UsuarioDto.class));

                blog.setImagenes(objectMapper.readValue(
                        rs.getString("imagenes"),
                        new TypeReference<List<ImagenDto>>() {}
                ));

                // Parsear JSON de comentarios
                blog.setComentarios(objectMapper.readValue(
                        rs.getString("comentarios"),
                        new TypeReference<List<ComentarioDto>>() {}
                ));
            } catch (JsonProcessingException e) {
                // Manejo de error: podrías loguearlo o lanzar RuntimeException
                throw new RuntimeException("Error al parsear JSON del blog", e);
            }

            return blog;
        });
    }

    public BlogDto obtenerBlog(Long idBlog) {
        String sql = """
        SELECT
            b.id_blog,
            b.title,
            b.tema,
            b.contenido,
            b.periodicidad,
            b.fecha_reg,
            json_build_object(
                'id_usuario', u.id_usuario,
                'nombres', u.nombres,
                'apellido_paterno', u.apellido_paterno,
                'apellido_materno', u.apellido_materno,
                'correo', u.correo_electronico
            ) AS autor,
            COALESCE(
                json_agg(DISTINCT jsonb_build_object(
                    'id_imagen', i.id_imagen,
                    'url', i.url,
                    'descripcion', i.descripcion
                )) FILTER (WHERE i.id_imagen IS NOT NULL), '[]'
            ) AS imagenes,
            COALESCE(
                json_agg(DISTINCT jsonb_build_object(
                    'id_comentario', c.id_comentario,
                    'contenido', c.contenido,
                    'usuario', json_build_object(
                        'id_usuario', cu.id_usuario,
                        'nombres', cu.nombres,
                        'apellido_paterno', cu.apellido_paterno,
                        'apellido_materno', cu.apellido_materno,
                        'correo', cu.correo_electronico
                    )
                )) FILTER (WHERE c.id_comentario IS NOT NULL), '[]'
            ) AS comentarios
        FROM blogs.blog b
        INNER JOIN core.usuario u ON b.id_usuario = u.id_usuario
        LEFT JOIN blogs.blog_imagen i ON b.id_blog = i.id_blog
        LEFT JOIN blogs.blog_comentario c ON b.id_blog = c.id_blog
        LEFT JOIN core.usuario cu ON c.id_usuario = cu.id_usuario
        WHERE b.id_blog = ?
        GROUP BY b.id_blog, u.id_usuario
    """;

        return jdbcTemplate.queryForObject(sql, new Object[]{idBlog}, (rs, rowNum) -> {
            BlogDto blog = new BlogDto();
            blog.setIdBlog(rs.getLong("id_blog"));
            blog.setTitle(rs.getString("title"));
            blog.setTema(rs.getString("tema"));
            blog.setContenido(rs.getString("contenido"));
            blog.setPeriodicidad(rs.getString("periodicidad"));
            blog.setFechaReg(rs.getObject("fecha_reg", LocalDateTime.class));

            try {
                blog.setAutor(objectMapper.readValue(rs.getString("autor"), UsuarioDto.class));
                blog.setImagenes(objectMapper.readValue(
                        rs.getString("imagenes"),
                        new TypeReference<List<ImagenDto>>() {}
                ));
                blog.setComentarios(objectMapper.readValue(
                        rs.getString("comentarios"),
                        new TypeReference<List<ComentarioDto>>() {}
                ));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error al parsear JSON del blog", e);
            }

            return blog;
        });
    }

}
