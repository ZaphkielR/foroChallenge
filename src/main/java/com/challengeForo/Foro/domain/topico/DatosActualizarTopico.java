package com.challengeForo.Foro.domain.topico;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizarTopico(
        @NotNull
        @JsonProperty("idUsuario")
        Long usuario,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        @JsonProperty("nombreCurso")
        String curso
) {
}
