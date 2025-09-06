package org.esosa.api.backend.repository;

import org.esosa.api.backend.model.Comentario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends CrudRepository<Comentario, Long> {
}
