-- Crear esquema
CREATE SCHEMA `test_jdbc`;

-- Vamos a crear una BD con dos entidades
-- people: Datos de una persona
-- document: Documento de identidad de una persona
-- address: Dirección de persona

-- se crea una realción 1 a 1 people-documents
-- lo que indica que una persona tiene un único documento de identidad

-- se crea una realción 1 a n people-address
-- lo que indica que una persona puede tener más de una dirección

-- crear tabla
-- tabla personas
CREATE TABLE   `test_jdbc`.`people` (
    `id` BIGINT NOT NULL  PRIMARY KEY AUTO_INCREMENT COMMENT 'id de persona',
    `name` VARCHAR(100) NOT NULL COMMENT 'nombre de la persona',
    `last_name` VARCHAR(100) NOT NULL COMMENT 'apellidos de la persona',
    `email` VARCHAR(200) NOT NULL COMMENT 'correo electrónico de la persona',
    `birthday` DATE NOT NULL COMMENT 'fecha de nacimiento de la persona'
);

-- tabla documento
CREATE TABLE   `test_jdbc`.`documents` (
    `id` BIGINT NOT NULL  PRIMARY KEY AUTO_INCREMENT COMMENT 'id del documento',
    `person_id` BIGINT NOT NULL COMMENT 'id de la persona a la que pertenece el documento',
    `type` ENUM('TI', 'CC', 'CE', 'P') COMMENT 'tipo documento TI=tarjeta identidad, CC=cedula ciudadanía, CE = cédula extranjería, P = pasaporte',
    `number` VARCHAR(20) NOT NULL COMMENT  'número/ código del documento',
    `issue_date` DATE DEFAULT NULL COMMENT 'fecha de expedición  puede ser nulo',
    `expiration_date` DATE DEFAULT NULL COMMENT 'fecha de vencimiento  puede ser nulo',
    `issue_place` VARCHAR(50) DEFAULT NULL COMMENT 'lugar de expedición puede ser nulo',
    `extra_info` VARCHAR(1024) DEFAULT NULL COMMENT 'información extra puede ser nulo'
);

-- tabla direcciones
CREATE TABLE   `test_jdbc`.`addresses` (
    `id` BIGINT NOT NULL  PRIMARY KEY AUTO_INCREMENT COMMENT 'id de la dirección',
    `person_id` BIGINT NOT NULL COMMENT 'id de la persona a la que pertenece la dirección',
    `address1` VARCHAR(100) NOT NULL COMMENT 'línea 1 dirección no nula',
    `address2` VARCHAR(100) DEFAULT '' COMMENT 'línea 2 dirección puede ser nula',
    `address3` VARCHAR(100) DEFAULT ''  COMMENT 'línea 3 dirección  puede ser nula',
    `city` VARCHAR(100) NOT NULL COMMENT 'ciudad o pueblo',
    `province` VARCHAR(100) NOT NULL COMMENT 'departamento o estado o provincia',
    `country` VARCHAR(100) NOT NULL COMMENT 'pais'
);

-- clave foránea para relación 1-1 people-documents
-- id_persona UNICO hace
ALTER TABLE `test_jdbc`.`documents`
ADD CONSTRAINT  `documents_person_uq`
UNIQUE (`person_id`) COMMENT 'documento unico persona';
-- Clave foránea persona
ALTER TABLE `test_jdbc`.`documents`
ADD FOREIGN KEY(`person_id`)  REFERENCES `test_jdbc`.`people` (`id`);

-- clave foránea relación 1-N people-addresses
ALTER TABLE `test_jdbc`.`addresses`
ADD FOREIGN KEY(`person_id`)  REFERENCES `test_jdbc`.`people` (`id`);



