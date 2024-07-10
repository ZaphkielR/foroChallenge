package com.challengeForo.Foro.domain.topico.validaciones;

import com.challengeForo.Foro.domain.topico.DatosRegistroTopico;
import com.challengeForo.Foro.domain.topico.TopicoRepository;
import com.challengeForo.Foro.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoDuplicado {
    @Autowired
    TopicoRepository topicoRepository;

    public void validar(DatosRegistroTopico datos) {
        if (topicoRepository.existsByTituloOrMensaje(datos.titulo(), datos.mensaje())){
            throw new ValidacionDeIntegridad("El título o mensaje ya existen");
        }
    }
}
