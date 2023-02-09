-- INSERT INTO `users` (`id`, `nombre`, `casa`, `puntos`) VALUES (1, 'amado', 'Griffindor', '23');

-- Datos Manuales de la BBDD
DROP DATABASE IF EXISTS `casasHowarts`;
CREATE DATABASE `casasHowarts`;
DROP USER IF EXISTS `userMago` @`localhost`;
CREATE USER `userMago` @`localhost` IDENTIFIED BY 'user1234';
GRANT ALL PRIVILEGES ON *.* TO `userMago` @`localhost`;
USE `casasHowarts`;
CREATE TABLE `users` (
    ID int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    NOMBRE VARCHAR (100) NOT NULL,
    CASA VARCHAR (100) NOT NULL,
    PUNTOS int NOT NULL
);
INSERT INTO `users` (`id`, `nombre`, `casa`, `puntos`) VALUES
(1, 'amado', 'Griffindor', '23');

