CREATE SCHEMA IF NOT EXISTS core;

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
