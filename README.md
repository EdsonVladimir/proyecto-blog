# Edson Vladimir Sosa Sacari

Proyecto Blog
Descripción del Proyecto

Este proyecto implementa un blog completo con:

Backend (Spring Boot): API REST para la gestión de blogs y usuarios.

Frontend (Angular): Interfaz web que consume las APIs del backend.

Base de datos (PostgreSQL): Almacena información persistente de usuarios y blogs.

Todo el proyecto se ejecuta mediante Docker y Docker Compose, asegurando portabilidad, consistencia y facilidad de despliegue.

Tecnologías Utilizadas
Tecnología	Propósito	Justificación
Spring Boot	Backend	APIs REST robustas, integración nativa con PostgreSQL, escalable.
Angular	Frontend	SPA moderna, experiencia de usuario dinámica.
PostgreSQL	Base de datos	Transacciones, confiabilidad, datos estructurados.
Docker	Contenerización	Aislamiento de servicios y dependencias.
Docker Compose	Orquestación	Levantar todo el stack con un solo comando.
Nginx	Servir frontend	Servidor web ligero y eficiente para Angular en producción.
Swagger	Documentación API	Permite explorar y probar los endpoints REST de manera visual e interactiva.
Arquitectura Visual del Proyecto
+-----------------------------+
|        NAVEGADOR            |
| (Usuario/Cliente Web)       |
+------------+----------------+
             |
             v
+-----------------------------+
|       FRONTEND              |
|  Angular + Nginx (80)       |
+------------+----------------+
             |
             v
+-----------------------------+
|        BACKEND              |
|      Spring Boot (8080)     |
|    Swagger UI: /swagger-ui  |
+------------+----------------+
             |
             v
+-----------------------------+
|       POSTGRESQL            |
|     Base de datos (5432)    |
+-----------------------------+

# Flujo de Datos
Usuario crea blog -> Frontend envía POST -> Backend recibe datos -> Backend guarda en PostgreSQL
Usuario solicita blogs -> Frontend hace GET -> Backend consulta PostgreSQL -> Backend devuelve datos -> Frontend muestra

# Manual para Levantar el Proyecto
Requisitos Previos

Docker y Docker Compose instalados.

Clonar repositorio:

git clone https://github.com/EdsonVladimir/proyecto-blog.git esta implementado en la rama develop
cd proyecto-blog

Paso 1: Revisar variables de entorno

En docker-compose.prod.yml:

POSTGRES_USER: usuario
POSTGRES_PASSWORD: secreto
POSTGRES_DB: midatabase


Modifica si es necesario.

Paso 2: Levantar contenedores
docker-compose -f docker-compose.prod.yml up --build


Servicios levantados: postgres → backend → frontend.

--build asegura que se construyan las imágenes más recientes.

Paso 3: Acceso

Frontend (Angular): http://localhost:4200

Backend API (Spring Boot): http://localhost:8080

Documentación Swagger (REST API): http://localhost:8080/swagger-ui/index.html#/

PostgreSQL: puerto 5432, cliente PostgreSQL compatible.

Nota: Swagger permite explorar todos los endpoints REST de manera visual, probar solicitudes GET, POST, PUT y DELETE, y ver la estructura de los datos. Es una herramienta muy útil para revisores y desarrolladores.

Paso 4: Detener servicios
docker-compose -f docker-compose.prod.yml down


Eliminar volúmenes persistentes:

docker-compose -f docker-compose.prod.yml down -v

Ejemplos de Uso del Backend
Método	URL	Descripción
GET	/api/blogs/todos	Listar todos los blogs
GET	/api/blogs/{id}	Obtener blog por ID
POST	/api/blogs/crear	Crear nuevo blog
PUT	/api/blogs/{id}	Actualizar blog existente
POST    /api/usuarios/crear Crear autor se necesita el id_usuario para crear blogs
POST 	/api/auth/login Se inicia sesion para obtener los datos del author y crear blogs
POST 	/api/comentarios/crear Se usa la api para crear comentarios se necesita el idBlog y el id del usuario que crea el comentario
curl ejemplos
# Obtener blogs
curl -X GET http://localhost:8080/api/blogs/todos

# Crear un blog
curl -X POST http://localhost:8080/api/blogs/crear

Ejemplos de Uso del Frontend

Accede a http://localhost:4200

Funcionalidades:

Visualizar blogs

Crear, actualizar y eliminar blogs

Navegar entre secciones dinámicamente

Beneficios de la Implementación

Separación de responsabilidades: Cada servicio aislado en su contenedor.

Persistencia: PostgreSQL usa volúmenes.

Portabilidad: Docker asegura funcionamiento consistente.

Escalabilidad: Servicios independientes permiten escalar individualmente.

Desarrollo moderno: Angular + Spring Boot, modular y rápido.

Despliegue sencillo: Docker Compose levanta todo con un comando.

