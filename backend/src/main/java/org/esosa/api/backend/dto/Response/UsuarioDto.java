package org.esosa.api.backend.dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioDto {
    @JsonProperty("id_usuario")
    private Long idUsuario;

    @JsonProperty("nombres")
    private String nombres;

    @JsonProperty("apellido_paterno")
    private String apellidoPaterno;

    @JsonProperty("apellido_materno")
    private String apellidoMaterno;

    @JsonProperty("correo")
    private String correo;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}