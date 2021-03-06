-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 06, 2021 at 11:39 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

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

CREATE TABLE `agence` (
  `agence_Id` int(11) NOT NULL,
  `agence_name` varchar(50) NOT NULL,
  `localisation` varchar(100) NOT NULL,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `agence`
--

INSERT INTO `agence` (`agence_Id`, `agence_name`, `localisation`, `latitude`, `longitude`) VALUES
(1, 'CIH Kortoba', 'LOT Kortoba MEKNES Maroc', 33.8603, -5.54765),
(10, 'CIH ELMANSSOUR', 'MENSOUR 123 MEKNES Maroc', 33.8665, -5.57764),
(11, 'CIH HEMRIA', 'HEMRIA 123 MEKNES Maroc', 33.9067, -5.54058),
(12, 'CIH WISSLAN', 'WISLLAN123 MEKNES Maroc', 33.912, -5.48507);

-- --------------------------------------------------------

--
-- Table structure for table `bureau`
--

CREATE TABLE `bureau` (
  `bureau_Id` int(11) NOT NULL,
  `service` varchar(50) NOT NULL,
  `isavailable` tinyint(1) NOT NULL,
  `agence_Id` int(11) NOT NULL,
  `bureau_availability` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bureau`
--

INSERT INTO `bureau` (`bureau_Id`, `service`, `isavailable`, `agence_Id`, `bureau_availability`) VALUES
(1, 'service chéque et virement', 1, 1, 81),
(2, 'service creation de compte', 1, 1, 100),
(3, 'service directeur', 1, 1, 100),
(4, 'service chéque et virement', 1, 10, 100),
(7, 'service creation de compte', 1, 10, 100),
(10, 'service directeur', 1, 10, 100),
(5, 'service chéque et virement', 1, 11, 100),
(8, 'service creation de compte', 1, 11, 100),
(11, 'service directeur', 1, 11, 100),
(6, 'service chéque et virement', 1, 12, 100),
(9, 'service creation de compte', 1, 12, 100),
(12, 'service directeur', 1, 12, 100);

-- --------------------------------------------------------

--
-- Table structure for table `employe`
--

CREATE TABLE `employe` (
  `employe_id` int(11) NOT NULL,
  `bureau_id` int(11) NOT NULL,
  `password` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employe`
--

INSERT INTO `employe` (`employe_id`, `bureau_id`, `password`) VALUES
(6, 1, 'xeno');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `reservation_Id` int(11) NOT NULL,
  `cin_client` varchar(50) NOT NULL,
  `nom_client` varchar(50) NOT NULL,
  `prenom_client` varchar(50) NOT NULL,
  `email_client` varchar(50) NOT NULL,
  `horaire` datetime NOT NULL,
  `duree` int(11) NOT NULL,
  `bureau_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`reservation_Id`, `cin_client`, `nom_client`, `prenom_client`, `email_client`, `horaire`, `duree`, `bureau_id`) VALUES
(34, 'UA12345', 'DERROUICH', 'JAOUHAR', 'JAOUHARDER@GMAIL.COM', '2021-03-08 15:00:00', 0, 1),
(36, 'XXXXX', 'BENTTALEB', 'AMINE', 'benettalebamine@gmail.com', '2021-03-08 10:00:00', 0, 1),
(38, 'YYYYYY', 'ELMASBAHY', 'IDRISS', 'idriss.elmasbahy.45@gmail.com', '2021-03-08 09:30:00', 0, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agence`
--
ALTER TABLE `agence`
  ADD PRIMARY KEY (`agence_Id`);

--
-- Indexes for table `bureau`
--
ALTER TABLE `bureau`
  ADD PRIMARY KEY (`bureau_Id`),
  ADD KEY `agence_Id` (`agence_Id`);

--
-- Indexes for table `employe`
--
ALTER TABLE `employe`
  ADD PRIMARY KEY (`employe_id`),
  ADD UNIQUE KEY `bureau_id` (`bureau_id`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`reservation_Id`),
  ADD KEY `bureau_id` (`bureau_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `agence`
--
ALTER TABLE `agence`
  MODIFY `agence_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `bureau`
--
ALTER TABLE `bureau`
  MODIFY `bureau_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `employe`
--
ALTER TABLE `employe`
  MODIFY `employe_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `reservation_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
