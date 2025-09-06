package org.esosa.api.backend.dto.Response;

import org.esosa.api.backend.model.Usuario;

import java.time.LocalDate;

public class UsuarioResponse {
    private Long idUsuario;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String paisResidencia;
    private String correoElectronico;
    private Long idRole;

    public UsuarioResponse(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nombres = usuario.getNombres();
        this.apellidoPaterno = usuario.getApellidoPaterno();
        this.apellidoMaterno = usuario.getApellidoMaterno();
        this.fechaNacimiento = usuario.getFechaNacimiento();
        this.paisResidencia = usuario.getPaisResidencia();
        this.correoElectronico = usuario.getCorreoElectronico();
        this.idRole = usuario.getIdRole();
    }

    public Long getIdUsuario() { return idUsuario; }
    public String getNombres() { return nombres; }
    public String getApellidoPaterno() { return apellidoPaterno; }
    public String getApellidoMaterno() { return apellidoMaterno; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public String getPaisResidencia() { return paisResidencia; }
    public String getCorreoElectronico() { return correoElectronico; }
    public Long getIdRole() { return idRole; }
}
