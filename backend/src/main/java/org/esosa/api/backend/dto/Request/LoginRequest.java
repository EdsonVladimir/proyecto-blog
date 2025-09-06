package org.esosa.api.backend.dto.Request;

public class LoginRequest {
    private String correoElectronico;
    private String password;

    public String getCorreoElectronico() {
        return correoElectronico;
    }
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    public String getPassword() {
        return password;
    }
}
