-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 05, 2021 at 12:21 AM
-- Server version: 5.7.31
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `reservationdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `agence`
--

DROP TABLE IF EXISTS `agence`;
CREATE TABLE IF NOT EXISTS `agence` (
  `agence_Id` int(11) NOT NULL AUTO_INCREMENT,
  `agence_name` varchar(50) NOT NULL,
  `localisation` varchar(100) NOT NULL,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL,
  PRIMARY KEY (`agence_Id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `agence`
--

INSERT INTO `agence` (`agence_Id`, `agence_name`, `localisation`, `latitude`, `longitude`) VALUES
(1, 'CIH Kortoba', 'LOT Kortoba DEMNAT Maroc', 31.7923, -7.08017);

-- --------------------------------------------------------

--
-- Table structure for table `bureau`
--

DROP TABLE IF EXISTS `bureau`;
CREATE TABLE IF NOT EXISTS `bureau` (
  `bureau_Id` int(11) NOT NULL AUTO_INCREMENT,
  `service` varchar(50) NOT NULL,
  `isavailable` tinyint(1) NOT NULL,
  `agence_Id` int(11) NOT NULL,
  `bureau_availability` int(11) NOT NULL,
  PRIMARY KEY (`bureau_Id`),
  KEY `agence_Id` (`agence_Id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bureau`
--

INSERT INTO `bureau` (`bureau_Id`, `service`, `isavailable`, `agence_Id`, `bureau_availability`) VALUES
(1, 'service chéque et virement', 1, 1, 86),
(2, 'service creation de compte', 1, 1, 88),
(3, 'service directeur', 1, 1, 40);

-- --------------------------------------------------------

--
-- Table structure for table `employe`
--

DROP TABLE IF EXISTS `employe`;
CREATE TABLE IF NOT EXISTS `employe` (
  `employe_id` int(11) NOT NULL AUTO_INCREMENT,
  `bureau_id` int(11) NOT NULL,
  `password` text NOT NULL,
  PRIMARY KEY (`employe_id`),
  UNIQUE KEY `bureau_id` (`bureau_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `reservation_Id` int(11) NOT NULL AUTO_INCREMENT,
  `cin_client` varchar(50) NOT NULL,
  `nom_client` varchar(50) NOT NULL,
  `prenom_client` varchar(50) NOT NULL,
  `email_client` varchar(50) NOT NULL,
  `horaire` datetime NOT NULL,
  `duree` int(11) NOT NULL,
  `bureau_id` int(11) NOT NULL,
  PRIMARY KEY (`reservation_Id`),
  KEY `bureau_id` (`bureau_id`)
) ENGINE=MyISAM AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
