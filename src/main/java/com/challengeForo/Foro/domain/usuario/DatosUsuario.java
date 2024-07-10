package com.challengeForo.Foro.domain.usuario;

import com.challengeForo.Foro.domain.perfil.DatosPerfil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosUsuario(
    @NotBlank
    String nombre,
    @NotBlank
    @Email
    String correoElectronico,
    @NotBlank
    String contrasena,
    @NotBlank
    @Valid
    DatosPerfil perfil
) {
}
