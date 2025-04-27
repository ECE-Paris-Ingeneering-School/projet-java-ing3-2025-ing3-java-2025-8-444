package model;

/**
 * Classe qui représente un patient :
 * Le patient peut rechercher des spécialistes et réserver des rendez-vous.
 *
 * Cette classe hérite d'Utilisateur.
 */

public class Patient extends Utilisateur {

    /**
     * Constructeur pour créer un patient.
     *
     * @param id Identifiant unique
     * @param nom Nom du patient
     * @param prenom Prénom du patient
     * @param email Email de connexion
     * @param motDePasse Mot de passe
     */
    public Patient(int id, String nom, String prenom, String email, String motDePasse) {
        super(id, nom, prenom, email, motDePasse);
    }
}
