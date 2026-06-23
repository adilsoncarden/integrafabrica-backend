-- Alineación de esquema para entidad User (ejecutar una vez si la tabla ya existía)
-- Corrige columnas temporales y longitud de roles.name

ALTER TABLE roles
    ALTER COLUMN name TYPE VARCHAR(100);

ALTER TABLE users
    ADD COLUMN IF NOT EXISTS created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW();

ALTER TABLE users
    ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW();

ALTER TABLE users
    ALTER COLUMN created_at TYPE TIMESTAMP WITHOUT TIME ZONE
    USING created_at AT TIME ZONE 'UTC';

ALTER TABLE users
    ALTER COLUMN updated_at TYPE TIMESTAMP WITHOUT TIME ZONE
    USING updated_at AT TIME ZONE 'UTC';
