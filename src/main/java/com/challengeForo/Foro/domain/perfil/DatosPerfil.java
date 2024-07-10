package com.challengeForo.Foro.domain.perfil;

import jakarta.validation.constraints.NotBlank;

public record DatosPerfil(
        @NotBlank
        String nombre
) {
}
