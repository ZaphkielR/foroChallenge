create table perfil(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    primary key(id)
);

create table curso(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    categoria varchar(100) not null,
    primary key(id)
);

create table usuario(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    correo_electronico varchar(100) not null,
    contrasena varchar(100) not null,
    perfiles bigint not null,

    primary key(id),

    constraint fk_usuario_perfil_id foreign key(perfiles) references perfil(id)
);

create table topico(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(100) not null,
    fecha_creacion datetime not null,
    status varchar(100) not null,
    autor bigint not null,
    curso bigint not null,

    primary key(id),

    constraint fk_topico_curso_id foreign key(curso) references curso(id),
    constraint fk_topico_usuario_id foreign key(autor) references usuario(id)
);

create table respuesta(
    id bigint not null auto_increment,
    mensaje varchar(100) not null,
    solucion varchar(100) not null,
    fecha_ceacion datetime not null,
    autor bigint not null,
    topico bigint not null,

    primary key(id),

    constraint fk_respuesta_topico_id foreign key(topico) references topico(id),
    constraint fk_respuesta_usuario_id foreign key(autor) references usuario(id)
);









