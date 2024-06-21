
-- Eliminar la tabla odontologos si ya existe
DROP TABLE IF EXISTS odontologos;
-- Crear tabla odontologos
CREATE TABLE IF NOT EXISTS odontologos (
    numeroMatricula INT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL
);
-- Insertar datos de ejemplo en la tabla odontologos
INSERT INTO odontologos (numeroMatricula, nombre, apellido) VALUES (1, 'Juan', 'Perez');
INSERT INTO odontologos (numeroMatricula, nombre, apellido) VALUES (2, 'Ana', 'Gomez');
INSERT INTO odontologos (numeroMatricula, nombre, apellido) VALUES (3, 'Carlos', 'Lopez');