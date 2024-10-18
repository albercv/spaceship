# Spaceship API

Este proyecto es una API para gestionar spaceships, creada con **Spring Boot**. La API permite crear, leer, actualizar y eliminar (CRUD) spaceships. El proyecto también incluye integración con **Redis** para caché y **RabbitMQ** para la mensajería asíncrona.

## Tecnologías Utilizadas

- **Java 21**: Última versión estable de Java.
- **Spring Boot 3.3.4**: Framework principal para la aplicación.
- **Spring Data JPA**: Para la persistencia de datos usando **JPA/Hibernate**.
- **Redis**: Sistema de caché usado para almacenar las consultas.
- **RabbitMQ**: Sistema de mensajería para la creación y actualización de spaceships.
- **Thymeleaf**: Motor de plantillas para las vistas HTML.
- **Flyway**: Herramienta para la gestión y migración de la base de datos.
- **H2**: Base de datos en memoria usada para pruebas y desarrollo.
- **Docker & Docker Compose**: Para contenerizar la aplicación junto con Redis y RabbitMQ.
- **JUnit 5 y Mockito**: Para pruebas unitarias.
- **Postman**: Herramienta para pruebas de API. Se incluye una colección con las pruebas.

## Requisitos

Para ejecutar la aplicación, asegúrate de tener instalados los siguientes componentes:

- **Docker** y **Docker Compose**: Para correr la aplicación y sus servicios asociados.
- **Java 21**: Para ejecutar la aplicación localmente fuera de Docker.

## Instalación y Configuración

