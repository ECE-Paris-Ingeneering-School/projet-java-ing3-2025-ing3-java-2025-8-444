
-- Création de la base
DROP DATABASE IF EXISTS rdv_specialiste;
CREATE DATABASE rdv_specialiste;
USE rdv_specialiste;

-- Table utilisateur
CREATE TABLE utilisateur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    mot_de_passe VARCHAR(255),
    type ENUM('patient', 'admin', 'specialiste') NOT NULL
);

-- Table spécialité
CREATE TABLE specialite (
    nom VARCHAR(100) PRIMARY KEY,
    description TEXT
);

-- Table specialiste
CREATE TABLE specialiste (
    id INT PRIMARY KEY,
    qualification VARCHAR(255),
    specialite_nom VARCHAR(100),
    FOREIGN KEY (id) REFERENCES utilisateur(id),
    FOREIGN KEY (specialite_nom) REFERENCES specialite(nom)
);

-- Table patient
CREATE TABLE patient (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES utilisateur(id)
);

-- Table admin
CREATE TABLE admin (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES utilisateur(id)
);

-- Table lieu
CREATE TABLE lieu (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100),
    adresse VARCHAR(255),
    ville VARCHAR(100),
    code_postal VARCHAR(10)
);

-- Table disponibilite
CREATE TABLE disponibilite (
    id INT AUTO_INCREMENT PRIMARY KEY,
    specialiste_id INT,
    date DATE,
    heure_debut TIME,
    heure_fin TIME,
    lieu_id INT,
    est_disponible BOOLEAN,
    FOREIGN KEY (specialiste_id) REFERENCES specialiste(id),
    FOREIGN KEY (lieu_id) REFERENCES lieu(id)
);

-- Table rendezvous
CREATE TABLE rendezvous (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT,
    specialiste_id INT,
    disponibilite_id INT,
    statut VARCHAR(50),
    notes TEXT,
    FOREIGN KEY (patient_id) REFERENCES patient(id),
    FOREIGN KEY (specialiste_id) REFERENCES specialiste(id),
    FOREIGN KEY (disponibilite_id) REFERENCES disponibilite(id)
);

-- Insertion des utilisateurs
INSERT INTO utilisateur (nom, prenom, email, mot_de_passe, type) VALUES
('Dupont', 'Alice', 'alice@exemple.com', 'pass123', 'patient'),
('Martin', 'Bob', 'bob@exemple.com', 'pass123', 'patient'),
('Durand', 'Charles', 'charles@exemple.com', 'pass123', 'specialiste'),
('Moreau', 'Diane', 'diane@exemple.com', 'pass123', 'specialiste'),
('Admin', 'Eve', 'eve@exemple.com', 'adminpass', 'admin'),
('Admin', 'Frank', 'frank@exemple.com', 'adminpass', 'admin');

-- Insertion des patients
INSERT INTO patient (id) VALUES (1), (2);

-- Insertion des admins
INSERT INTO admin (id) VALUES (5), (6);

-- Insertion des spécialités
INSERT INTO specialite (nom, description) VALUES
('Cardiologie', 'Spécialiste du cœur et du système circulatoire'),
('Dermatologie', 'Spécialiste de la peau');

-- Insertion des spécialistes
INSERT INTO specialiste (id, qualification, specialite_nom) VALUES
(3, 'Diplôme Universitaire de Cardiologie', 'Cardiologie'),
(4, 'Diplôme en Dermatologie', 'Dermatologie');

-- Insertion des lieux
INSERT INTO lieu (nom, adresse, ville, code_postal) VALUES
('Clinique du Centre', '12 rue de Paris', 'Paris', '75001'),
('Centre Médical République', '45 avenue République', 'Lyon', '69000'),
('Polyclinique Est', '89 boulevard Est', 'Marseille', '13000');

-- Insertion des disponibilités
INSERT INTO disponibilite (specialiste_id, date, heure_debut, heure_fin, lieu_id, est_disponible) VALUES
(3, '2025-04-14', '09:00:00', '09:30:00', 1, TRUE),
(3, '2025-04-14', '10:00:00', '10:30:00', 1, TRUE),
(4, '2025-04-14', '14:00:00', '14:30:00', 2, TRUE),
(4, '2025-04-15', '15:00:00', '15:30:00', 2, TRUE),
(3, '2025-04-15', '11:00:00', '11:30:00', 3, TRUE),
(4, '2025-04-16', '16:00:00', '16:30:00', 3, TRUE);

-- Insertion des rendez-vous
INSERT INTO rendezvous (patient_id, specialiste_id, disponibilite_id, statut, notes) VALUES
(1, 3, 1, 'confirmé', 'Patient avec antécédents cardiaques'),
(2, 3, 2, 'en attente', 'Première visite'),
(1, 4, 3, 'confirmé', 'Consultation pour allergie'),
(2, 4, 4, 'annulé', 'Douleurs cutanées légères');
