package com.challengeForo.Foro.domain.usuario;

import com.challengeForo.Foro.domain.perfil.Perfil;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "usuario")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String contrasena;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfiles")
    private Perfil perfil;
}
