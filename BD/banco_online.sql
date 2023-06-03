-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-06-2023 a las 10:58:18
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `banco_online`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `Nombre` varchar(32) NOT NULL,
  `Apellidos` varchar(64) NOT NULL,
  `DNI` varchar(9) NOT NULL,
  `Email` varchar(32) NOT NULL,
  `Teléfono` varchar(9) NOT NULL,
  `Password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`Nombre`, `Apellidos`, `DNI`, `Email`, `Teléfono`, `Password`) VALUES
('David', 'Segura Carmona', '49166814L', 'david@gmail.com', '345565422', '12345'),
('Ivan ', 'Segura Carmona', '49166815L', 'ivan06sep@gmail.com', '634653950', '1234'),
('Paula ', 'Gallardo', '50382116I', 'paulag@gmail.com', '648812590', '123456');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `Num_Cuenta` varchar(100) NOT NULL,
  `DNI_Cliente` varchar(9) NOT NULL,
  `Saldo` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cuenta`
--

INSERT INTO `cuenta` (`Num_Cuenta`, `DNI_Cliente`, `Saldo`) VALUES
('345345', '49166815L', '25');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transacciones`
--

CREATE TABLE `transacciones` (
  `ID_Transaccion` int(11) NOT NULL,
  `Num_Cuenta` varchar(100) NOT NULL,
  `Cantidad` decimal(10,0) NOT NULL,
  `Num_cuenta_destino` varchar(100) NOT NULL,
  `Fecha` date NOT NULL,
  `Descripcion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `transacciones`
--

INSERT INTO `transacciones` (`ID_Transaccion`, `Num_Cuenta`, `Cantidad`, `Num_cuenta_destino`, `Fecha`, `Descripcion`) VALUES
(3, '345345', '10', '345345', '2023-06-02', 'Prueba 2'),
(9, '345345', '10', '345345', '2023-06-03', 'Prueba'),
(10, '345345', '5', '345345', '2023-06-03', 'Prueba'),
(11, '345345', '20', '338589', '2023-06-03', 'Hola'),
(12, '345345', '12', '338589', '2023-06-03', 'Prueba'),
(13, '345345', '3', '338589', '2023-06-03', 'Prueba'),
(14, '345345', '5', '345345257284', '2023-06-03', 'Prueba'),
(15, '345345', '5', '257284', '2023-06-03', 'Prueba');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`DNI`),
  ADD KEY `DNI_2` (`DNI`),
  ADD KEY `DNI_3` (`DNI`);

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`Num_Cuenta`),
  ADD KEY `DNI_Cliente` (`DNI_Cliente`);

--
-- Indices de la tabla `transacciones`
--
ALTER TABLE `transacciones`
  ADD PRIMARY KEY (`ID_Transaccion`),
  ADD KEY `Num_Cuenta` (`Num_Cuenta`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `transacciones`
--
ALTER TABLE `transacciones`
  MODIFY `ID_Transaccion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `cuenta_ibfk_1` FOREIGN KEY (`DNI_Cliente`) REFERENCES `cliente` (`DNI`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `transacciones`
--
ALTER TABLE `transacciones`
  ADD CONSTRAINT `transacciones_ibfk_1` FOREIGN KEY (`Num_Cuenta`) REFERENCES `cuenta` (`Num_Cuenta`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
