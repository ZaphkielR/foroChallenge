package com.challengeForo.Foro.domain.topico;

import com.challengeForo.Foro.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        String autor,
        String curso
) {
    public DatosRespuestaTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
