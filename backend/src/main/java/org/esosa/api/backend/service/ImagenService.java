package org.esosa.api.backend.service;

import org.esosa.api.backend.model.Imagen;
import org.esosa.api.backend.repository.ImagenRepository;
import org.springframework.stereotype.Service;

@Service
public class ImagenService {
    private final ImagenRepository imagenRepository;

    public ImagenService(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    public Imagen createImagen(Long idBlog, String url, String descripcion) {
        Imagen imagen = new Imagen(idBlog, url, descripcion);
        return imagenRepository.save(imagen);
    }
}
