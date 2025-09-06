package org.esosa.api.backend.service;

import org.esosa.api.backend.model.Comentario;
import org.esosa.api.backend.repository.ComentarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    public Comentario crearComentario(Long blogId, Long usuarioId, String contenido) {
        Comentario comentario = new Comentario(blogId, usuarioId, contenido);
        return comentarioRepository.save(comentario);
    }

}
