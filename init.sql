CREATE SCHEMA IF NOT EXISTS core;
CREATE SCHEMA IF NOT EXISTS blogs;

CREATE TABLE IF NOT EXISTS core.role (
    id_role SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT
);

INSERT INTO core.role (name, description) VALUES 
    ('ADMIN', 'Usuario con todos los privilegios'),
    ('AUTHOR', 'Usuario que puede crear y modificar contenido'),
    ('COMMENTER', 'Usuario que solo puede comentar')
ON CONFLICT (name) DO NOTHING;

CREATE TABLE IF NOT EXISTS core.usuario (
    id_usuario SERIAL PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellido_paterno VARCHAR(100) NOT NULL,
    apellido_materno VARCHAR(100),
    fecha_nacimiento DATE NOT NULL,
    pais_residencia VARCHAR(100) NOT NULL,
    correo_electronico VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    id_role INT NOT NULL,
    CONSTRAINT fk_usuario_role FOREIGN KEY (id_role) REFERENCES core.role (id_role)
);

CREATE TABLE IF NOT EXISTS blogs.blog(
	id_blog SERIAL PRIMARY KEY,
	title VARCHAR,
	tema VARCHAR,
	contenido TEXT,
	periodicidad VARCHAR,
	id_usuario INT NOT NULL,
	fecha_reg TIMESTAMP DEFAULT now(),
	CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES core.usuario(id_usuario)
);

CREATE TABLE IF NOT EXISTS blogs.blog_imagen (
    id_imagen SERIAL PRIMARY KEY,
    id_blog INT NOT NULL,
    url VARCHAR(500) NOT NULL,
    descripcion VARCHAR(255),
    fecha_reg TIMESTAMP DEFAULT now(),
    CONSTRAINT fk_blog_imagen FOREIGN KEY (id_blog) REFERENCES blogs.blog(id_blog) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS blogs.blog_comentario (
    id_comentario SERIAL PRIMARY KEY,
    id_blog INT NOT NULL,
    id_usuario INT NOT NULL,
    contenido TEXT NOT NULL,
    fecha_reg TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_blog_comentario FOREIGN KEY (id_blog) REFERENCES blogs.blog(id_blog) ON DELETE CASCADE,
    CONSTRAINT fk_usuario_comentario FOREIGN KEY (id_usuario) REFERENCES core.usuario(id_usuario) ON DELETE CASCADE
);

