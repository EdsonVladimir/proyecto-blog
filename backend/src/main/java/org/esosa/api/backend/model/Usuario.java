package org.esosa.api.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("usuario")
public class Usuario {

    @Id
    private Long idUsuario;

    @Column("nombres")
    private String nombres;

    @Column("apellido_paterno")
    private String apellidoPaterno;

    @Column("apellido_materno")
    private String apellidoMaterno;

    @Column("fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column("pais_residencia")
    private String paisResidencia;

    @Column("correo_electronico")
    private String correoElectronico;

    @Column("password")
    @JsonIgnore
    private String password;

    @Column("id_role")
    private Long idRole;

    public Usuario( String nombres, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String paisResidencia, String correoElectronico, String password, Long idRole) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.paisResidencia = paisResidencia;
        this.correoElectronico = correoElectronico;
        this.password = password;
        this.idRole = idRole;
    }
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPaisResidencia() {
        return paisResidencia;
    }

    public void setPaisResidencia(String paisResidencia) {
        this.paisResidencia = paisResidencia;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public Usuario orElseThrow(Object usuarioNoEncontrado) {
        return null;
    }
}
