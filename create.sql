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

CREATE TABLE IF NOT EXISTS `motivoE` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `borrado` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
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
('Notificada'),
('Pagada');

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
(1,
"$2a$10$pru/P8Ryn1aaLDwW7d945up08gqyn0.jmnyKD/u31g66NYjxyYwYu",
"luis@gmail.com",
"luis",
"admin");

INSERT INTO `silvahnos`.`motivoe`
(`id`,
`borrado`,
`descripcion`,
`nombre`)
VALUES
(1,
0,
"Egresos Astara",
"Astara"),
(2,
0,
"Contadora",
"Contadora"),
(3,
0,
"Cromocar",
"Cromocar"),
(4,
0,
"Pago IVA",
"IVA"),
(5,
0,
"Outlet",
"Outlet"),
(6,
0,
"Pago de SII",
"SII"),
(7,
0,
"Pago de sueldos",
"Sueldos"),
(8,
0,
"Gastos taller",
"Taller"),
(9,
0,
"Pago de arriendo",
"Arriendo"),
(10,
0,
"Creditos",
"Créditos");




