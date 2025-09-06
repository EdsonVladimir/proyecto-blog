package org.esosa.api.backend.repository;

import org.esosa.api.backend.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario findByCorreoElectronico(String correoElectronico);
}
