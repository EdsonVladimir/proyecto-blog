package org.esosa.api.backend.dto.Response;


import org.esosa.api.backend.model.Usuario;
import org.esosa.api.backend.dto.Response.UsuarioResponse;

public class LoginResponse {
    private String token;
    private UsuarioResponse usuario;

    public LoginResponse(String token, Usuario usuario) {
        this.token = token;
        this.usuario = new UsuarioResponse(usuario);
    }

    public String getToken() {
        return token;
    }

    public UsuarioResponse getUsuario() {
        return usuario;
    }
}
