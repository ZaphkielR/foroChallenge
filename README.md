"# foroChallenge" 
# Foro Challenge

Foro Challenge es una aplicación desarrollada en Java con Spring Boot que permite a los usuarios registrarse, autenticarse, y participar en foros creando y visualizando a tópicos.

## Características

- Autenticación de usuarios con JWT
- Creación, actualización, y eliminación de tópicos
- Respuestas a tópicos
- Paginación de tópicos
- Validaciones de datos
- Manejo de errores

## Configuración de la Base de Datos

El proyecto utiliza MySQL como base de datos. Asegúrate de tener un servidor MySQL en funcionamiento y configura las siguientes variables de entorno:

- `DB_HOST`: Host de la base de datos
- `DB_NAME`: Nombre de la base de datos
- `MYSQL_USER`: Usuario de la base de datos
- `MYSQL_PASS`: Contraseña del usuario de la base de datos
- `AES_KEY`: Clave secreta para la generación de JWT

## Configuración de la Aplicación

Configura las siguientes propiedades en el archivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${MYSQL_USER}
spring.datasource.password=${MYSQL_PASS}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.show-sql=true
server.error.include-stacktrace=never

api.security.secret=${AES_KEY:314159265}
```
Uso de la API
Autenticación
Login
POST /login

Request Body:
```
{
    "correoElectronico": "usuario@example.com",
    "contrasena": "password"
}
```

Response:
```
{
    "JWTtoken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjb3JyZW9AZ21haWwuY29tIiwiaXNzIjoiZm9ybyIsImlkIjoxLCJleHAiOjE3MjA2MzA3MzF9.uq89vN31EDavpz7f_XGKXiLVS8-kBiEl9av18e0Mra0"
}

```

Tópicos
Crear Tópico
POST /topicos

Request Body:
```
{
    "idUsuario": "1",
    "mensaje": "Este es un nuevo tópico",
    "curso": "Curso de Java",
    "titulo": "Nuevo Tópico"
}
```
Response:
```
{
    "id": "1",
    "titulo": "Error validacion HTTP 3",
    "mensaje": "Error en la requisicion 3",
    "autor": "Autor",
    "curso": "Curso de HTML"
}
```

Listar Tópicos
GET /topicos

Response:
```
{
    "totalElements": 1,
    "totalPages": 1,
    "size": 4,
    "content": [
        {
            "id": 1,
            "titulo": "Error validacion HTTP 3",
            "mensaje": "Error en la requisicion 3",
            "fechaCreacion": "2024-07-10T09:56:54",
            "autor": "Autor",
            "estado": "ABIERTO",
            "curso": "Curso de HTML"
        }
    ],
    "number": 0,
    "sort": {
        "empty": true,
        "unsorted": true,
        "sorted": false
    },
    "first": true,
    "last": true,
    "numberOfElements": 1,
    "pageable": {
        "pageNumber": 0,
        "pageSize": 4,
        "sort": {
            "empty": true,
            "unsorted": true,
            "sorted": false
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "empty": false
}

```

Actualizar Tópico
PUT /topicos/{id}

Request Body:

````
{
    "idUsuario": "1",
    "mensaje": "Este es un mensaje actualizado",
    "nombreCurso": "Curso de Java",
    "titulo": "Tópico Actualizado"
}
````
Response:

```
{
    "id": 1,
    "titulo": "Error validacion HTTP 3",
    "mensaje": "Error en la requisicion 3",
    "fechaCreacion": "2024-07-10T09:56:54",
    "autor": "Autor",
    "estado": "ABIERTO",
    "curso": "Curso de HTML"
}
```

Obtener Tópico por ID
GET localhost:8080/topicos/1

Response: 
```
{
    "id": 1,
    "titulo": "Error validacion HTTP 3",
    "mensaje": "Error en la requisicion 3",
    "fechaCreacion": "2024-07-10T09:56:54",
    "autor": "Rafael",
    "estado": "ABIERTO",
    "curso": "Curso de HTML"
}
```

Eliminar Tópico
DELETE /topicos/{id}
Response
```
{}
```

## Manejo de Errores
El manejo de errores se realiza a través del controlador de errores ubicado en com.challengeForo.Foro.infra.errores.TratadorDeErrores. Se manejan los siguientes tipos de errores:

- EntityNotFoundException
- ValidacionDeIntegridad
- StackOverflowError
- SQLIntegrityConstraintViolationException