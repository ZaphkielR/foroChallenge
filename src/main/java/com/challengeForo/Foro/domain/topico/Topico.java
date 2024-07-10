package com.challengeForo.Foro.domain.topico;

import com.challengeForo.Foro.domain.curso.Curso;
import com.challengeForo.Foro.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topico")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private Estado status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso")
    private Curso curso;

    public Topico(Usuario autor, Curso curso, String mensaje, String titulo) {
        this.curso = curso;
        this.autor = autor;
        this.mensaje = mensaje;
        this.titulo = titulo;
        this.fechaCreacion = LocalDateTime.now();
        this.status = Estado.ABIERTO;
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico, Usuario autor, Curso curso) {
        this.autor = autor;
        this.curso = curso;
        this.mensaje = datosActualizarTopico.mensaje();
        this.titulo = datosActualizarTopico.titulo();
    }
}
