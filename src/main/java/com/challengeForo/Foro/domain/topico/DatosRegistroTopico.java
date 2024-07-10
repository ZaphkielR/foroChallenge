package com.challengeForo.Foro.domain.topico;

import com.challengeForo.Foro.domain.curso.DatosCurso;
import com.challengeForo.Foro.domain.usuario.DatosUsuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosRegistroTopico(
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
