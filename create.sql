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
  `host` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `port` int NOT NULL,
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

CREATE TABLE IF NOT EXISTS `motivoe` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `borrado` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `motivoi`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `borrado` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `meses` (
  `mes` int,
  PRIMARY KEY (`mes`)
);

-- Insert data
INSERT INTO silvahnos.configuracion_email
(id,
host,
password,
port,
texto,
username)
VALUES
(1,
"smtp.gmail.com",
"jkyrbwigkhucmpfa",
587,
"Las facturas por vencer son:",
"silvahnos.notificacion@gmail.com");

INSERT INTO silvahnos.estado
(nombre)
VALUES('No pagada'),
('Notificada'),
('Pagada');

INSERT INTO `silvahnos`.`parametro`
(`valor`)
VALUES
("3");

INSERT INTO `silvahnos`.`correo` 
(`id`, `direccion`) 
VALUES 
('1', 'silvahnos.servicios@gmail.com');

INSERT INTO `silvahnos`.`usuario`
(`id`,
`contrasenna`,
`correo`,
`nombre`,
`usuario`)
VALUES
(1,
"$2a$10$eWwisV1InNyOuTNC6dLaXOt8p/0pmpKm3lk/toiPrso4G/sB/WJYa",
"silvahnos.notificacion@gmail.com",
"admin",
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
"Créditos"),
(11,
0,
"Repuestos",
"Repuestos");

INSERT INTO `silvahnos`.`motivoi`
(`id`,
`borrado`,
`descripcion`,
`nombre`)
VALUES
(1,
0,
"Astara",
"Astara");

INSERT INTO `silvahnos`.`meses`
(`mes`) 
VALUES
(1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12);



