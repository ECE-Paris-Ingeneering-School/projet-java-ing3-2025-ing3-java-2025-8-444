-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : dim. 27 avr. 2025 à 20:42
-- Version du serveur : 9.1.0
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `rdv_specialiste`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`) VALUES
(5),
(6);

-- --------------------------------------------------------

--
-- Structure de la table `disponibilite`
--

DROP TABLE IF EXISTS `disponibilite`;
CREATE TABLE IF NOT EXISTS `disponibilite` (
  `id` int NOT NULL AUTO_INCREMENT,
  `specialiste_id` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `heure_debut` time DEFAULT NULL,
  `heure_fin` time DEFAULT NULL,
  `lieu_id` int DEFAULT NULL,
  `est_disponible` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `specialiste_id` (`specialiste_id`),
  KEY `lieu_id` (`lieu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `disponibilite`
--

INSERT INTO `disponibilite` (`id`, `specialiste_id`, `date`, `heure_debut`, `heure_fin`, `lieu_id`, `est_disponible`) VALUES
(1, 3, '2025-04-14', '09:00:00', '09:30:00', 1, 0),
(2, 3, '2025-04-14', '10:00:00', '10:30:00', 1, 0),
(3, 4, '2025-04-14', '14:00:00', '14:30:00', 2, 0),
(4, 4, '2025-04-15', '15:00:00', '15:30:00', 2, 0),
(5, 3, '2025-04-15', '11:00:00', '11:30:00', 3, 1),
(6, 4, '2025-04-16', '16:00:00', '16:30:00', 3, 0),
(7, 7, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(8, 8, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(9, 9, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(10, 10, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(11, 11, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(12, 12, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(13, 13, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(14, 14, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(15, 15, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(16, 16, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(17, 17, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(18, 18, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(19, 19, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(20, 20, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(21, 21, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(22, 22, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(23, 23, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(24, 24, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(25, 25, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(26, 26, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(27, 27, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(28, 28, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(29, 29, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(30, 30, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(31, 31, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(32, 32, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(33, 33, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(34, 34, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(35, 35, '2025-04-20', '09:00:00', '09:30:00', 1, 0),
(36, 36, '2025-04-20', '09:00:00', '09:30:00', 1, 1),
(37, 7, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(38, 8, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(39, 9, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(40, 10, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(41, 11, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(42, 12, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(43, 13, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(44, 14, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(45, 15, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(46, 16, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(47, 17, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(48, 18, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(49, 19, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(50, 20, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(51, 21, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(52, 22, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(53, 23, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(54, 24, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(55, 25, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(56, 26, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(57, 27, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(58, 28, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(59, 29, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(60, 30, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(61, 31, '2025-04-20', '10:00:00', '10:30:00', 2, 0),
(62, 32, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(63, 33, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(64, 34, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(65, 35, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(66, 36, '2025-04-20', '10:00:00', '10:30:00', 2, 1),
(67, 7, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(68, 8, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(69, 9, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(70, 10, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(71, 11, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(72, 12, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(73, 13, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(74, 14, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(75, 15, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(76, 16, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(77, 17, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(78, 18, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(79, 19, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(80, 20, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(81, 21, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(82, 22, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(83, 23, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(84, 24, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(85, 25, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(86, 26, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(87, 27, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(88, 28, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(89, 29, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(90, 30, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(91, 31, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(92, 32, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(93, 33, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(94, 34, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(95, 35, '2025-04-20', '11:00:00', '11:30:00', 3, 1),
(96, 36, '2025-04-20', '11:00:00', '11:30:00', 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

DROP TABLE IF EXISTS `lieu`;
CREATE TABLE IF NOT EXISTS `lieu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `ville` varchar(100) DEFAULT NULL,
  `code_postal` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `lieu`
--

INSERT INTO `lieu` (`id`, `nom`, `adresse`, `ville`, `code_postal`) VALUES
(1, 'Clinique du Centre', '12 rue de Paris', 'Paris', '75001'),
(2, 'Centre Médical République', '45 avenue République', 'Lyon', '69000'),
(3, 'Polyclinique Est', '89 boulevard Est', 'Marseille', '13000');

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`id`) VALUES
(1),
(2),
(7),
(8),
(9),
(10),
(37),
(38),
(39),
(40),
(41),
(42),
(43),
(44),
(45),
(46),
(47),
(48),
(49),
(50),
(51),
(52),
(53),
(54),
(55),
(56),
(57),
(58),
(59),
(60),
(61),
(62),
(63),
(64),
(65),
(66),
(67),
(68),
(69),
(70),
(71),
(72),
(73),
(74),
(75),
(76),
(77),
(78),
(79),
(80),
(81),
(82),
(83),
(84),
(85),
(86),
(87),
(88),
(89),
(90),
(91),
(92),
(93),
(94),
(95),
(96),
(97),
(98),
(99),
(100),
(101),
(102),
(103),
(104),
(105),
(106),
(107),
(108),
(109),
(110),
(111),
(112),
(113),
(114),
(115),
(116),
(117),
(118),
(119),
(120),
(121),
(122),
(123),
(124),
(125),
(126),
(127),
(128),
(129),
(130),
(131),
(132),
(133),
(134),
(135),
(136);

-- --------------------------------------------------------

--
-- Structure de la table `rendezvous`
--

DROP TABLE IF EXISTS `rendezvous`;
CREATE TABLE IF NOT EXISTS `rendezvous` (
  `id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int DEFAULT NULL,
  `specialiste_id` int DEFAULT NULL,
  `disponibilite_id` int DEFAULT NULL,
  `statut` varchar(50) DEFAULT NULL,
  `notes` text,
  PRIMARY KEY (`id`),
  KEY `patient_id` (`patient_id`),
  KEY `specialiste_id` (`specialiste_id`),
  KEY `disponibilite_id` (`disponibilite_id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `rendezvous`
--

INSERT INTO `rendezvous` (`id`, `patient_id`, `specialiste_id`, `disponibilite_id`, `statut`, `notes`) VALUES
(1, 1, 3, 1, 'annulé', 'Patient avec antécédents cardiaques'),
(2, 2, 3, 2, 'en attente', 'Première visite'),
(3, 1, 4, 3, 'annulé', 'Consultation pour allergie'),
(4, 2, 4, 4, 'annulé', 'Douleurs cutanées légères'),
(5, 1, 4, 4, 'annulé', ''),
(6, 1, 4, 6, 'annulé', ''),
(7, 1, 4, 6, 'annulé', ''),
(8, 1, 4, 6, 'annulé', ''),
(9, 1, 4, 6, 'confirmé', ''),
(10, 1, 4, 6, 'confirmé', ''),
(11, 1, 4, 6, 'confirmé', ''),
(12, 1, 4, 6, 'confirmé', ''),
(13, 1, 4, 6, 'confirmé', ''),
(14, 1, 4, 6, 'confirmé', ''),
(15, 1, 4, 6, 'confirmé', ''),
(16, 1, 4, 3, 'confirmé', ''),
(17, 1, 4, 3, 'confirmé', ''),
(18, 1, 4, 6, 'confirmé', ''),
(19, 1, 4, 3, 'confirmé', ''),
(20, 131, 7, 7, 'en attente', 'Rendez-vous automatique'),
(21, 79, 7, 37, 'confirmé', 'Rendez-vous automatique'),
(22, 101, 7, 67, 'en attente', 'Rendez-vous automatique'),
(23, 70, 8, 8, 'annulé', 'Rendez-vous automatique'),
(24, 102, 8, 38, 'confirmé', 'Rendez-vous automatique'),
(25, 71, 8, 68, 'en attente', 'Rendez-vous automatique'),
(26, 107, 9, 9, 'annulé', 'Rendez-vous automatique'),
(27, 69, 9, 39, 'annulé', 'Rendez-vous automatique'),
(28, 119, 9, 69, 'confirmé', 'Rendez-vous automatique'),
(29, 103, 10, 10, 'en attente', 'Rendez-vous automatique'),
(30, 69, 10, 40, 'en attente', 'Rendez-vous automatique'),
(31, 43, 10, 70, 'en attente', 'Rendez-vous automatique'),
(32, 64, 11, 11, 'annulé', 'Rendez-vous automatique'),
(33, 107, 11, 41, 'annulé', 'Rendez-vous automatique'),
(34, 135, 11, 71, 'en attente', 'Rendez-vous automatique'),
(35, 77, 12, 12, 'en attente', 'Rendez-vous automatique'),
(36, 117, 12, 42, 'confirmé', 'Rendez-vous automatique'),
(37, 99, 12, 72, 'en attente', 'Rendez-vous automatique'),
(38, 104, 13, 13, 'annulé', 'Rendez-vous automatique'),
(39, 57, 13, 43, 'en attente', 'Rendez-vous automatique'),
(40, 121, 13, 73, 'annulé', 'Rendez-vous automatique'),
(41, 53, 14, 14, 'en attente', 'Rendez-vous automatique'),
(42, 95, 14, 44, 'confirmé', 'Rendez-vous automatique'),
(43, 100, 14, 74, 'annulé', 'Rendez-vous automatique'),
(44, 117, 15, 15, 'confirmé', 'Rendez-vous automatique'),
(45, 91, 15, 45, 'confirmé', 'Rendez-vous automatique'),
(46, 54, 15, 75, 'en attente', 'Rendez-vous automatique'),
(47, 82, 16, 16, 'confirmé', 'Rendez-vous automatique'),
(48, 46, 16, 46, 'confirmé', 'Rendez-vous automatique'),
(49, 98, 16, 76, 'en attente', 'Rendez-vous automatique'),
(50, 130, 17, 17, 'annulé', 'Rendez-vous automatique'),
(51, 48, 17, 47, 'en attente', 'Rendez-vous automatique'),
(52, 124, 17, 77, 'en attente', 'Rendez-vous automatique'),
(53, 84, 18, 18, 'confirmé', 'Rendez-vous automatique'),
(54, 48, 18, 48, 'confirmé', 'Rendez-vous automatique'),
(55, 127, 18, 78, 'annulé', 'Rendez-vous automatique'),
(56, 55, 19, 19, 'en attente', 'Rendez-vous automatique'),
(57, 74, 19, 49, 'confirmé', 'Rendez-vous automatique'),
(58, 80, 19, 79, 'annulé', 'Rendez-vous automatique'),
(59, 135, 20, 20, 'en attente', 'Rendez-vous automatique'),
(60, 114, 20, 50, 'annulé', 'Rendez-vous automatique'),
(61, 113, 20, 80, 'en attente', 'Rendez-vous automatique'),
(62, 99, 21, 21, 'annulé', 'Rendez-vous automatique'),
(63, 38, 21, 51, 'confirmé', 'Rendez-vous automatique'),
(64, 92, 21, 81, 'en attente', 'Rendez-vous automatique'),
(65, 88, 22, 22, 'confirmé', 'Rendez-vous automatique'),
(66, 121, 22, 52, 'en attente', 'Rendez-vous automatique'),
(67, 78, 22, 82, 'annulé', 'Rendez-vous automatique'),
(68, 69, 23, 23, 'annulé', 'Rendez-vous automatique'),
(69, 82, 23, 53, 'en attente', 'Rendez-vous automatique'),
(70, 98, 23, 83, 'confirmé', 'Rendez-vous automatique'),
(71, 92, 24, 24, 'annulé', 'Rendez-vous automatique'),
(72, 41, 24, 54, 'en attente', 'Rendez-vous automatique'),
(73, 125, 24, 84, 'confirmé', 'Rendez-vous automatique'),
(74, 81, 25, 25, 'en attente', 'Rendez-vous automatique'),
(75, 88, 25, 55, 'annulé', 'Rendez-vous automatique'),
(76, 118, 25, 85, 'en attente', 'Rendez-vous automatique'),
(77, 112, 26, 26, 'en attente', 'Rendez-vous automatique'),
(78, 42, 26, 56, 'annulé', 'Rendez-vous automatique'),
(79, 65, 26, 86, 'annulé', 'Rendez-vous automatique'),
(80, 131, 27, 27, 'en attente', 'Rendez-vous automatique'),
(81, 72, 27, 57, 'en attente', 'Rendez-vous automatique'),
(82, 60, 27, 87, 'annulé', 'Rendez-vous automatique'),
(83, 63, 28, 28, 'annulé', 'Rendez-vous automatique'),
(84, 124, 28, 58, 'en attente', 'Rendez-vous automatique'),
(85, 71, 28, 88, 'annulé', 'Rendez-vous automatique'),
(86, 109, 29, 29, 'annulé', 'Rendez-vous automatique'),
(87, 106, 29, 59, 'confirmé', 'Rendez-vous automatique'),
(88, 103, 29, 89, 'annulé', 'Rendez-vous automatique'),
(89, 66, 30, 30, 'annulé', 'Rendez-vous automatique'),
(90, 110, 30, 60, 'annulé', 'Rendez-vous automatique'),
(91, 65, 30, 90, 'annulé', 'Rendez-vous automatique'),
(92, 123, 31, 31, 'confirmé', 'Rendez-vous automatique'),
(93, 117, 31, 61, 'annulé', 'Rendez-vous automatique'),
(94, 90, 31, 91, 'confirmé', 'Rendez-vous automatique'),
(95, 127, 32, 32, 'en attente', 'Rendez-vous automatique'),
(96, 71, 32, 62, 'annulé', 'Rendez-vous automatique'),
(97, 81, 32, 92, 'en attente', 'Rendez-vous automatique'),
(98, 62, 33, 33, 'annulé', 'Rendez-vous automatique'),
(99, 131, 33, 63, 'en attente', 'Rendez-vous automatique'),
(100, 94, 33, 93, 'en attente', 'Rendez-vous automatique'),
(101, 73, 34, 34, 'en attente', 'Rendez-vous automatique'),
(102, 114, 34, 64, 'confirmé', 'Rendez-vous automatique'),
(103, 82, 34, 94, 'annulé', 'Rendez-vous automatique'),
(104, 98, 35, 35, 'annulé', 'Rendez-vous automatique'),
(105, 102, 35, 65, 'confirmé', 'Rendez-vous automatique'),
(106, 120, 35, 95, 'annulé', 'Rendez-vous automatique'),
(107, 40, 36, 36, 'confirmé', 'Rendez-vous automatique'),
(108, 49, 36, 66, 'en attente', 'Rendez-vous automatique'),
(109, 49, 36, 96, 'confirmé', 'Rendez-vous automatique'),
(147, 1, 31, 61, 'confirmé', ''),
(148, 1, 35, 35, 'confirmé', ''),
(149, 1, 3, 1, 'confirmé', ''),
(150, 1, 3, 2, 'confirmé', '');

-- --------------------------------------------------------

--
-- Structure de la table `specialiste`
--

DROP TABLE IF EXISTS `specialiste`;
CREATE TABLE IF NOT EXISTS `specialiste` (
  `id` int NOT NULL,
  `qualification` varchar(255) DEFAULT NULL,
  `specialite_nom` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `specialite_nom` (`specialite_nom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `specialiste`
--

INSERT INTO `specialiste` (`id`, `qualification`, `specialite_nom`) VALUES
(3, 'Diplôme Universitaire de Cardiologie', 'Cardiologie'),
(4, 'Diplôme en Dermatologie', 'Dermatologie'),
(7, 'Certificat en Gastro-entérologie', 'Gastro-entérologie'),
(8, 'Diplôme en Neurologie', 'Neurologie'),
(9, 'Diplôme en Ophtalmologie', 'Ophtalmologie'),
(10, 'Diplôme en Pédiatrie', 'Pédiatrie'),
(11, 'Diplôme en Psychiatrie', 'Psychiatrie'),
(12, 'Certificat en Cardiologie', 'Cardiologie'),
(13, 'Diplôme en Dermatologie', 'Dermatologie'),
(14, 'Diplôme en Gastro-entérologie', 'Gastro-entérologie'),
(15, 'Diplôme en Neurologie', 'Neurologie'),
(16, 'Certificat en Ophtalmologie', 'Ophtalmologie'),
(17, 'Diplôme en Pédiatrie', 'Pédiatrie'),
(18, 'Diplôme en Psychiatrie', 'Psychiatrie'),
(19, 'Diplôme en Cardiologie', 'Cardiologie'),
(20, 'Diplôme en Dermatologie', 'Dermatologie'),
(21, 'Certificat en Gastro-entérologie', 'Gastro-entérologie'),
(22, 'Diplôme en Neurologie', 'Neurologie'),
(23, 'Diplôme en Ophtalmologie', 'Ophtalmologie'),
(24, 'Diplôme en Pédiatrie', 'Pédiatrie'),
(25, 'Diplôme en Psychiatrie', 'Psychiatrie'),
(26, 'Certificat en Cardiologie', 'Cardiologie'),
(27, 'Diplôme en Dermatologie', 'Dermatologie'),
(28, 'Diplôme en Gastro-entérologie', 'Gastro-entérologie'),
(29, 'Diplôme en Neurologie', 'Neurologie'),
(30, 'Certificat en Ophtalmologie', 'Ophtalmologie'),
(31, 'Diplôme en Pédiatrie', 'Pédiatrie'),
(32, 'Diplôme en Psychiatrie', 'Psychiatrie'),
(33, 'Diplôme en Cardiologie', 'Cardiologie'),
(34, 'Diplôme en Dermatologie', 'Dermatologie'),
(35, 'Diplôme en Gastro-entérologie', 'Gastro-entérologie'),
(36, 'Diplôme en Neurologie', 'Neurologie');

-- --------------------------------------------------------

--
-- Structure de la table `specialite`
--

DROP TABLE IF EXISTS `specialite`;
CREATE TABLE IF NOT EXISTS `specialite` (
  `nom` varchar(100) NOT NULL,
  `description` text,
  PRIMARY KEY (`nom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `specialite`
--

INSERT INTO `specialite` (`nom`, `description`) VALUES
('Cardiologie', 'Spécialiste du cœur et du système circulatoire'),
('Dermatologie', 'Spécialiste de la peau'),
('Gastro-entérologie', 'Spécialiste du système digestif'),
('Neurologie', 'Spécialiste du cerveau et du système nerveux'),
('Ophtalmologie', 'Spécialiste des yeux et de la vision'),
('Pédiatrie', 'Spécialiste de la santé des enfants'),
('Psychiatrie', 'Spécialiste de la santé mentale');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `mot_de_passe` varchar(255) DEFAULT NULL,
  `type` enum('patient','admin','specialiste') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `email`, `mot_de_passe`, `type`) VALUES
(1, 'Dupont', 'Alice', 'alice@exemple.com', 'pass123', 'patient'),
(2, 'Martin', 'Bob', 'bob@exemple.com', 'pass123', 'patient'),
(3, 'Durand', 'Charles', 'charles@exemple.com', 'pass123', 'specialiste'),
(4, 'Moreau', 'Diane', 'diane@exemple.com', 'pass123', 'specialiste'),
(5, 'Admin', 'Eve', 'eve@exemple.com', 'adminpass', 'admin'),
(6, 'Admin', 'Frank', 'frank@exemple.com', 'adminpass', 'admin'),
(7, 'Leo', 'L', 'leopold@exemple.com', 'pass123', 'patient'),
(8, 'Leo', 'L', 'leo@exemple.com', 'pass123', 'patient'),
(9, 'Leny', 'Andre', 'leny@exemple.com', 'pass123', 'patient'),
(10, 'ANDRE', 'Leny', 'leny1@exemple.com', 'pass123', 'patient'),
(11, 'Lemoine', 'Adrien', 'adrien@exemple.com', 'pass123', 'specialiste'),
(12, 'Petit', 'Brigitte', 'brigitte@exemple.com', 'pass123', 'specialiste'),
(13, 'Benoit', 'Cedric', 'cedric@exemple.com', 'pass123', 'specialiste'),
(14, 'Garcia', 'Delphine', 'delphine@exemple.com', 'pass123', 'specialiste'),
(15, 'Faure', 'Eric', 'eric@exemple.com', 'pass123', 'specialiste'),
(16, 'Henry', 'Fanny', 'fanny@exemple.com', 'pass123', 'specialiste'),
(17, 'Noel', 'Gilles', 'gilles@exemple.com', 'pass123', 'specialiste'),
(18, 'Marchand', 'Helene', 'helene@exemple.com', 'pass123', 'specialiste'),
(19, 'Olivier', 'Ivan', 'ivan@exemple.com', 'pass123', 'specialiste'),
(20, 'Philippe', 'Julie', 'julie@exemple.com', 'pass123', 'specialiste'),
(21, 'Rey', 'Kevin', 'kevin@exemple.com', 'pass123', 'specialiste'),
(22, 'Simon', 'Laura', 'laura@exemple.com', 'pass123', 'specialiste'),
(23, 'Thomas', 'Marc', 'marc@exemple.com', 'pass123', 'specialiste'),
(24, 'Vidal', 'Nina', 'nina@exemple.com', 'pass123', 'specialiste'),
(25, 'Bernard', 'Olivier', 'olivierb@exemple.com', 'pass123', 'specialiste'),
(26, 'Collet', 'Pauline', 'pauline@exemple.com', 'pass123', 'specialiste'),
(27, 'Devaux', 'Quentin', 'quentin@exemple.com', 'pass123', 'specialiste'),
(28, 'Esteve', 'Roxane', 'roxane@exemple.com', 'pass123', 'specialiste'),
(29, 'Fabre', 'Sophie', 'sophie@exemple.com', 'pass123', 'specialiste'),
(30, 'Gauthier', 'Thomas', 'thomasg@exemple.com', 'pass123', 'specialiste'),
(31, 'Hubert', 'Ursula', 'ursula@exemple.com', 'pass123', 'specialiste'),
(32, 'Imbert', 'Victor', 'victor@exemple.com', 'pass123', 'specialiste'),
(33, 'Joly', 'Wendy', 'wendy@exemple.com', 'pass123', 'specialiste'),
(34, 'Klein', 'Xavier', 'xavier@exemple.com', 'pass123', 'specialiste'),
(35, 'Lambert', 'Yasmine', 'yasmine@exemple.com', 'pass123', 'specialiste'),
(36, 'Meyer', 'Zoe', 'zoe@exemple.com', 'pass123', 'specialiste'),
(37, 'Nicolas', 'Annie', 'annie@exemple.com', 'pass123', 'specialiste'),
(38, 'Pires', 'Bastien', 'bastien@exemple.com', 'pass123', 'specialiste'),
(39, 'Rousseau', 'Chloe', 'chloe@exemple.com', 'pass123', 'specialiste'),
(40, 'Sanchez', 'David', 'david@exemple.com', 'pass123', 'specialiste'),
(41, 'Patient46', 'Test46', 'patient46@exemple.com', 'pass123', 'patient'),
(42, 'Patient45', 'Test45', 'patient45@exemple.com', 'pass123', 'patient'),
(43, 'Patient44', 'Test44', 'patient44@exemple.com', 'pass123', 'patient'),
(44, 'Patient43', 'Test43', 'patient43@exemple.com', 'pass123', 'patient'),
(45, 'Patient42', 'Test42', 'patient42@exemple.com', 'pass123', 'patient'),
(46, 'Patient41', 'Test41', 'patient41@exemple.com', 'pass123', 'patient'),
(47, 'Patient40', 'Test40', 'patient40@exemple.com', 'pass123', 'patient'),
(48, 'Patient39', 'Test39', 'patient39@exemple.com', 'pass123', 'patient'),
(49, 'Patient38', 'Test38', 'patient38@exemple.com', 'pass123', 'patient'),
(50, 'Patient37', 'Test37', 'patient37@exemple.com', 'pass123', 'patient'),
(51, 'Patient56', 'Test56', 'patient56@exemple.com', 'pass123', 'patient'),
(52, 'Patient55', 'Test55', 'patient55@exemple.com', 'pass123', 'patient'),
(53, 'Patient54', 'Test54', 'patient54@exemple.com', 'pass123', 'patient'),
(54, 'Patient53', 'Test53', 'patient53@exemple.com', 'pass123', 'patient'),
(55, 'Patient52', 'Test52', 'patient52@exemple.com', 'pass123', 'patient'),
(56, 'Patient51', 'Test51', 'patient51@exemple.com', 'pass123', 'patient'),
(57, 'Patient50', 'Test50', 'patient50@exemple.com', 'pass123', 'patient'),
(58, 'Patient49', 'Test49', 'patient49@exemple.com', 'pass123', 'patient'),
(59, 'Patient48', 'Test48', 'patient48@exemple.com', 'pass123', 'patient'),
(60, 'Patient47', 'Test47', 'patient47@exemple.com', 'pass123', 'patient'),
(61, 'Patient66', 'Test66', 'patient66@exemple.com', 'pass123', 'patient'),
(62, 'Patient65', 'Test65', 'patient65@exemple.com', 'pass123', 'patient'),
(63, 'Patient64', 'Test64', 'patient64@exemple.com', 'pass123', 'patient'),
(64, 'Patient63', 'Test63', 'patient63@exemple.com', 'pass123', 'patient'),
(65, 'Patient62', 'Test62', 'patient62@exemple.com', 'pass123', 'patient'),
(66, 'Patient61', 'Test61', 'patient61@exemple.com', 'pass123', 'patient'),
(67, 'Patient60', 'Test60', 'patient60@exemple.com', 'pass123', 'patient'),
(68, 'Patient59', 'Test59', 'patient59@exemple.com', 'pass123', 'patient'),
(69, 'Patient58', 'Test58', 'patient58@exemple.com', 'pass123', 'patient'),
(70, 'Patient57', 'Test57', 'patient57@exemple.com', 'pass123', 'patient'),
(71, 'Patient76', 'Test76', 'patient76@exemple.com', 'pass123', 'patient'),
(72, 'Patient75', 'Test75', 'patient75@exemple.com', 'pass123', 'patient'),
(73, 'Patient74', 'Test74', 'patient74@exemple.com', 'pass123', 'patient'),
(74, 'Patient73', 'Test73', 'patient73@exemple.com', 'pass123', 'patient'),
(75, 'Patient72', 'Test72', 'patient72@exemple.com', 'pass123', 'patient'),
(76, 'Patient71', 'Test71', 'patient71@exemple.com', 'pass123', 'patient'),
(77, 'Patient70', 'Test70', 'patient70@exemple.com', 'pass123', 'patient'),
(78, 'Patient69', 'Test69', 'patient69@exemple.com', 'pass123', 'patient'),
(79, 'Patient68', 'Test68', 'patient68@exemple.com', 'pass123', 'patient'),
(80, 'Patient67', 'Test67', 'patient67@exemple.com', 'pass123', 'patient'),
(81, 'Patient86', 'Test86', 'patient86@exemple.com', 'pass123', 'patient'),
(82, 'Patient85', 'Test85', 'patient85@exemple.com', 'pass123', 'patient'),
(83, 'Patient84', 'Test84', 'patient84@exemple.com', 'pass123', 'patient'),
(84, 'Patient83', 'Test83', 'patient83@exemple.com', 'pass123', 'patient'),
(85, 'Patient82', 'Test82', 'patient82@exemple.com', 'pass123', 'patient'),
(86, 'Patient81', 'Test81', 'patient81@exemple.com', 'pass123', 'patient'),
(87, 'Patient80', 'Test80', 'patient80@exemple.com', 'pass123', 'patient'),
(88, 'Patient79', 'Test79', 'patient79@exemple.com', 'pass123', 'patient'),
(89, 'Patient78', 'Test78', 'patient78@exemple.com', 'pass123', 'patient'),
(90, 'Patient77', 'Test77', 'patient77@exemple.com', 'pass123', 'patient'),
(91, 'Patient96', 'Test96', 'patient96@exemple.com', 'pass123', 'patient'),
(92, 'Patient95', 'Test95', 'patient95@exemple.com', 'pass123', 'patient'),
(93, 'Patient94', 'Test94', 'patient94@exemple.com', 'pass123', 'patient'),
(94, 'Patient93', 'Test93', 'patient93@exemple.com', 'pass123', 'patient'),
(95, 'Patient92', 'Test92', 'patient92@exemple.com', 'pass123', 'patient'),
(96, 'Patient91', 'Test91', 'patient91@exemple.com', 'pass123', 'patient'),
(97, 'Patient90', 'Test90', 'patient90@exemple.com', 'pass123', 'patient'),
(98, 'Patient89', 'Test89', 'patient89@exemple.com', 'pass123', 'patient'),
(99, 'Patient88', 'Test88', 'patient88@exemple.com', 'pass123', 'patient'),
(100, 'Patient87', 'Test87', 'patient87@exemple.com', 'pass123', 'patient'),
(101, 'Patient106', 'Test106', 'patient106@exemple.com', 'pass123', 'patient'),
(102, 'Patient105', 'Test105', 'patient105@exemple.com', 'pass123', 'patient'),
(103, 'Patient104', 'Test104', 'patient104@exemple.com', 'pass123', 'patient'),
(104, 'Patient103', 'Test103', 'patient103@exemple.com', 'pass123', 'patient'),
(105, 'Patient102', 'Test102', 'patient102@exemple.com', 'pass123', 'patient'),
(106, 'Patient101', 'Test101', 'patient101@exemple.com', 'pass123', 'patient'),
(107, 'Patient100', 'Test100', 'patient100@exemple.com', 'pass123', 'patient'),
(108, 'Patient99', 'Test99', 'patient99@exemple.com', 'pass123', 'patient'),
(109, 'Patient98', 'Test98', 'patient98@exemple.com', 'pass123', 'patient'),
(110, 'Patient97', 'Test97', 'patient97@exemple.com', 'pass123', 'patient'),
(111, 'Patient116', 'Test116', 'patient116@exemple.com', 'pass123', 'patient'),
(112, 'Patient115', 'Test115', 'patient115@exemple.com', 'pass123', 'patient'),
(113, 'Patient114', 'Test114', 'patient114@exemple.com', 'pass123', 'patient'),
(114, 'Patient113', 'Test113', 'patient113@exemple.com', 'pass123', 'patient'),
(115, 'Patient112', 'Test112', 'patient112@exemple.com', 'pass123', 'patient'),
(116, 'Patient111', 'Test111', 'patient111@exemple.com', 'pass123', 'patient'),
(117, 'Patient110', 'Test110', 'patient110@exemple.com', 'pass123', 'patient'),
(118, 'Patient109', 'Test109', 'patient109@exemple.com', 'pass123', 'patient'),
(119, 'Patient108', 'Test108', 'patient108@exemple.com', 'pass123', 'patient'),
(120, 'Patient107', 'Test107', 'patient107@exemple.com', 'pass123', 'patient'),
(121, 'Patient126', 'Test126', 'patient126@exemple.com', 'pass123', 'patient'),
(122, 'Patient125', 'Test125', 'patient125@exemple.com', 'pass123', 'patient'),
(123, 'Patient124', 'Test124', 'patient124@exemple.com', 'pass123', 'patient'),
(124, 'Patient123', 'Test123', 'patient123@exemple.com', 'pass123', 'patient'),
(125, 'Patient122', 'Test122', 'patient122@exemple.com', 'pass123', 'patient'),
(126, 'Patient121', 'Test121', 'patient121@exemple.com', 'pass123', 'patient'),
(127, 'Patient120', 'Test120', 'patient120@exemple.com', 'pass123', 'patient'),
(128, 'Patient119', 'Test119', 'patient119@exemple.com', 'pass123', 'patient'),
(129, 'Patient118', 'Test118', 'patient118@exemple.com', 'pass123', 'patient'),
(130, 'Patient117', 'Test117', 'patient117@exemple.com', 'pass123', 'patient'),
(131, 'Patient136', 'Test136', 'patient136@exemple.com', 'pass123', 'patient'),
(132, 'Patient135', 'Test135', 'patient135@exemple.com', 'pass123', 'patient'),
(133, 'Patient134', 'Test134', 'patient134@exemple.com', 'pass123', 'patient'),
(134, 'Patient133', 'Test133', 'patient133@exemple.com', 'pass123', 'patient'),
(135, 'Patient132', 'Test132', 'patient132@exemple.com', 'pass123', 'patient'),
(136, 'Patient131', 'Test131', 'patient131@exemple.com', 'pass123', 'patient'),
(137, 'Patient130', 'Test130', 'patient130@exemple.com', 'pass123', 'patient'),
(138, 'Patient129', 'Test129', 'patient129@exemple.com', 'pass123', 'patient'),
(139, 'Patient128', 'Test128', 'patient128@exemple.com', 'pass123', 'patient'),
(140, 'Patient127', 'Test127', 'patient127@exemple.com', 'pass123', 'patient');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `disponibilite`
--
ALTER TABLE `disponibilite`
  ADD CONSTRAINT `disponibilite_ibfk_1` FOREIGN KEY (`specialiste_id`) REFERENCES `specialiste` (`id`),
  ADD CONSTRAINT `disponibilite_ibfk_2` FOREIGN KEY (`lieu_id`) REFERENCES `lieu` (`id`);

--
-- Contraintes pour la table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `rendezvous`
--
ALTER TABLE `rendezvous`
  ADD CONSTRAINT `rendezvous_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  ADD CONSTRAINT `rendezvous_ibfk_2` FOREIGN KEY (`specialiste_id`) REFERENCES `specialiste` (`id`),
  ADD CONSTRAINT `rendezvous_ibfk_3` FOREIGN KEY (`disponibilite_id`) REFERENCES `disponibilite` (`id`);

--
-- Contraintes pour la table `specialiste`
--
ALTER TABLE `specialiste`
  ADD CONSTRAINT `specialiste_ibfk_1` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `specialiste_ibfk_2` FOREIGN KEY (`specialite_nom`) REFERENCES `specialite` (`nom`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

