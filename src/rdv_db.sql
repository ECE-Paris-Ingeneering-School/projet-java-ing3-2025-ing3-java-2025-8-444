-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 07 avr. 2025 à 19:31
-- Version du serveur : 8.2.0
-- Version de PHP : 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `rdv_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`, `nom`, `email`, `mot_de_passe`) VALUES
(1, 'Admin1', 'admin1@rdv.com', 'adminpass1'),
(2, 'Admin2', 'admin2@rdv.com', 'adminpass2');

-- --------------------------------------------------------

--
-- Structure de la table `disponibilite`
--

DROP TABLE IF EXISTS `disponibilite`;
CREATE TABLE IF NOT EXISTS `disponibilite` (
  `id` int NOT NULL AUTO_INCREMENT,
  `specialiste_id` int NOT NULL,
  `horaire` datetime NOT NULL,
  `disponible` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `specialiste_id` (`specialiste_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `disponibilite`
--

INSERT INTO `disponibilite` (`id`, `specialiste_id`, `horaire`, `disponible`) VALUES
(1, 1, '2025-04-10 09:00:00', 1),
(2, 1, '2025-04-10 10:00:00', 0),
(3, 2, '2025-04-11 14:00:00', 1),
(4, 3, '2025-04-12 11:00:00', 1),
(5, 4, '2025-04-13 15:00:00', 1),
(6, 5, '2025-04-14 16:00:00', 0),
(7, 2, '2025-04-11 15:00:00', 1),
(8, 1, '2025-04-10 11:00:00', 1);

-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

DROP TABLE IF EXISTS `lieu`;
CREATE TABLE IF NOT EXISTS `lieu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `adresse` varchar(255) NOT NULL,
  `ville` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `lieu`
--

INSERT INTO `lieu` (`id`, `adresse`, `ville`) VALUES
(1, '12 rue des Lilas', 'Paris'),
(2, '34 avenue Victor Hugo', 'Lyon'),
(3, '22 boulevard de la Paix', 'Marseille'),
(4, '5 rue Nationale', 'Toulouse'),
(5, '7 allée des Peupliers', 'Lille');

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `type_patient` enum('Nouveau','Ancien') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`id`, `nom`, `prenom`, `email`, `mot_de_passe`, `type_patient`) VALUES
(1, 'Durand', 'Claire', 'claire.durand@example.com', 'mdp1', 'Nouveau'),
(2, 'Martin', 'Luc', 'luc.martin@example.com', 'mdp2', 'Ancien'),
(3, 'Lemoine', 'Sophie', 'sophie.lemoine@example.com', 'mdp3', 'Ancien'),
(4, 'Bernard', 'Jean', 'jean.bernard@example.com', 'mdp4', 'Nouveau'),
(5, 'Dubois', 'Camille', 'camille.dubois@example.com', 'mdp5', 'Ancien');

-- --------------------------------------------------------

--
-- Structure de la table `rendezvous`
--

DROP TABLE IF EXISTS `rendezvous`;
CREATE TABLE IF NOT EXISTS `rendezvous` (
  `id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int NOT NULL,
  `specialiste_id` int NOT NULL,
  `disponibilite_id` int NOT NULL,
  `note` text,
  PRIMARY KEY (`id`),
  KEY `patient_id` (`patient_id`),
  KEY `specialiste_id` (`specialiste_id`),
  KEY `disponibilite_id` (`disponibilite_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `rendezvous`
--

INSERT INTO `rendezvous` (`id`, `patient_id`, `specialiste_id`, `disponibilite_id`, `note`) VALUES
(1, 2, 1, 2, 'Très bon cardiologue, ponctuel.'),
(2, 5, 5, 6, 'Écoute attentive et très professionnelle.');

-- --------------------------------------------------------

--
-- Structure de la table `specialiste`
--

DROP TABLE IF EXISTS `specialiste`;
CREATE TABLE IF NOT EXISTS `specialiste` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `specialisation` varchar(100) NOT NULL,
  `qualification` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `specialiste`
--

INSERT INTO `specialiste` (`id`, `nom`, `prenom`, `specialisation`, `qualification`) VALUES
(1, 'Dupont', 'Alice', 'Cardiologue', 'DES Cardiologie, 10 ans d’expérience'),
(2, 'Nguyen', 'Marc', 'Dermatologue', 'DES Dermatologie, publications internationales'),
(3, 'Roux', 'Laura', 'Gynécologue', 'DES Gynécologie, experte fertilité'),
(4, 'Khan', 'Omar', 'Ophtalmologue', 'DES Ophtalmologie, chirurgie laser'),
(5, 'Leclerc', 'Emma', 'Psychologue', 'Master Psychologie clinique');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
