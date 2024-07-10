package com.challengeForo.Foro.domain.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(
        @NotBlank
        @JsonProperty("email")
        String correoElectronico,
        @NotBlank
        @JsonProperty("contrasena")
        String contrasena
) {
}
