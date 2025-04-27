package model;

/* Sources :
https://drive.google.com/file/d/10Qu73GKToaf7oOndfdIq0Zd4hZA8Z7Uc/view
https://drive.google.com/file/d/1QZy3Q5R-kIY697UORIlo7DmL8ZY_Kcwo/view
https://drive.google.com/file/d/1jUEKNnXz6l-_FBw6AGSPawFFzJeSDmTS/view
*/

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