1. **Clona el repositorio**:

   ```bash
   git clone git@github.com:albercv/spaceship.git
   cd Spaceship
Construye la aplicación usando Docker:

Ejecuta los siguientes comandos para construir y levantar la aplicación junto con Redis y RabbitMQ:

bash
Always show details

Copy code
docker-compose up --build
Esto levantará los servicios en los siguientes puertos:

API: http://localhost:8080
Redis: localhost:6379
RabbitMQ: localhost:5672 (Consola de administración: http://localhost:15672, usuario: buzz, contraseña: lightyear)
### Configura Redis en application.properties:


```
spring.data.redis.host=localhost
spring.data.redis.port=6379
``` 

### Configura Rabbit en application.properties:
```
spring.rabbitmq.host=rabbitmq
spring.rabbitmq.port=5672
spring.rabbitmq.username=buzz
spring.rabbitmq.password=lightyear
```

### Se utiliza H2 como base de datos en memoria para facilitar el desarrollo y pruebas locales. Puedes acceder a la consola de H2 en http://localhost:8080/h2-console con las siguientes credenciales:

```
spring.datasource.url=jdbc:h2:mem:spaceshipdb
spring.datasource.username=captain
spring.datasource.password=totheinfiniteandbeyond
spring.h2.console.enabled=true
```

## Endpoints de la API
La API proporciona varios endpoints para gestionar las spaceships:
```
GET /api/spaceships: Lista todas las spaceships con soporte de paginación.
GET /api/spaceships/{id}: Recupera una nave espacial por su ID.
POST /api/spaceships: Crea una nueva nave espacial.
PUT /api/spaceships/{id}: Actualiza una nave espacial existente.
DELETE /api/spaceships/{id}: Elimina una nave espacial por su ID.
```

Ejemplos de cURL
Crear una nave espacial:
```
curl -X POST http://localhost:8080/api/spaceships \
-H "Content-Type: application/json" \
-d '{
"name": "Millennium Falcon",
"model": "YT-1300",
"manufacturer": "Corellian Engineering",
"crewCapacity": 4,
"passengerCapacity": 6
}'
```

Eliminar una nave espacial:

```
curl -X DELETE http://localhost:8080/api/spaceships/1
```

Colección de Postman
Se incluye una colección de Postman para probar los diferentes endpoints de la API. Puedes importar el archivo `Spaceship.postman_collection.json` en Postman para realizar pruebas rápidamente.

Ejemplos de pruebas en Postman:
getWithName: Recupera spaceships filtradas por nombre.
create: Crea una nueva nave espacial con el cuerpo JSON proporcionado.
delete: Elimina una nave espacial por ID.
Para usar la colección de Postman, sigue estos pasos:

Importar la colección: En Postman, selecciona Import y carga el archivo Spaceship.postman_collection.json.
Ejecutar pruebas: Prueba cada endpoint directamente desde Postman.
Pruebas
El proyecto incluye pruebas unitarias y de integración usando JUnit 5 y Mockito. Para ejecutar las pruebas, usa el siguiente comando:

```
mvn test
```

Dependencias clave

- Spring Boot 3.3.4: Framework principal para construir la API.
- Spring Data JPA: Persistencia de datos con JPA.
- Redis: Para caché.
- RabbitMQ: Para la mensajería.
- Flyway: Herramienta de migración de base de datos.
- Thymeleaf: Motor de plantillas para renderizar vistas.
- JUnit 5 y Mockito: Para pruebas.

Gestión de caché y mensajería

- Redis: Se utiliza para almacenar en caché las respuestas de las consultas más frecuentes.
- RabbitMQ: La aplicación usa colas de mensajes para manejar la creación y actualización de spaceships de manera asíncrona.

## Vista para Enviar Mensajes a RabbitMQ

La aplicación incluye una vista HTML simple, renderizada con Thymeleaf, desde la cual los usuarios pueden enviar mensajes a una cola de RabbitMQ. Estos mensajes contienen los datos de una nave espacial, que serán procesados por el sistema para crear o actualizar objetos Spaceship en la base de datos.

Detalles de la vista:

La vista se encuentra disponible en: http://localhost:8080/api/rabbit/form.
Desde esta página, los usuarios pueden llenar un formulario con los detalles de la nave espacial (nombre, modelo, capacidad de tripulación, etc.) y enviar el formulario.
Al enviar el formulario, los datos son enviados a una cola de RabbitMQ, que luego será procesada para crear o actualizar la nave espacial correspondiente en la base de datos.
Ejemplo de funcionamiento:
El usuario accede a la página http://localhost:8080/api/rabbit/form.
Llena el formulario con los detalles de la nave espacial.
Al enviar el formulario, los datos son enviados a la cola de RabbitMQ.
El consumidor de RabbitMQ lee el mensaje y lo procesa para crear o actualizar el registro de la nave espacial en la base de datos.
Este enfoque permite que las creaciones o actualizaciones de naves espaciales se manejen de manera asíncrona y eficiente a través de RabbitMQ, lo que es útil para sistemas distribuidos o que manejan una alta carga de tráfico.

# Posibles Puntos de Mejora
1. #### Incluir MapStruct para la conversión de entidades a DTOs: 
    Usar MapStruct permitiría convertir automáticamente entre las entidades y los DTOs de entrada y salida. Esto ayudaría a evitar exponer las entidades directamente y mejoraría la separación de responsabilidades en el sistema.

2. #### Incluir DTOs de Entrada y Salida: 
   Crear DTOs de entrada para las peticiones y de salida para las respuestas de la API. Esto mejoraría la seguridad al no exponer las entidades directamente al cliente y facilitaría la validación de datos.

3. #### Validaciones de los objetos Spaceship: 
    Implementar validaciones para los objetos Spaceship usando las herramientas de validación de Spring o Jakarta Bean Validation. Esto aseguraría que los datos recibidos sean correctos antes de ser procesados o almacenados.

4. #### Incluir JWT para la securización de la API: 
   Utilizar JWT (JSON Web Tokens) junto con Spring Security para proteger los endpoints de la API. Esto permitiría gestionar la autenticación y la autorización de forma más segura y controlada.

5. #### Optimización del uso de la caché: 
    Optimizar el uso de Redis con políticas de expiración de caché para evitar datos obsoletos y cachear solo las consultas más costosas. De esta manera, se mejoraría el rendimiento del sistema.