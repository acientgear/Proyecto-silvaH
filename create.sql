-- Create database
CREATE DATABASE IF NOT EXISTS `silvahnos`;

-- Use silvahnos
USE `silvahnos`;

-- Create tables 
CREATE TABLE IF NOT EXISTS `parametro` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `valor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE IF NOT EXISTS `estado` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `configuracion_email` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `auth` bit(1) NOT NULL,
  `host` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `port` int NOT NULL,
  `starttls` bit(1) NOT NULL,
  `texto` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `correo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contrasenna` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Insert data
INSERT INTO silvahnos.configuracion_email
(id,
auth,
host,
password,
port,
starttls,
texto,
username)
VALUES
(1,
1,
"smtp.gmail.com",
"dchpqzbthkuyszdv",
587,
1,
"Los facturas por vencer son:",
"javiertoroflores@gmail.com");

INSERT INTO silvahnos.estado
(nombre)
VALUES('No pagada'),
('Pagada'),
('Notificada');

INSERT INTO `silvahnos`.`parametro`
(`valor`)
VALUES
("5");

INSERT INTO `silvahnos`.`correo` 
(`id`, `direccion`) 
VALUES 
('1', 'javiertoroflores@gmail.com');

INSERT INTO `silvahnos`.`usuario`
(`id`,
`contrasenna`,
`correo`,
`nombre`,
`usuario`)
VALUES
(2,
"$2a$10$pru/P8Ryn1aaLDwW7d945up08gqyn0.jmnyKD/u31g66NYjxyYwYu",
"luis@gmail.com",
"luis",
"admin");




