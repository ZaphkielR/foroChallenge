package com.challengeForo.Foro.controller;

import com.challengeForo.Foro.domain.curso.Curso;
import com.challengeForo.Foro.domain.curso.CursoRepository;
import com.challengeForo.Foro.domain.topico.*;
import com.challengeForo.Foro.domain.topico.validaciones.TopicoDuplicado;
import com.challengeForo.Foro.domain.usuario.Usuario;
import com.challengeForo.Foro.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoDuplicado topicoDuplicado;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(
            @RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
            UriComponentsBuilder uriComponentsBuilder
    ){
        topicoDuplicado.validar(datosRegistroTopico);

        Usuario autor = usuarioRepository.getReferenceById(datosRegistroTopico.usuario());
        Curso curso = cursoRepository.findByNombre(datosRegistroTopico.curso());
        Topico topico = topicoRepository.save(
                new Topico(
                        autor,
                        curso,
                        datosRegistroTopico.mensaje(),
                        datosRegistroTopico.titulo()
                ));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> litadoTopicos(@PageableDefault(size = 4) Pageable paginacion) {
        return ResponseEntity.ok(
                topicoRepository.findAll(paginacion).map(DatosListadoTopico::new)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> retornarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        DatosListadoTopico datosTopico = new DatosListadoTopico(topico);
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico, @PathVariable Long id){
        Optional<Topico> topico = topicoRepository.findAllById(id);
        if (!topico.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Usuario autor = usuarioRepository.getReferenceById(datosActualizarTopico.usuario());
        Curso curso = cursoRepository.findByNombre(datosActualizarTopico.curso());

        topico.get().actualizarDatos(datosActualizarTopico, autor, curso);
        return ResponseEntity.ok(
                new DatosRespuestaTopico(topico.get())
        );
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findAllById(id);
        if (!topico.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
